package com.evan.wj.interceptor;

import com.evan.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.err.println("session:" + session);
        String contextPath = session.getServletContext().getContextPath();
        System.err.println("contextPath:" + contextPath);
        String[] requireAuthPages = new String[]{
                "index",
        };
        String uri = request.getRequestURI();
        System.err.println("uri:" + uri);
        uri = StringUtils.remove(uri, contextPath + "/");
        String page = uri;
        System.err.println("page:" + page);
        if (beginWith(page, requireAuthPages)) {
            User user = (User) session.getAttribute("user");
            if (Objects.isNull(user)) {
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages) {
        boolean result = false;
        for (String requireAuthPage : requireAuthPages) {
            if (StringUtils.startsWith(page, requireAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
