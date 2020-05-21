package cn.yun.go.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * @author jinyun liu
 * @date 2020/5/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    /**
     * 角色id
     */
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 创建时间
     */
    LocalDateTime createdAt;

    /**
     * 删除标记
     */
    private Integer deleted;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;
}
