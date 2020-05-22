package cn.yun.go.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinyun liu
 * @date 2020/5/22
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * create default delegatingPasswordEncoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder defaultDelegatingPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

   // @Bean
    public Argon2PasswordEncoder argon2PasswordEncoder() {
        return new Argon2PasswordEncoder();
    }

    //@Bean()
    public DelegatingPasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>(1);
        encoders.put("argon2", new Argon2PasswordEncoder());
        return new DelegatingPasswordEncoder("argon2", encoders);
    }

    /**
     * create custom delegatingPasswordEncoder
     *
     * @return
     */
    //@Bean
    public PasswordEncoder customizedDelegatingPasswordEncoder() {
        // start with $2a$
        String idForEncode = "bcrypt";
        Map encoders = new HashMap<>(5);
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

}
