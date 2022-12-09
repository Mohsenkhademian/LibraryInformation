package mft.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mft.model.entity.Book;
import mft.model.entity.BorrowView;
import mft.model.entity.Member;
import mft.model.repository.MemberDA;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Member selectedMember = null;

    private Book selectedBook = null;

    @FXML
    private Button memberSaveBtn , memberExitBtn , memberEditBtn , memberRemoveBtn , bookSaveBtn , bookExitBtn , bookEditBtn , bookRemoveBtn , selectMemberBtn , selectBookBtn , saveBorrowBtn , exitBorrowBtn ,returnBtn;

    @FXML
    private TextField memberNameTxt , memberFamilyTxt , memberIdTxt , bookNameTxt , bookWriterTxt , bookIdTxt , bookCountTxt;

    @FXML
    private TableView memberTbl , bookTbl , borrowViewTbl;

    @FXML
    private Label memberLbl , bookLbl , timeStampLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showMemberData();
        showBookData();
        showBorrowViewData();


        memberSaveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MemberController.add(memberNameTxt.getText(),memberFamilyTxt.getText());
                clearMemberTxt();
                Alert alert = new Alert(Alert.AlertType.INFORMATION , "عضو ذخیره شد");
                alert.show();
                showMemberData();
            }
        });

        memberEditBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MemberController.edit(Integer.parseInt(memberIdTxt.getText()),memberNameTxt.getText(),memberFamilyTxt.getText());
                clearMemberTxt();
                Alert alert = new Alert(Alert.AlertType.INFORMATION , "عضو ویرایش شد");
                alert.show();
                showMemberData();
            }
        });

       memberRemoveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               MemberController.remove(Integer.parseInt(memberIdTxt.getText()));
                clearMemberTxt();
                Alert alert = new Alert(Alert.AlertType.INFORMATION , "عضو حذف شد");
                alert.show();
                showMemberData();

            }
        });

        bookEditBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BookController.edit(Integer.parseInt(bookIdTxt.getId()),bookNameTxt.getText(),bookWriterTxt.getText(),Integer.parseInt(bookCountTxt.getId()));
                clearBookTxt();
                Alert alert = new Alert(Alert.AlertType.INFORMATION , "کتاب ویرایش شد");
                alert.show();
                showBookData();
            }
        });

        bookRemoveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BookController.remove(Integer.parseInt(bookIdTxt.getId()));
                clearBookTxt();
                Alert alert = new Alert(Alert.AlertType.INFORMATION , "کتاب حذف شد");
                alert.show();
                showBookData();
            }
        });

        memberExitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "آیا از خروج خود مطمئن هستید؟");
                if (alert.showAndWait().get()== ButtonType.OK) {
                    Platform.exit();
                }
            }
        });

        memberTbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
              Member member = (Member) memberTbl.getSelectionModel().getSelectedItem();
              memberIdTxt.setText(String.valueOf(member.getId()));
              memberNameTxt.setText(member.getName());
              memberFamilyTxt.setText(member.getFamily());
           }
       });

        bookSaveBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               BookController.add(bookNameTxt.getText() , bookWriterTxt.getText() , Integer.parseInt(bookCountTxt.getText()));
               clearBookTxt();
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "کتاب ذخیره شد");
               alert.show();
               showBookData();
           }
       });

        bookExitBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "آیا از خروج خود مطمئن هستید؟");
               if(alert.showAndWait().get()== ButtonType.OK){
                   Platform.exit();
               }
           }
       });

        exitBorrowBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"آیا از خروج خود مطمئن هستید؟");
               if(alert.showAndWait().get()==ButtonType.OK){
                   Platform.exit();
               }
           }
       });

        saveBorrowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorrowController.add(selectedMember, selectedBook);
                selectedMember = null;
                selectedBook = null;
                memberLbl.setText("not selected");
                bookLbl.setText("not selected");
                timeStampLbl.setText(" ");
                showBorrowViewData();
            }
        });

        bookTbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               Book book = (Book) bookTbl.getSelectionModel().getSelectedItem();
               bookIdTxt.setText(String.valueOf(book.getId()));
               bookNameTxt.setText(book.getName());
               bookWriterTxt.setText(book.getWriter());
               bookCountTxt.setText(String.valueOf(book.getCount()));
           }
       });

        selectMemberBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedMember = (Member) memberTbl.getSelectionModel().getSelectedItem();
                memberLbl.setText(String.valueOf(selectedMember));
                timeStampLbl.setText(String.valueOf(LocalDateTime.now()));
            }
        });

        selectBookBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedBook = (Book) bookTbl.getSelectionModel().getSelectedItem();
                bookLbl.setText(String.valueOf(selectedBook));
                timeStampLbl.setText(String.valueOf(LocalDateTime.now()));
            }
        });

        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorrowView borrowView = (BorrowView) borrowViewTbl.getSelectionModel().getSelectedItem();
                BorrowController.returnBook(borrowView.getId());
                showBorrowViewData();
            }
        });

    }

    private void showMemberData(){
        memberTbl.getItems().clear();
        memberTbl.getColumns().clear();

        ObservableList<Member> members = FXCollections.observableList(MemberController.findAll());

        TableColumn<Member,Integer> idCol = new TableColumn<>("شماره عضویت :");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Member,String> nameCol = new TableColumn<>("نام");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member,String> familyCol = new TableColumn<>("نام خانوادکی");
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));


        memberTbl.getColumns().addAll(idCol,nameCol,familyCol);
        memberTbl.setItems(members);
    }

    private void clearMemberTxt(){
        memberIdTxt.clear();
        memberNameTxt.clear();
        memberFamilyTxt.clear();
    }

    private void showBookData(){
        bookTbl.getItems().clear();
        bookTbl.getColumns().clear();

        ObservableList<Book> books = FXCollections.observableList(BookController.findAll());

        TableColumn<Book , Integer> idCol = new TableColumn<>("شماره کتاب");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book , String> nameCol = new TableColumn<>("نام کتاب");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book , String> writerCol = new TableColumn<>("نویسنده");
        writerCol.setCellValueFactory(new PropertyValueFactory<>("writer"));

        TableColumn<Book , Integer> countCol = new TableColumn<>("تعداد کتاب");
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        bookTbl.getColumns().addAll(idCol,nameCol,writerCol,countCol);
        bookTbl.setItems(books);


    }

    private void clearBookTxt(){
        bookIdTxt.clear();
        bookNameTxt.clear();
        bookWriterTxt.clear();
        bookCountTxt.clear();
    }

    private void showBorrowViewData() {
        borrowViewTbl.getItems().clear();
        borrowViewTbl.getColumns().clear();
        ObservableList<BorrowView> borrowViewList = FXCollections.observableList(BorrowController.findAllView());

        TableColumn<BorrowView, Integer> idCol = new TableColumn<>("کد شناسه");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<BorrowView, String> memberCol = new TableColumn<>("عضو");
        memberCol.setCellValueFactory(new PropertyValueFactory<>("member"));

        TableColumn<BorrowView, String> bookCol = new TableColumn<>("کتاب");
        bookCol.setCellValueFactory(new PropertyValueFactory<>("book"));

        TableColumn<BorrowView, String> timeStampCol = new TableColumn<>("تاریخ امانت");
        timeStampCol.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

        TableColumn<BorrowView, String> statusCol = new TableColumn<>("وضعیت");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("returned"));

        borrowViewTbl.getColumns().addAll(idCol, memberCol, bookCol, timeStampCol, statusCol);
        borrowViewTbl.setItems(borrowViewList);
    }
}
