package cn.sea.controller;

import cn.sea.entity.User;
import cn.sea.entity.UserFile;
import cn.sea.service.UserFileService;
import io.swagger.annotations.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(tags = "文件控制器 FileController")
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private UserFileService userFileService;

    // 以json格式返回当前用户所有的文件信息，
    @ApiOperation("查询当前用户的所有文件信息，并以JSON格式返回")
    @GetMapping("/findAllJSON")
    @ResponseBody
    public List<UserFile> findAllJSON(@ApiIgnore HttpSession session) {
        // 在登录的session中获取用户信息
        User user = (User) session.getAttribute("user");
        // 根据用户id查询所有的文件信息
        List<UserFile> files = userFileService.findByUserId(user.getId());
        return files;
    }

    /**
     * 删除文件
     * @param id
     */
    @ApiOperation("根据文件id删除文件")
    @GetMapping("/delete")
    public String delete(@ApiParam(value = "文件id",required = true) Integer id) throws FileNotFoundException {
        // 根据id查询信息
        UserFile userFile = userFileService.findById(id);
        // 获取用户要删除的文件的路径
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();
        File file = new File(realPath, userFile.getNewFileName());
        // 删除文件
        if ( file.exists() ) {
            file.delete();  // 立即删除
        }

        // 删除数据库中的信息
        userFileService.delete(id);


        return "redirect:/file/showAll";
    }

    /**
     * 文件下载
     * @param openStyle
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation("根据文件id打开或下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openStyle" , value = "文件的打开方式attachment：附件，inline:在线打开", required = false),
            @ApiImplicitParam(name = "id", value = "文件id",required = true)
    })
    @GetMapping("/download")
    public void download(String openStyle, Integer id, @ApiIgnore HttpServletResponse response) throws IOException {
        // 获取打开方式
        openStyle = openStyle == null ? "attachment" : openStyle;
        // 获取文件信息
        UserFile userFile = userFileService.findById(id);
        // 点击下载时更新文件下载次数
        if ("attachment".equals(openStyle)) {
            userFile.setDownloadCounts(userFile.getDownloadCounts() + 1);
            userFileService.updateDownloadCounts(userFile);
        }
        // 获取并拼接文件的真实路径
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath() ;
        // 根据文件信息中的 文件名 和 文件存储l路径获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPath, userFile.getNewFileName()));
        // 配置附件下载，并指定编码方式
        response.setHeader("content-disposition",openStyle+";filename="+ URLEncoder.encode(userFile.getOldFileName(), "UTF-8"));
        // 获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        // 文件拷贝
        IOUtils.copy(is, os); // 将 is 传给 os进行输出
        // 关流
        IOUtils.closeQuietly(os);
        IOUtils.closeQuietly(is);
    }

    /**
     * 文件上传
     * @param file
     * @param session
     * @return
     * @throws IOException
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public String upload(@ApiParam("文件") MultipartFile file, @ApiIgnore HttpSession session) throws IOException {
        // 0.获取登录用户的信息
        User user = (User) session.getAttribute("user");

        // 1.获取文件的原始名称
        String oldFilename = file.getOriginalFilename();
        // 2. 获取文件后缀
        String extension = "." +  FilenameUtils.getExtension(oldFilename);
        // 3.生成新的文件名
        String newFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + UUID.randomUUID().toString().replace("-","") + extension;
        // 4. 获取文件的大小
        long size = file.getSize();
        // 5. 获取文件的类型
        String type = file.getContentType();


        // 6.获取文件的存储路径(位置)
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
        // 7.创建一个日期文件夹
        // 7.1 生成文件名
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = realPath + "/" + dateFormat;
        File dateDir = new File(dateDirPath);
        // 7.2创建日期文件夹
        if (!dateDir.exists()) { // 如果不存在
            dateDir.mkdirs(); // 创建日期目录
        }

        // 8. 件上传
        file.transferTo(new File(dateDir, newFilename));

        // 8.将文件信息放入数据库中
        UserFile userFile = new UserFile();
        // 处理文件对象的信息
        userFile.setOldFileName(oldFilename).setNewFileName(newFilename).setExt(extension).setSize(String.valueOf(size));
        userFile.setType(type).setPath("/files/"+dateFormat).setUserId(user.getId());
        userFileService.save(userFile);
        return "redirect:/file/showAll";
    }

    /**
     * 展示当前用户的所有文件
     * @param model
     * @param session
     * @return
     */
    @ApiIgnore
    @GetMapping("/showAll")
    public String findAll(Model model, HttpSession session) {
        // 在登录的session中获取用户信息
        User user = (User) session.getAttribute("user");
        // 根据用户id查询所有的文件信息
        List<UserFile> files = userFileService.findByUserId(user.getId());
        // 将读取到的文件信息存入作用域中
        model.addAttribute("files", files);
        return "showAll";
    }
}
