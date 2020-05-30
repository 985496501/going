package authentication;

import cn.yun.go.SecurityApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * jdbc auth test
 *
 * @author jinyun liu
 * @date 2020/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class JDBCAuthTest {
    // JdbcDaoImpl impl UserDetailService to provide support for username/password based authentication
    // that is retrieved using JDBC. JdbcUserDetailsManager(impl) provide UserDetails.
    // UserDetails based authentication is used by SpringSecurity when it is configured to accept a user/pwd for authentication.
    // UserDetails is returned by the UserDetailsService.












}
