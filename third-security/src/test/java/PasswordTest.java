import cn.yun.go.SecurityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * All In-Memory authentications are unsafe.
 *
 * @author jinyun liu
 * @date 2020/5/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class PasswordTest {
    @Autowired
    private PasswordEncoder defaultDelegatingPasswordEncoder;

    @Autowired
    private DelegatingPasswordEncoder passwordEncoder;


    @Test
    public void userBuilderTest() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user, admin);

        // deprecated.
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user2 = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin2 = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        new InMemoryUserDetailsManager(user2, admin2);
    }



    @Test
    public void delegatingTest() {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("123456", encode);
        System.out.println(matches);
    }



    @Test
    public void setDefaultDelegatingPasswordEncoderTest() {
        // 加密穿过来的数据
        String encode = defaultDelegatingPasswordEncoder.encode("123456");
        System.out.println(encode);
        // 把穿过来的和已经加密的进行对比
        boolean matches = defaultDelegatingPasswordEncoder.matches("123456", encode);
        System.out.println(matches);
    }


    @Test
    public void defaultPasswordEncoderTest() {
        // deprecated. It is exposed in memory and in compiled source code,
        // For production, you should hash your passwords externally.
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
    }

    @Test
    public void bcryptTest() {
        // In order to make it more resistant to password cracking, bcrypt is deliberately slow.
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        String encode = bCryptPasswordEncoder.encode("123456");
        // $2a$16$TLgmwot1gXwCWzb/umEETOn2wgFZCNxpyqeb/DqAN1XMLL5GCxP7m differ every time.
        System.out.println(encode);
        // tuned to take about 1 s to verify a password.
        boolean matches = bCryptPasswordEncoder.matches("123456", encode);
        System.out.println(matches);
    }


    @Test
    public void argon2Test() {
        // 1s to verify the password.
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        String result = encoder.encode("myPassword");
        assertTrue(encoder.matches("myPassword", result));
    }


    // Pbkdf2PasswordEncoder  SCryptPasswordEncoder all 1s. requires large amounts of memory.


}
