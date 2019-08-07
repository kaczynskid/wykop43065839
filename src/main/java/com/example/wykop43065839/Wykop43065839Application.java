package com.example.wykop43065839;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class Wykop43065839Application {

    public static void main(String[] args) {
        SpringApplication.run(Wykop43065839Application.class, args);
    }

}

@Configuration
class WebSecurityCfg extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/register", "/error").permitAll()
                        .anyRequest().authenticated()
                .and()
                        .formLogin()
                                .permitAll()
                .and()
                        .csrf()
                                .ignoringAntMatchers("/register");
    }
}

@RestController
@RequiredArgsConstructor
class RegistrationController {

    private final AccountService accountService;

    @GetMapping
    public String getForm() {
        return "{}";
    }

    @PostMapping("/register")
    public Account createUser(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

}

@Component
class AccountService {

    public Account createAccount(Account account) {
        return account;
    }
}

@Data
class Account {

    String name;
}