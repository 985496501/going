package cn.yun.go.service;

import cn.yun.go.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/19:16
 */
public interface UserService extends IService<UserEntity>, UserDetailsService {


}
