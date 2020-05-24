package cn.yun.go.service.impl;

import cn.yun.go.dao.UserMapper;
import cn.yun.go.entity.UserEntity;
import cn.yun.go.manager.UserManager;
import cn.yun.go.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/19:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userManager.selectUserByUsername(username);
    }
}
