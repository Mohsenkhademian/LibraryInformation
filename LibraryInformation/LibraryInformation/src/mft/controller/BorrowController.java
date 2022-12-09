package mft.controller;

import mft.model.bl.BorrowBL;
import mft.model.bl.BorrowViewBL;
import mft.model.bl.MemberBL;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.BorrowView;
import mft.model.entity.Member;

import java.util.List;

public class BorrowController  {

    public static void add(Member member , Book book){
        if (member != null && book != null){
            try{
                BorrowBL.add(new Borrow(member,book));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void edit(Member member , Book book){
        if (member != null && book != null){
            try{
                BorrowBL.edit(new Borrow(member,book));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void returnBook(int borrowId){
        if (borrowId>0){
            try{
                BorrowBL.returnBook(borrowId);
            }
            catch(Exception e){
                System.out.println("Error");
            }
        }
    }


    public static List<BorrowView> findAllView(){
        try{
            return BorrowViewBL.findAll();
        }
        catch(Exception e){
            System.out.println("Error");
            return null;
        }
    }

    public static List<Borrow> findAll(){
        try{
            BorrowBL.findAll();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
