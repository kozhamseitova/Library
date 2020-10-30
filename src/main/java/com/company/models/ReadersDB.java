package com.company.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadersDB {

    Connection connection = null;
    private static ReadersDB instance = null;

    public static ReadersDB getInstance(){
        if(instance == null){
            instance = new ReadersDB();
        }
        return instance;
    }
    private ReadersDB(){
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

    public ArrayList<Readers> getAllReaders()
    {
        try
        {
            String sql = "SELECT * FROM readers";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Readers> readersArrayList = new ArrayList();
            while (resultSet.next()) {
                Readers readers = new Readers(
                        resultSet.getInt("id"),
                        resultSet.getString("fname"),
                        resultSet.getString("lname"),
                        resultSet.getString("reg_date")
                );
                readersArrayList.add(readers);
            }
            return readersArrayList;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean addReaders(Readers readers) {
        try {
            String sql = "INSERT INTO readers(id, fname, lname, reg_date) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, readers.getId());
            stmt.setString(2, readers.getFname());
            stmt.setString(3, readers.getLname());
            stmt.setString(4, readers.getReg_date());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(int id, String fname, String lname, String reg_date)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update readers set fname=?, lname=?, reg_date=? where id=?");
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, reg_date);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void remove(int id){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from readers where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public  ArrayList<String> check1(int id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select name from borrowed_books where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> names = new ArrayList<>();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                names.add(name);
            }
            return names;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public boolean check(int id){
        ArrayList<String> names = check1(id);
        if(names.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
