package common;

import exceptions.BaseException;

/**
 * 模仿spring 自定义一个断言接口
 *
 * @author jinyun liu
 * @date 2020/6/2
 */
public interface Assert {
    BaseException newException(Object... args);
}
