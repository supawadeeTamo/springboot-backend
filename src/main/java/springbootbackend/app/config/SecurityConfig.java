package springbootbackend.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //การเข้ารหัสของ spring
    public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        //TODO:
    }

    @Override
    //เป็นการกำหนดว่าใครสามารถเข้าไปทำ API โดยที่ไม่ต้องทำการ Log-in ได้บ้างอะไรทำนองนี้ || api ที่เข้าได้โดยไม่ต้อง log-in มี /user/register
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable();
//              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //StatelessSession คือไม่เก็บ state คนที่เข้ามาขอ call
//              .and().authorizeRequests().antMatchers("/user/register", "/user/login").anonymous() //ถ้า api เป็น/user/register สามารถเข้ามาได้เลย
//              .anyRequest().authenticated(); //api อื่นๆถ้าเข้ามาต้อง login ก่อน
        }
}
