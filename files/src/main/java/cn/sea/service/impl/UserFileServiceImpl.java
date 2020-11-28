package cn.sea.service.impl;

import cn.sea.dao.UserFileDAO;
import cn.sea.entity.UserFile;
import cn.sea.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    private UserFileDAO userFileDAO;

    @Override
    @Transactional( propagation = Propagation.SUPPORTS )
    public List<UserFile> findByUserId(Integer id) {
        return userFileDAO.findByUserId(id);
    }

    @Override
    public void save(UserFile userFile) {
        //userFile.setIsimg() ?  是否是图片,当类型中h含有 image 时说明当前类型一定为图片
        String isImage = userFile.getType().startsWith("image") ? "是":"否";
        userFile.setIsimg(isImage);
        userFile.setDownloadCounts(0);
        userFile.setUploadTime(new Date());
        userFileDAO.save(userFile);
    }

    @Override
    public UserFile findById(Integer id) {
        return userFileDAO.findById(id);
    }

    // 更新文件下载次数
    @Override
    public void updateDownloadCounts(UserFile userFile) {
        userFileDAO.updateDownloadCounts(userFile);
    }

    // 删除文件
    @Override
    public void delete(Integer id) {
        userFileDAO.delete(id);
    }
}
