package cn.sea.dao;

import cn.sea.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void save(User user);

    List<User> findAll();
}
