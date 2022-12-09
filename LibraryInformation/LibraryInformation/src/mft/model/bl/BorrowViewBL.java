package mft.model.bl;

import mft.model.entity.Borrow;
import mft.model.entity.BorrowView;

import java.util.ArrayList;
import java.util.List;

public class BorrowViewBL {
    public static List<BorrowView> findAll() throws Exception {
        List<BorrowView> borrowViewList = new ArrayList<>();
        for (Borrow borrow : BorrowBL.findAll()){
            BorrowView borrowView = new BorrowView()
                    .setId(borrow.getId())
                    .setMember(borrow.getMember().getName()+" "+borrow.getMember().getFamily())
                    .setBook(borrow.getBook().getName()+" از : "+borrow.getBook().getWriter())
                    .setTimeStamp(String.valueOf(borrow.getBorromTime()))
                    .setReturned((borrow.isReturned())?"پس داده": "پس نداده");
            borrowViewList.add(borrowView);
        }
        return borrowViewList;
    }

}
