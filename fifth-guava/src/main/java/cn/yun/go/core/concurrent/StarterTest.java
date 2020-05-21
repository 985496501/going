package cn.yun.go.core.concurrent;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.ThreadFactory;

/**
 * test guava library
 *
 * @author jinyun liu
 * @date 2020/5/20
 */
public class StarterTest {

    @Test
    public void aVoidTest() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("my thread factory-%d").build();

    }



}
