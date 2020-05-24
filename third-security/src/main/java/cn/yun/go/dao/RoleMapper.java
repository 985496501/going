package cn.yun.go.dao;

import cn.yun.go.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/19:10
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
    /**
     * 根据用户id查询所有的用户角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<RoleEntity> selectAllRolesByUserId(Long userId);
}
