package mft.model.bl;

import mft.model.entity.Book;
import mft.model.repository.BookDA;

import java.util.List;

public class BookBL {
    public static void add(Book book) throws Exception{
        try(BookDA bookDA = new BookDA()){
            bookDA.add(book);
        }
    }
    public static void edit(Book book) throws Exception{
        try(BookDA bookDA = new BookDA()){
            bookDA.edit(book);
        }
    }
    public static void remove(int id) throws Exception{
        try(BookDA bookDA = new BookDA()){
            bookDA.remove(id);
        }
    }
    public static List<Book> findAll() throws Exception{
        try(BookDA bookDA = new BookDA()){
           return bookDA.findAll();
        }
    }

    public static List<Book> findAllAvailable() throws Exception{
        try(BookDA bookDA = new BookDA()){
            return bookDA.findAllAvailable();
        }
    }
    public static Book findById(int id) throws Exception{
        try(BookDA bookDA = new BookDA()){
           return bookDA.findById(id);
        }
    }

    public static void borrowBook(int bookId) throws Exception{
        try(BookDA bookDA = new BookDA()){
           bookDA.borrowBook(bookId);
        }
    }

    public static void returnBook(int bookId) throws Exception{
        try(BookDA bookDA = new BookDA()){
            bookDA.returnBook(bookId);
        }
    }
}
