package com.swufe.javaee.request_response;


import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SelectBeerServlet", value = "/SelectBeerServlet")
public class SelectBeerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String body = request.getParameter("body");
        int amount = Integer.parseInt(request.getParameter("amount")) + 2;
        String[] sizes = request.getParameterValues("sizes");
        out.println("<html><body>");
        out.println("<h1>This is get response</h1>");
        if("".equals(body)||body==null){
            out.println("<p>body is null</p>");
        }else if("".equals(request.getParameter("amount"))||request.getParameter("amount")==null){
            out.println("<p>amount is null</p>");
        } else if(sizes.length==0){
            out.println("<p>sizes is null</p>");
        } else{
            out.println("<p>body: " + body + "</p>");
            out.println("<p>amount: " + amount + "</p>");
            for (String s : sizes) {
                out.println("<p>sizes: " + s + "</p>");
            }
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String body = request.getParameter("body");
        int amount = Integer.parseInt(request.getParameter("amount")) + 1;
        String[] sizes = request.getParameterValues("sizes");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>This is post response</h1>");
        out.println("<p>body: " + body + "</p>");
        out.println("<p>amount: " + amount + "</p>");
        for (String s : sizes) {
            out.println("<p>sizes: " + s + "</p>");
        }
        out.println("</body></html>");
    }
}
