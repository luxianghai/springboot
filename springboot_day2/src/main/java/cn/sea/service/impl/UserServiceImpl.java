package cn.sea.service.impl;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println(user);
    }
}
