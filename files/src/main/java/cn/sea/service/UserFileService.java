package cn.sea.service;

import cn.sea.entity.UserFile;

import java.util.List;

public interface UserFileService {

    // 根据登录用户的id获取用户的文件列表
    List<UserFile> findByUserId(Integer id);

    // 保存上传成功的文件信息
    void save(UserFile userFile);

    // 根据id查询文件信息
    UserFile findById(Integer id);

    /**
     * 更新文件下载次数
     * @param userFile
     */
    void updateDownloadCounts(UserFile userFile);

    /**
     * 删除文件
     * @param id
     */
    void delete(Integer id);
}
