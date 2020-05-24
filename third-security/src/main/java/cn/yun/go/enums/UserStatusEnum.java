package cn.yun.go.enums;

import lombok.Getter;

/**
 * @author: Liu Jinyun
 * @date: 2020/5/24/21:08
 */
@Getter
public enum UserStatusEnum {
    /**
     * 用户状态为激活
     */
    ACTIVE(1),

    /**
     * 用户动态冻结
     */
    IN_ACTIVE(0),
    ;

    private final int status;

    UserStatusEnum(int status) {
        this.status = status;
    }

}
