package com.company.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class BooksDB {

    Connection connection = null;
    private static BooksDB instance = null;

    public static BooksDB getInstance(){
        if(instance == null){
            instance = new BooksDB();
        }
        return instance;
    }
    private BooksDB(){
        Context initialContext;
        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/books");
            connection = ds.getConnection();
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Books> getAllBooks()
    {
        try
        {
            String sql = "SELECT * FROM books";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Books> bookList = new ArrayList();
            while (resultSet.next()) {
                Books books = new Books(
                        resultSet.getInt("isbn"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"),
                        resultSet.getString("year"),
                        resultSet.getInt("copy")
                );
                bookList.add(books);
            }
            return bookList;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean addBooks(Books books) {
        try {
            String sql = "INSERT INTO books(isbn, name, author, genre, year, copy) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, books.getIsbn());
            stmt.setString(2, books.getName());
            stmt.setString(3, books.getAuthor());
            stmt.setString(4, books.getGenre());
            stmt.setString(5, books.getYear());
            stmt.setInt(6, books.getCopy());
            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(int isbn, String name, String author, String genre, String year, int copy)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update books set name=?, author=?, genre=?, year=?, copy=? where isbn=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, year);
            preparedStatement.setInt(5, copy);
            preparedStatement.setInt(6, isbn);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void remove(int isbn){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from books where isbn = ?");
            preparedStatement.setInt(1, isbn);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
