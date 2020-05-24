package cn.yun.go.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/20:55
 */
@Data
public class BaseEntity implements Serializable {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer deleted;
}
