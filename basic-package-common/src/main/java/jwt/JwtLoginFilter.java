package jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import jwt.TokenAuthenticationService;
import jwt.authority.UserDetailsServiceImpl;
import jwt.authority.entity.SysUser;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();
    String secret;
    long expirationTime;

    public JwtLoginFilter(String url, AuthenticationManager authManager, String secret, long expirationTime) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        SysUser creds = new ObjectMapper()
                .readValue(req.getInputStream(), SysUser.class);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(creds.getUsername());

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        userDetails.getAuthorities()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        JwtResult jwtResult = TokenAuthenticationService.addAuthentication(res, auth, this.secret, this.expirationTime);
        String jwtResultJson = new ObjectMapper().writeValueAsString(jwtResult);

        res.setContentType("application/json");
        PrintWriter printWriter = res.getWriter();
        printWriter.print(jwtResultJson);
        printWriter.flush();
    }

   // @ApiModel(value = "授权信息")
    public static class JwtResult {
      //  @ApiModelProperty(value = "令牌", required = true, example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJBRE1JTiJ9LHsiYXV0aG9yaXR5IjoiQUNUVUFUT1IifV0sImlhdCI6MTUwODEyNDEzNiwiZXhwIjoxNTA4MjEwNTM2fQ.PCongdNHDm6t6DD1tFJdzDx87qPbjgtBXUEf3sdc5bzPrwjD7FUFBSwOy28nkhcsdWNgJMswJ2v3ZWk3K7mcgQ")
        private String token;
    //    @ApiModelProperty(value = "令牌类型", required = true, example = "Bearer")
        private String type;

        public JwtResult(String token, String type) {
            this.token = token;
            this.type = type;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
