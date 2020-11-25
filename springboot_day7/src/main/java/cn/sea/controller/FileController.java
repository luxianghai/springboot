package cn.sea.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    // 文件下载方式一
    @GetMapping("/download")
    public void download(String filename, HttpServletResponse response) throws IOException {

        // 根据文件名去指定目录中查找文件
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
        System.out.println("filename = " + filename);
        // 读取文件
        File file = new File(realPath, filename);
        // 获取文件输入流
        FileInputStream is = new FileInputStream(file);
        // attachment; 附件下载     inline;在线打开   fileName：要下载的文件的文件名
        response.setHeader("Content-Disposition", "attachment;filename="+filename);

        // 获取文件响应输出流
        ServletOutputStream os = response.getOutputStream();

        // 文件拷贝方式二
        IOUtils.copy(is, os);
        // 关流（优雅）
        IOUtils.closeQuietly(os);
        IOUtils.closeQuietly(is);

        /*// 文件拷贝方式一
        int len = 0;
        byte[] b = new byte[1024];
        while (true) {
            // 将数据读入 b 字节数组中
            len = is.read(b);
            if ( len == -1 ) { // 如果返回 -1 则表示读取完成
                break;
            }
            // 将 b 字节的数据写出
            os.write(b, 0, len);
        }

        // 关流
        if ( os != null ) {
            os.close();
        }
        if ( is != null ) {
            is.close();
        }*/

    }

    // 文件下载方式二
    @GetMapping("/download2")
    public ResponseEntity<byte[]> download2(String filename) throws IOException {
        // 根据文件名去指定目录中查找文件
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
        System.out.println("filename = " + filename);
        // 读取文件
        File file = new File(realPath, filename);
        // 获取文件输入流
        FileInputStream is = new FileInputStream(file);

        byte[] tmp = new byte[is.available()];
        is.read(tmp); // 将 is 的数据读取到 tmp 数组中
        // 关流
        IOUtils.closeQuietly(is);

        // 将要下载的流返回
        MultiValueMap<String, String> heads = new HttpHeaders();
        // 设置文件下载的请求头
        heads.set("content-disposition", "attachment;filename="+filename);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(tmp, heads, HttpStatus.OK);
        return responseEntity;
    }

    // 文件上传
    @PostMapping("/upload")
    public String upload(MultipartFile aaa, HttpServletRequest request) throws IOException {
        // 文件上传
        System.out.println("文件名："+aaa.getOriginalFilename());
        System.out.println("文件类型："+aaa.getContentType());
        System.out.println("文件大小："+aaa.getSize());

        // 处理文件上传路径
        //String realPath = request.getServletContext().getRealPath("/file"); // 这里获取的是本机上的路径
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
        System.out.println("realPath = " + realPath );
        // 日期目录创建
        String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File dir = new File(realPath, dateDir);
        if ( !dir.exists() ) {
            dir.mkdirs();
        }

        // 修改文件名  时间戳+UUID
        String newFileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + UUID.randomUUID().toString();
        // 获取文件后缀
        String extension = FilenameUtils.getExtension(aaa.getOriginalFilename());
        // 新文件名
        String newFilename = newFileNamePrefix + "." + extension ;
        aaa.transferTo(new File(dir, newFilename));

        return "redirect:/upload.html";
    }
}
