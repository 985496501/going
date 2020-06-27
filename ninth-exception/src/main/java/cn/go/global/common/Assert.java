package cn.go.global.common;

/**
 * @author jinyun liu
 * @date 2020/6/3
 */
public interface Assert {
    BaseException newException(Object... o);

    BaseException newException(Throwable t, Object... args);

    default void assertNotNull(Object o) {
        if (o == null) {
            throw newException(null);
        }
    }


    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
    }

}
