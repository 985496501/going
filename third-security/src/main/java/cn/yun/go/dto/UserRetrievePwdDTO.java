package cn.yun.go.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jinyun liu
 * @date 2020/5/22
 */
@Data
@ApiModel("用户找回密码对象")
public class UserRetrievePwdDTO {
    @ApiModelProperty(required = true, notes = "申请的账号")
    private String userName;

    @ApiModelProperty(required = true, notes = "手机号")
    private String phoneNum;
}
