package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddController {

    private Main main;

    @FXML
    private TextField carReg;

    @FXML
    private TextField YearMade;

    @FXML
    private TextField Color;

    @FXML
    private TextField Color2;

    @FXML
    private TextField Color3;

    @FXML
    private TextField CarMake;

    @FXML
    private TextField CarModel;

    @FXML
    private TextField Price;

    @FXML
    private TextField quantity;
    public void setMain(Main main){
        this.main=main;
    }

    public void AddClicked(ActionEvent actionEvent)throws Exception {
        if (carReg.getText().equals("") | YearMade.getText().equals("") | Color.getText().equals("") | CarMake.getText().equals("") | CarModel.getText().equals("") | Price.getText().equals("") | quantity.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Adding Car Error!!");
            alert.setHeaderText("Attempt to add car unsuccessful!");
            alert.setContentText("Required fields missing information! ");
            alert.showAndWait();

        }
        else{


            try{
                Car newCar = new Car(carReg.getText(), Integer.parseInt(YearMade.getText()), Color.getText(), Color2.getText(), Color3.getText(), CarMake.getText(), CarModel.getText(), Integer.parseInt(Price.getText()), Integer.parseInt(quantity.getText()));
                main.AddCar(newCar);
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Adding Car Error!!");
                alert.setHeaderText("Attempt to add car unsuccessful!");
                alert.setContentText("YearMade, Price and Quantity Should all be numbers! ");
                alert.showAndWait();
            }
        }
    }

    public void BackToMainPageClicked(ActionEvent actionEvent) throws Exception{
        main.showManufacturerPage=true;
        main.manufacturer.setShowManufacturerPage(true);
        main.networkUtil.getOos().reset();
        main.networkUtil.write(main.manufacturer);
    }
}
