package Utility;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/faces/admin/*"})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
             Boolean isAdmin= false;
            if(ses!=null){
                isAdmin= ((int) ses.getAttribute("isAdmin")) == 1;
                
            }
            if (
                    reqURI.indexOf("/login.xhtml") >= 0
                    ||isAdmin
                    || reqURI.contains("javax.faces.resource")
                    ) {

                chain.doFilter(request, response);
                
            }else if(ses.getAttribute("user")!=null){
               resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml"); 
            }
            
            else {
                resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            HttpServletResponse resp = (HttpServletResponse) response;
             HttpServletRequest reqt = (HttpServletRequest) request;
            resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
