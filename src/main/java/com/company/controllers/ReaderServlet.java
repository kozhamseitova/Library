package com.company.controllers;

import com.company.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ReaderServlet")
public class ReaderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReadersDB readersDB = ReadersDB.getInstance();
        PrintWriter writer = response.getWriter();

        String act = request.getParameter("act");
        switch (act){
            case "frommain":
                List<Readers> readers = readersDB.getAllReaders();
                request.setAttribute("readers", readers);
                request.getRequestDispatcher("jsp/readers.jsp").forward(request,response);
                break;
            case "add":
                int id = Integer.parseInt(request.getParameter("id"));
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String reg_date = request.getParameter("reg_date");

                Readers readers1 = new Readers();
                readers1.setId(id);
                readers1.setFname(fname);
                readers1.setLname(lname);
                readers1.setReg_date(reg_date);

                if(readersDB.addReaders(readers1)){
                    List<Readers> readersList = readersDB.getAllReaders();
                    request.setAttribute("readers", readersList);
                    request.getRequestDispatcher("jsp/readers.jsp").forward(request,response);
                }
                else{
                    writer.append("This id is already exists!");
                }

                break;
            case "change":
                int id1 = Integer.parseInt(request.getParameter("id"));
                String fname1 = request.getParameter("fname");
                String lname1 = request.getParameter("lname");
                String reg_date1 = request.getParameter("reg_date");

                readersDB.update(id1, fname1, lname1, reg_date1);
                List<Readers> readers2 = readersDB.getAllReaders();
                request.setAttribute("readers", readers2);
                request.getRequestDispatcher("jsp/readers.jsp").forward(request,response);

                break;
            case "remove":
                int id2 = Integer.parseInt(request.getParameter("id"));
                if(readersDB.check(id2)){
                    readersDB.remove(id2);
                    List<Readers> readers3 = readersDB.getAllReaders();
                    request.setAttribute("readers", readers3);
                    request.getRequestDispatcher("jsp/readers.jsp").forward(request, response);
                }
                else
                    writer.append("This reader have borrowed books!!!");

        }
    }
}
