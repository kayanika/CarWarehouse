package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
    private Main main;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void ResetButtonClicked(ActionEvent actionEvent) {
        username.setText(null);
        password.setText(null);

    }

    public void SubmitButtonClicked(ActionEvent actionEvent) throws Exception {
        String userName=username.getText();
        String passWord=password.getText();
        main.login(userName,passWord);
    }
    public void setMain(Main main){this.main=main;}
}
