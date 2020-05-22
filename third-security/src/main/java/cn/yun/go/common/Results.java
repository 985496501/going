package cn.yun.go.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的返回值
 * @author jinyun liu
 * @date 2020/5/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("接口通用返回对象")
public class Results<T> {
    @ApiModelProperty(required = true, notes = "状态码", example = "200")
    private int status;

    @ApiModelProperty(required = true, notes = "时间戳", example = "1590118183883")
    private long time;

    @ApiModelProperty(required = true, notes = "返回信息", example = "SUCCESS")
    private String message;

    @ApiModelProperty(required = true, notes = "返回数据", example = "123 demo")
    private T data;

    public Results(int status, String message, T data) {
        setStatus(status);
        setMessage(message);
        setTime(System.currentTimeMillis());
        setData(data);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
