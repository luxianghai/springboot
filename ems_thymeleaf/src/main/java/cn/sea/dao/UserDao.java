package cn.sea.dao;

import cn.sea.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    void save(User user);

    User login(@Param("username") String username, @Param("password") String password);
}
