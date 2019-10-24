package escuelaing.edu.arep.LabSecurity.config;

import escuelaing.edu.arep.LabSecurity.services.AuthorizationService;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserFilter implements Filter {

    private final AuthorizationService authorizationService;

    public UserFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            String apiKey = req.getHeader("api-key");
            if (req.getMethod().equals("GET") && (apiKey == null || !authorizationService.isAuthorizeAsUser(apiKey))) {
                resp.reset();
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                chain.doFilter(req, resp);
            }
        }
    }
}