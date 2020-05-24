package cn.yun.go.manager;

import cn.yun.go.dao.RoleMapper;
import cn.yun.go.dao.UserMapper;
import cn.yun.go.entity.RoleEntity;
import cn.yun.go.entity.UserEntity;
import cn.yun.go.enums.UserStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/21:06
 */
@Component
public class UserManager {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过用户名查询用户信息及其角色
     *
     * @param username 用户名
     * @return 用户信息
     */
    public UserEntity selectUserByUsername(String username) {
        UserEntity userEntity = userMapper.selectOne(new QueryWrapper<UserEntity>()
                .eq("username", username)
                .eq("status", UserStatusEnum.ACTIVE.getStatus()));
        if (userEntity != null) {
            List<RoleEntity> roleEntities = roleMapper.selectAllRolesByUserId(userEntity.getId());
            userEntity.setList(roleEntities);
        }

        return userEntity;
    }

}
