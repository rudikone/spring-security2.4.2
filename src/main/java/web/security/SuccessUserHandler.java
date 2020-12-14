package web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

//
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        handle(request, response, authentication);
//        clearAuthenticationAttributes(request);
//    }
//
//    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        String targetUrl = determineTargetUrl(authentication);
//        redirectStrategy.sendRedirect(request, response, targetUrl);
//    }
//
//    protected String determineTargetUrl(Authentication authentication) {
//        boolean isUser = false;
//        boolean isAdmin = false;
//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//
//        if (roles.contains("ROLE_USER")) {
//                isUser = true;
//            } else if (roles.contains("ROLE_ADMIN")) {
//                isAdmin = true;
//            }
//
//
//        if (isUser) {
//            return "/user";
//        } else if (isAdmin) {
//            return "/index";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return;
//        }
//        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//    }
//
//    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
//        this.redirectStrategy = redirectStrategy;
//    }
//
//    protected RedirectStrategy getRedirectStrategy() {
//        return redirectStrategy;
//    }

    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/users");
        } else {
            httpServletResponse.sendRedirect("/user");
            System.out.println(roles);
        }
    }
}
