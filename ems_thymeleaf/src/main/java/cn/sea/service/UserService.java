package cn.sea.service;

import cn.sea.entity.User;

public interface UserService {

    void save(User user);

    User login(String username, String password);

}
