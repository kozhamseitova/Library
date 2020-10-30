package com.company.controllers;

import com.company.models.Books;
import com.company.models.BooksDB;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksDB booksDB = BooksDB.getInstance();
        PrintWriter writer = response.getWriter();

        String act = request.getParameter("act");
        switch (act){
            case "frommain":
                List<Books> books = booksDB.getAllBooks();
                request.setAttribute("books", books);
                request.getRequestDispatcher("jsp/books.jsp").forward(request,response);
                break;
            case "add":
                int isbn = Integer.parseInt(request.getParameter("isbn"));
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String genre = request.getParameter("genre");
                String year = request.getParameter("year");
                int copy = Integer.parseInt(request.getParameter("copy"));

                Books books1 = new Books();
                books1.setIsbn(isbn);
                books1.setName(name);
                books1.setAuthor(author);
                books1.setGenre(genre);
                books1.setYear(year);
                books1.setCopy(copy);


                if(booksDB.addBooks(books1)){
                    List<Books> books2 = booksDB.getAllBooks();
                    request.setAttribute("books", books2);
                    request.getRequestDispatcher("jsp/books.jsp").forward(request,response);
                }else{
                    writer.append("This ISBN is already exists");
                }

                break;
            case "change":
                int isbn1 = Integer.parseInt(request.getParameter("isbn"));
                String name1 = request.getParameter("name");
                String author1 = request.getParameter("author");
                String genre1 = request.getParameter("genre");
                String year1 = request.getParameter("year");
                int copy1 = Integer.parseInt(request.getParameter("copy"));

                booksDB.update(isbn1, name1, author1, genre1, year1, copy1);
                List<Books> books3 = booksDB.getAllBooks();
                request.setAttribute("books", books3);
                request.getRequestDispatcher("jsp/books.jsp").forward(request,response);

                break;
            case "remove":
                int isbn2 = Integer.parseInt(request.getParameter("isbn"));
                booksDB.remove(isbn2);
                List<Books> books4 = booksDB.getAllBooks();
                request.setAttribute("books", books4);
                request.getRequestDispatcher("jsp/books.jsp").forward(request, response);
        }
    }
}
