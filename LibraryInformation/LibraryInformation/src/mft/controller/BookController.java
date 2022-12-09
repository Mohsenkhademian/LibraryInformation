package mft.controller;


import mft.model.bl.BookBL;
import mft.model.bl.MemberBL;
import mft.model.entity.Book;
import mft.model.entity.Member;

import java.util.List;

public class BookController {

    public static void add(String name ,String writer , int count){
        if(!name.isEmpty() && !writer.isEmpty() && count>0){
            try {
                BookBL.add(new Book(name ,writer ,count));
                System.out.println("Book Saved");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void edit(int id, String name, String writer ,int count) {
        if (!name.isEmpty() && !writer.isEmpty() && id > 0) {
            try {
               BookBL.edit(new Book(id, name, writer ,count));
                System.out.println("Book Edited");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void remove(int id){
        if(id > 0){
            try {
                BookBL.remove(id);
                System.out.println("Book Removed");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public static List<Book> findAllAvailable() {
        try {
            return BookBL.findAllAvailable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Book> findAll() {
        try {
            return BookBL.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Book findById(int id) {
        try {
            return BookBL.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
