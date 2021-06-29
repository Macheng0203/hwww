package com.swufe.javaee.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static com.swufe.javaee.session.utils.Readtext.readFileContent;

@WebServlet(name = "SignInServlet", value = "/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("sign-in.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("inputName");
        String inputPassword = req.getParameter("inputPassword");
        String basePath = req.getRealPath("/source/sample.txt");
        System.out.println(basePath);
        if(readFileContent(name,basePath)==null||!readFileContent(name,basePath).equals(inputPassword)){
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>wrong password, no such user.</h1>");
            out.println("</body></html>");
        }else {
            String remember = req.getParameter("remember");     //获取是否打钩
            Cookie nameCookie = new Cookie("name", name);
            Cookie passwordCookie = new Cookie("password", inputPassword);
            nameCookie.setPath(req.getContextPath()+"/");      //设置Cookie的有效路径
            passwordCookie.setPath(req.getContextPath()+"/");//设置Cookie的有效路径
            if(remember != null && "yes".equals(remember)){  //有记住我，就设置cookie的保存时间
                nameCookie.setMaxAge(7*24*60*60);
                passwordCookie.setMaxAge(7*24*60*60);
            }else{//没有记住我，设置cookie的时间为0
                nameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
            }
            resp.addCookie(nameCookie);
            resp.addCookie(passwordCookie);
            req.getSession().setAttribute("user", name);
            resp.sendRedirect("index");
        }
    }
}
