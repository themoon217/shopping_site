//package filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter("/*") // Apply this filter to all URLs
//public class AuthenticationFilter implements Filter {
//    
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        
//        HttpSession session = httpRequest.getSession(false); // Do not create a new session if none exists
//        String loginURI = httpRequest.getContextPath() + "/login-or-signup.jsp";
//        
//        // Allow access to login page and signup page without authentication
//        boolean loggedIn = session != null || session.getAttribute("userID") != null;
//        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
//        boolean indexPage = httpRequest.getRequestURI().endsWith("/index.jsp");
//        boolean loginPage = httpRequest.getRequestURI().endsWith("/login.jsp");
//        boolean registerPage = httpRequest.getRequestURI().endsWith("/loginForm.jsp");
//        boolean signUp = httpRequest.getRequestURI().endsWith("/signup.jsp");
//        boolean loginNG = httpRequest.getRequestURI().endsWith("/loginNG.jsp");
//        boolean loginOrRegisterPage = httpRequest.getRequestURI().endsWith("/login-or-signup.jsp");
//        boolean loginOK = httpRequest.getRequestURI().endsWith("/success-start-shopping.jsp");
//        boolean trueIndex = httpRequest.getRequestURI().endsWith("/shop-logo.jsp");
//        boolean isCss = httpRequest.getRequestURI().endsWith(".css");
//        boolean isJpg = httpRequest.getRequestURI().endsWith(".jpg");
//        boolean isPng = httpRequest.getRequestURI().endsWith(".png");
//        boolean isSvg = httpRequest.getRequestURI().endsWith(".svg");
//        boolean isJS = httpRequest.getRequestURI().endsWith(".js");
//        boolean isJava = httpRequest.getRequestURI().endsWith(".java");
//        // Exclude specific pages from redirection
//        if (loggedIn || isCss || isSvg || isJpg || isPng || isJS || isJava ||
//        	loginRequest || indexPage || loginOrRegisterPage || signUp ||
//        	loginPage || loginNG ||loginOK ||  registerPage) {
//            chain.doFilter(request, response); // Continue to the next filter or servlet
//            System.out.println("not filtered");
//        } else {
//            httpResponse.sendRedirect(loginURI); // Redirect to login page
//            System.out.println("filtered");
//
//        }
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//        // Initialization code if needed
//    }
//
//    public void destroy() {
//        // Cleanup code if needed
//    }
//}