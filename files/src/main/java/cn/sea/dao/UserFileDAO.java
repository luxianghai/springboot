package cn.sea.dao;

import cn.sea.entity.UserFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileDAO {

    // 根据登录用户的id获取用户的文件列表
    List<UserFile> findByUserId(Integer id);

    // 保存上传成功的文件信息
    void save(UserFile userFile);

    // 根据id查询文件信息
    UserFile findById(Integer id);

    // 更新文件下载次数
    void updateDownloadCounts(UserFile userFile);

    // 删除文件
    void delete(Integer id);
}
