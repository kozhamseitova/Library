package com.company.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowedBooksDB {


    Connection connection = null;
    private static BorrowedBooksDB instance = null;

    public static BorrowedBooksDB getInstance(){
        if(instance == null){
            instance = new BorrowedBooksDB();
        }
        return instance;
    }
    private BorrowedBooksDB(){
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

    public ArrayList<BorrowedBooks> getAllBorrowedBooks(int id)
    {
        try
        {
            String sql = "SELECT * FROM borrowed_books where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<BorrowedBooks> borrowedBooksArrayList = new ArrayList();
            while (resultSet.next()) {
                BorrowedBooks borrowedBooks = new BorrowedBooks(
                        resultSet.getInt("id"),
                        resultSet.getInt("isbn"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"),
                        resultSet.getString("year"),
                        resultSet.getString("borrow_date")
                );
                borrowedBooksArrayList.add(borrowedBooks);
            }
            return borrowedBooksArrayList;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public void addBooks1(int id, int isbn, String borrow_date) {
        try {
            String sql = "INSERT INTO borrowed_books(id, isbn, name, author, genre, year, borrow_date) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            Books books = getBookByIsbn(isbn);
            stmt.setInt(1, id);
            stmt.setInt(2, books.getIsbn());
            stmt.setString(3, books.getName());
            stmt.setString(4, books.getAuthor());
            stmt.setString(5, books.getGenre());
            stmt.setString(6, books.getYear());
            stmt.setString(7, borrow_date);
            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void remove(int id, int isbn){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from borrowed_books where id = ? and isbn = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, isbn);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public Books getBookByIsbn(int isbn){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from books where isbn = ?");
            preparedStatement.setInt(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            Books books = new Books();
            while (resultSet.next()) {
                books = new Books(
                        resultSet.getInt("isbn"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"),
                        resultSet.getString("year"),
                        resultSet.getInt("copy")
                );
            }
            return books;
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean check(int isbn){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select copy from books where isbn = ? limit 1");
            preparedStatement.setInt(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            int copy = 0;
            while(resultSet.next()){
                copy = resultSet.getInt("copy");
            }
            if(copy>0){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void update(int isbn){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update books set copy=copy-1" + "where isbn=?");
            preparedStatement.setInt(1, isbn);
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addBooks(int id, int isbn, String borrow_date){
        if(check(isbn)){
            update(isbn);
            addBooks1(id, isbn, borrow_date);
        }
    }
}
