/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infs3605;

import static infs3605.Database.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jenneyle
 */
public class InsertStaffController implements Initializable {

    @FXML
    Button back;
    @FXML
    Button submit;

    @FXML
    ComboBox<String> insertStaffType;
    @FXML
    ComboBox<Integer> insertStaffCapacity;
    @FXML
    private TextField insertStaffFname;
    @FXML
    private TextField insertStaffLname;
    @FXML
    private TextField insertStaffZid;
    @FXML
    private Label updateMsg;

    PageSwitchHelper pageSwitcher = new PageSwitchHelper();

    public void handleBackButton(ActionEvent event) throws IOException {
        pageSwitcher.switcher(event, "StaffTable.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> staffTypeList = FXCollections.observableArrayList("Education", "Research");
        insertStaffType.setItems(staffTypeList);

        ObservableList<Integer> staffCapacityList = FXCollections.observableArrayList(3, 6);
        insertStaffCapacity.setItems(staffCapacityList);
        updateMsg.setVisible(false);
    }

    public void handleInsertBtn(ActionEvent event) throws IOException, SQLException {

//        ObservableList<String> staffTypeList = FXCollections.observableArrayList("Education", "Research");
//        insertStaffType.setItems(staffTypeList);
        String type = insertStaffType.getValue();
//        ObservableList<Integer> staffCapacityList = FXCollections.observableArrayList(3, 6);
//        insertStaffCapacity.setItems(staffCapacityList);
        Integer capacity = insertStaffCapacity.getValue();
        String insertFname = insertStaffFname.getText();
        String insertLname = insertStaffLname.getText();
        String insertZid = insertStaffZid.getText();

        Statement st = conn.createStatement();
        try {
            String insertData = ("INSERT INTO STAFF (staff_id, Fname, Lname, staff_type, staff_capacity)" + " VALUES ('" + insertZid + "','"
                    + insertFname + "', '" + insertLname + "', '" + type + "', '" + capacity + "')");

            st.execute(insertData);
            System.out.println("Submitted");
            updateMsg.setText("Successfully submitted");
            updateMsg.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}