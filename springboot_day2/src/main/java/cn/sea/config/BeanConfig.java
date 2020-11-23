package cn.sea.config;

import cn.sea.entity.User;

//@Configuration
public class BeanConfig {

    //@Bean // @Bean 将当前方法的返回值作为作为工厂中的一个对象进行管理
    public User getUser() {
        return new User();
    }
}
