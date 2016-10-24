package com.lockbur.book.manager.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String defaultFailureUrl;
    private boolean forwardToDestination = false;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        logger.info("exception {}",exception.getMessage());
        String targetUrl = null;

        request.getSession().invalidate();

        targetUrl = request.getContextPath() + "/login" + "?msg=" + exception.getMessage();
        if (forwardToDestination) {
            request.getRequestDispatcher(targetUrl).forward(request, response);
        } else {
            response.sendRedirect(targetUrl);
        }
    }

    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }

    public boolean isForwardToDestination() {
        return forwardToDestination;
    }

    public void setForwardToDestination(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }
}
