package cn.sea.dao;

import cn.sea.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    User login(User user);
}
