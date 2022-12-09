package mft.model.bl;

import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.repository.BookDA;
import mft.model.repository.BorrowDA;

import java.util.List;


public class BorrowBL {
    public static void add(Borrow borrow) throws Exception{
        try(BorrowDA borrowDA = new BorrowDA()) {
            if (borrowDA.memberBorrowedBook(borrow.getMember().getId()) < 2) {
                borrowDA.add(borrow);
                BookBL.borrowBook(borrow.getBook().getId());
            }
        }
    }

    public static void edit(Borrow borrow) throws Exception{
        try(BorrowDA borrowDA = new BorrowDA()){
            borrowDA.edit(borrow);
        }
    }

    public static void remove(int id) throws Exception{
        try(BorrowDA borrowDA = new BorrowDA()){
            borrowDA.remove(id);
        }
    }

    public static List<Borrow> findAll() throws Exception{
        try(BorrowDA borrowDA = new BorrowDA()){
            return borrowDA.findAll();
        }
    }

    public static Borrow findById(int id) throws Exception{
        try(BorrowDA borrowDA = new BorrowDA()){
            return borrowDA.findById(id);
        }
    }

    public static void returnBook(int borrowId) throws Exception {
        try (BorrowDA borrowDA = new BorrowDA()) {
            borrowDA.returnBook(borrowId);
            BookBL.returnBook(borrowDA.findById(borrowId).getBook().getId());
        }
    }



}
