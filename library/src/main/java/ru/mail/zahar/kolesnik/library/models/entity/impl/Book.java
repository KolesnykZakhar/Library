package ru.mail.zahar.kolesnik.library.models.entity.impl;


import ru.mail.zahar.kolesnik.library.models.entity.Person;

import java.sql.Timestamp;

public class Book {

    public static int timeToRead = 7;
    public int idBook;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int isbn;

    public String bookName;

    public String category;

    public String description;

    public String authorName;

    public String authorPatronymic;

    public String authorSurname;

    public Timestamp issuedDate;

    public Timestamp returnedDate;

    public Person issued;

    public Book(int idBook, int isbn, String bookName, String category, String description, String authorName, String authorPatronymic,
                String authorSurname, Timestamp issuedDate, Timestamp returnedDate, Person issued) {
        this.idBook=idBook;
        this.isbn = isbn;
        this.bookName = bookName;
        this.category = category;
        this.description = description;
        this.authorName = authorName;
        this.authorPatronymic = authorPatronymic;
        this.authorSurname = authorSurname;
        this.issuedDate = issuedDate;
        this.returnedDate = returnedDate;
        this.issued = issued;
    }

    public String info(){
        return isbn+", \""+bookName+"\" ["+category+"] - "+authorName+" "+authorSurname;
    }
    @Override
    public String toString() {
        String issued = "never issued";
        if (issuedDate != null) {
            issued=issuedDate.toString();
        }
        String returnedDate = "never issued";
        String returned = "";
        if (this.issued != null) {
            issued = issuedDate.toString();
            returnedDate = "deadline: " + Timestamp.valueOf(this.issuedDate.toLocalDateTime().plusDays(timeToRead));
            returned = "not returned";
        }

        return "\nID: " + isbn +
                "\nName: " + bookName +
                "\nCategory: " + category +
                "\nDescription: " + description +
                "\nAuthor's name: " + authorName +
                "\nAuthor's patronymic: " + authorPatronymic +
                "\nAuthor's Surname: " + authorSurname +
                "\nissued: " + issued +
                "\nReturned Date: " + returnedDate +
                "\nStatus: " + returned;
    }

    public static int getTimeToRead() {
        return timeToRead;
    }

    public static void setTimeToRead(int timeToRead) {
        Book.timeToRead = timeToRead;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }

    public void setAuthorPatronymic(String authorPatronymic) {
        this.authorPatronymic = authorPatronymic;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public Timestamp getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Timestamp issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getReturnedDate() {
        return returnedDate.toLocalDateTime().toLocalDate().toString();
    }

    public void setReturnedDate(Timestamp returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Person getIssued() {
        return issued;
    }

    public void setIssued(Person issued) {
        this.issued = issued;
    }

}
