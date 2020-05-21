package cn.yun.go.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * 权限表
 * Those @RedisHash and @Id are responsible for creating the actual key used to persist the hash.
 *
 * @author jinyun liu
 * @date 2020/5/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("permission")
public class PermissionDTO {
    @Id
    private Long id;

    private Long parentId;

    private String rightName;

    private String description;
}
