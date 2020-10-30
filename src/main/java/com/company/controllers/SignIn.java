package com.company.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignIn")
public class SignIn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String p = "employee";
        PrintWriter writer = response.getWriter();
        if(password.equals(p)){
            HttpSession session = request.getSession(true);
            session.setAttribute("password",password);
            session.setMaxInactiveInterval(60);

            Cookie ck=new Cookie("password",password);
            response.addCookie(ck);
            response.sendRedirect(request.getContextPath() + "/jsp/main.jsp");
        }
        else{
            writer.append("Incorrect password");
        }
    }
}
