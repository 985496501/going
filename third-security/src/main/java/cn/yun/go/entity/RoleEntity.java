package cn.yun.go.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;


/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/20:53
 */
@Data
@TableName("sys_role")
public class RoleEntity extends BaseEntity implements GrantedAuthority {

    private String roleName;

    private String desc;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.getRoleName();
    }
}
