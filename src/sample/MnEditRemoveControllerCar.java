package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MnEditRemoveControllerCar {
    Main main;
    @FXML
    private TextField RegEdit;

    @FXML
    private TextField newCarPrice;

    @FXML
    private TextField newCarQuantity;

    @FXML
    private TextField DeleteCarReg;

    @FXML
    void DeleteButtonClicked(ActionEvent event)throws Exception {
        String carReg=DeleteCarReg.getText();
        DeleteCarReg.setText(null);
        main.deleteCar(carReg);
    }

    @FXML
    void SaveButtonClicked(ActionEvent event)throws Exception {
        //Car newCar=new Car()

        main.changeValue(RegEdit.getText()+" "+newCarPrice.getText()+" "+newCarQuantity.getText());
      //  main.showManufacturerPage=true;

    }
    public void setMain(Main main){
        this.main=main;
    }

    public void BackToMainPageClicked(ActionEvent actionEvent)throws Exception {
        main.showManufacturerPage=true;
        main.manufacturer.setShowManufacturerPage(true);
        main.networkUtil.getOos().reset();
        main.networkUtil.write(main.manufacturer);

    }
}
