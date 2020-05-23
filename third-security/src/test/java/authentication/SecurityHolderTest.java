package authentication;

import cn.yun.go.SecurityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * @author jinyun liu
 * @date 2020/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class SecurityHolderTest {

    //
    @Test
    public void securityContextHolderTest() {
        // the heart of SpringAuthentication model is securityContextHolder,
        // which contains securityContext
        // stores the authenticated user details
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        TestingAuthenticationToken token = new TestingAuthenticationToken("username", "password", "ROLE_USER");
        context.setAuthentication(token);

        SecurityContextHolder.setContext(context);

        // access currently authenticated user
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String name = authentication.getName();
        System.out.println(name);


        // Authentication: principal, credentials, authorities.
        Object principal = authentication.getPrincipal();
        System.out.println(principal);

        Object credentials = authentication.getCredentials();
        System.out.println(credentials);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(System.out::println);
        // GrantedAuthority:  are high level permission - roles. one role has many permissions.
        assert 1 == 1;
    }

    @Test
    public void authenticationManagerTest() {
        // defines how Spring Security's Filters perform authentication. ProviderManager.
        // Provider manages many AuthenticationProviders.
        // While supporting multiple types of authentication and only exposing a single AuthenticationManager bean.
        // AuthenticationManager --> ProviderManager --> AuthenticationProvider
        // DaoAuthProvider JwtAuthProvider
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoder);
//        daoAuthenticationProvider.setUserDetailsPasswordService();
//        daoAuthenticationProvider.setUserDetailsService();
    }


    @Test
    public void authenticationEntryPointTest() {
        // authenticationEntryPoint performs a redirect to a login page.
    }

    @Test
    public void userNamePwdTest() {
        // form login, basic auth, digest auth.




    }

}
