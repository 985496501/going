package cn.yun.go.dao;

import cn.yun.go.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/19:10
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
