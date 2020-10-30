package com.company.models;

public class BorrowedBooks {
    private int id;
    private int isbn;
    private String name;
    private String author;
    private String genre;
    private String year;
    private String borrow_date;

    public BorrowedBooks() {
    }

    public BorrowedBooks(int id, int isbn, String name, String author, String genre, String year, String borrow_date) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.borrow_date = borrow_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }
}
