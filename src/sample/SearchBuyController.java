package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchBuyController {
    Main main;
    public void setMain(Main main){
        this.main=main;
    }
    @FXML
    private TextField SearchCarID;

    @FXML
    private TextField searchCarMake;

    @FXML
    private TextField SearchCarModel;
    @FXML
    private TextField BuyCarReg;

    @FXML
    void BuyButtonClicked(ActionEvent event)throws Exception{
        String buyCar=BuyCarReg.getText();
        BuyCarReg.setText(null);
        main.BuyCar(buyCar);




    }

    @FXML
    void SearchByCarMake(ActionEvent event)throws Exception {


        String carMake=searchCarMake.getText();
       searchCarMake.setText(null);
        String carModel=SearchCarModel.getText();
        SearchCarModel.setText(null);

         main.searchCar(carMake+" "+carModel);


    }

    public void BackToMainPageClicked(ActionEvent actionEvent)throws Exception {

        main.viewer.setShowViewerList(true);
        main.networkUtil.getOos().reset();
        main.showViewerPage=true;
        main.networkUtil.write( main.viewer);
    }




    public void SearchByReg(ActionEvent actionEvent)throws Exception{
        String searchID=SearchCarID.getText();
        SearchCarID.setText(null);
        main.searchCar(searchID);
    }
}
