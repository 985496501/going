package cn.yun.go.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jinyun liu
 * @date 2020/5/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private Integer age;
    private LocalDateTime birthday;
}
