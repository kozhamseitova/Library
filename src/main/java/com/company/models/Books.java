package com.company.models;

public class Books {
    private int isbn;
    private String name;
    private String author;
    private String genre;
    private String year;
    private int copy;

    public Books() {
    }

    public Books(int isbn, String name, String author, String genre, String year, int copy) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.copy = copy;
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

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }
}
