package com.company.controllers;

import com.company.models.BorrowedBooks;
import com.company.models.BorrowedBooksDB;
import com.company.models.Readers;
import com.company.models.ReadersDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BorrowedBookServlet")
public class BorrowedBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowedBooksDB borrowedBooksDB = BorrowedBooksDB.getInstance();

        String act = request.getParameter("act");
        switch (act){
            case "frommain":
                int id = Integer.parseInt(request.getParameter("id"));
                String idd = request.getParameter("id");
                Cookie cookie1 = new Cookie("id", idd);
                response.addCookie(cookie1);
                List<BorrowedBooks> borrowedBooks = borrowedBooksDB.getAllBorrowedBooks(id);
                request.setAttribute("borrowedBooks", borrowedBooks);
                request.getRequestDispatcher("jsp/borrowedBooks.jsp").forward(request,response);
                break;
            case "add":

                int isbn = Integer.parseInt(request.getParameter("isbn"));
                String borrow_date = request.getParameter("borrow_date");
                Cookie[] cookies = request.getCookies();
                String id12 = null;
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("id")) {
                            id12 = cookie.getValue();
                        }
                    }

                int id1 = Integer.parseInt(id12);
                borrowedBooksDB.addBooks(id1, isbn, borrow_date);
                List<BorrowedBooks> borrowedBooks1 = borrowedBooksDB.getAllBorrowedBooks(id1);
                request.setAttribute("borrowedBooks", borrowedBooks1);
                request.getRequestDispatcher("jsp/borrowedBooks.jsp").forward(request,response);

                break;
            case "remove":
                int id2 = Integer.parseInt(request.getParameter("id"));
                int isbn1 = Integer.parseInt(request.getParameter("isbn"));
                borrowedBooksDB.remove(id2, isbn1);
                List<BorrowedBooks> borrowedBooks2 = borrowedBooksDB.getAllBorrowedBooks(id2);
                request.setAttribute("borrowedBooks", borrowedBooks2);
                request.getRequestDispatcher("jsp/borrowedBooks.jsp").forward(request, response);
        }
    }
}
