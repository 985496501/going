package cn.yun.go.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinyun liu
 * @date 2020/5/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户对象")
public class UserVO {
    @ApiModelProperty(required = true, notes = "用户名", example = "jinyun liu")
    private String userName;

    @ApiModelProperty(required = true, notes = "密码", example = "12345600")
    private String password;
}
