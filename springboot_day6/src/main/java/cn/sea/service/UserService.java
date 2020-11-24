package cn.sea.service;


import cn.sea.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();
}
