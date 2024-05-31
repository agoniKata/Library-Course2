package com.example.arystan;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.arystan.DataBook.ConstBook;
import com.example.arystan.DataBook.DataBook;
import com.example.arystan.DataLibrary.ConstLibrary;
import com.example.arystan.DataLibrary.DataLibrary;
import com.example.arystan.DataPupil.DataPupil;
import com.example.arystan.Global.Global;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Library {
    @FXML
    private ImageView home;
    @FXML
    private TableView<Book> allBookTable;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, String> bookTitle;
    @FXML
    private TableColumn<Book,String> whom;
    @FXML
    private TableColumn<Book,String> quantity;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField quantityField1;

    @FXML
    private TextField quantityField2;

    @FXML
    private Hyperlink show;
    @FXML
    private TableColumn<LibraryHistory, String> book;
    @FXML
    private TableColumn<LibraryHistory, String> name;
    @FXML
    private TableColumn<LibraryHistory, String> userQuantity;
    @FXML
    private TableView<LibraryHistory> allTableHistory;
    @FXML
    void initialize() {
        show.setOnAction(event -> {
            tableSetHistory();
        });
    tableSetBook();
        Settings set = new Settings();
        set.image(home, "home.fxml");
    }
        public void tableSetBook(){
            DataBook dataBook = new DataBook();
            ObservableList<Book>bookList = FXCollections.observableArrayList();
            bookList.addAll(dataBook.getAllBook());

            bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            whom.setCellValueFactory(new PropertyValueFactory<>("who"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            allBookTable.setItems(bookList);
            System.out.println(Global.name);
            allBookTable.setOnMouseClicked(event -> {

                if (event.getClickCount() == 1) {
                        Book selectedBook = allBookTable.getSelectionModel().getSelectedItem();

                    String userName = Global.name;
                    String bookName = selectedBook.getTitle();
                    String quantity = quantityField.getText();


                    DataLibrary dataLibrary = new DataLibrary();
                    dataLibrary.signUpUser(userName,bookName,quantity);

                    int j =0;
                    Login login = new Login();
                    try {
                        j = login.CheckUser(Global.loginText,Global.loginPassword);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    int a =  Integer.parseInt(selectedBook.getQuantity()) - Integer.parseInt(quantity);
                    if(a>=0){


                        if(j==2 && selectedBook.getWho().equals("Pupil")){



                            String update ="UPDATE " + ConstBook.USER_TABLE + " SET " +
                                    ConstBook.BOOKS_QUANTITY + " = ? WHERE " + ConstBook.BOOKS_TITLE + " = ?";
                            try {
                                PreparedStatement preparedStatement = dataBook.getDbConnection().prepareStatement(update);
                                preparedStatement.setString(1  ,String.valueOf(a));
                                preparedStatement.setString(2, bookName);
                                preparedStatement.executeUpdate();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (j == 1) {
                            System.out.println(bookName);
                            String update ="UPDATE " + ConstBook.USER_TABLE + " SET " +
                                    ConstBook.BOOKS_QUANTITY + " = ? WHERE " + ConstBook.BOOKS_TITLE + " = ?";
                            try {
                                PreparedStatement preparedStatement = dataBook.getDbConnection().prepareStatement(update);
                                preparedStatement.setString(1  ,String.valueOf(a));
                                preparedStatement.setString(2, bookName);
                                preparedStatement.executeUpdate();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        } else{
                            quantityField1.setText("not enough quantity of books.  In the presence of " + selectedBook.getQuantity() + " or Check who this book is intended for");
                        }
                        }


                }
            });
        }
        public void tableSetHistory(){
            DataLibrary libraryController = new DataLibrary();
            List<LibraryHistory> history = libraryController.getAllHistory(Global.name);

            allTableHistory.getItems().clear();
            allTableHistory.getColumns().clear();
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            book.setCellValueFactory(new PropertyValueFactory<>("title"));
            userQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            allTableHistory.getColumns().addAll(name, book, userQuantity);
            allTableHistory.getItems().addAll(history);

            DataBook dataBook = new DataBook();

            DataLibrary dataLibrary = new DataLibrary();
            allTableHistory.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1){
                    LibraryHistory selectedHistory = allTableHistory.getSelectionModel().getSelectedItem();

                    int s = Integer.parseInt(selectedHistory.getQuantity()) - Integer.parseInt(quantityField2.getText());
                    String update = "UPDATE " + ConstLibrary.USER_TABLE + " SET " +
                            ConstLibrary.Library_quantity + " = ? WHERE " + ConstLibrary.Library_name+ " = ? AND "  + ConstLibrary.Library_book + " = ?";
                    try {
                        PreparedStatement prSt = dataLibrary.getDbConnection().prepareStatement(update);
                        prSt.setString(1,String.valueOf(s));
                        prSt.setString(2,selectedHistory.getName());
                        prSt.setString(3,selectedHistory.getTitle());
                        prSt.executeUpdate();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Book book1 = new Book();
                    book1.setTitle(selectedHistory.getTitle());
                       ResultSet resultSet = dataBook.getBook(book1);
                    try {

                        if(resultSet.next()){
                           int a = Integer.parseInt(resultSet.getString("quantity")) + Integer.parseInt(quantityField2.getText());
                            String up ="UPDATE " + ConstBook.USER_TABLE + " SET " +
                                    ConstBook.BOOKS_QUANTITY + " = ? WHERE " + ConstBook.BOOKS_TITLE + " = ?";
                            try {
                                PreparedStatement preparedStatement = dataBook.getDbConnection().prepareStatement(up);
                                preparedStatement.setString(1  ,String.valueOf(a));
                                preparedStatement.setString(2, selectedHistory.getTitle());
                                preparedStatement.executeUpdate();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                }
            });
        }


        }



