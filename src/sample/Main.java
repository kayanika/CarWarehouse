package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
     Stage stage1;
    Manufacturer manufacturer;
     boolean showManufacturerPage=false;
     boolean showViewerPage=false;
     NetworkUtil networkUtil;
    Viewer viewer;



    @Override
    public void start(Stage primaryStage) throws Exception{ stage1=primaryStage;
  //  connectToServer();
    showFrontPage();

    }
    public void showFrontPage()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FrontPage.fxml"));
        Parent root = loader.load();
        FrontPageController controller = loader.getController();

        controller.setMain(this);

        stage1.setTitle("Login Page");
        stage1.setScene(new Scene(root));
        stage1.show();
    }
    private void connectToServer() throws IOException {

        this.networkUtil = new NetworkUtil("localhost", 12444);

    }


    public void showLoginPage() throws Exception{

          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("loginPage.fxml"));
          Parent root = loader.load();
          LoginPageController controller = loader.getController();

          controller.setMain(this);

          stage1.setTitle("Login Page");
          stage1.setScene(new Scene(root));
          stage1.show();
      }
    public  void showManufacturerPage(List list) throws  Exception{

        showManufacturerPage=true;
        System.out.println("in manu");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Manufacturer.fxml"));
        Parent root = loader.load();
        ManufacturerController controller = loader.getController();
        controller.setMain(this);
        controller.load(list);
        controller.table.refresh();
        stage1.setTitle("Manufacturer Homepage");
        stage1.setScene(new Scene(root ));
        stage1.show();
    }
    public  void showViewerPage(List list) throws  Exception{

        showViewerPage=true;
        System.out.println("in manu");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Viewer.fxml"));
        Parent root = loader.load();
        ViewerController controller = loader.getController();
        controller.setMain(this);
        controller.load(list);
        controller.table.refresh();
        stage1.setTitle("Viewer Homepage");
        stage1.setScene(new Scene(root ));
        stage1.show();
    }
    public void showViewPage()throws Exception{

        viewer = new Viewer();
        connectToServer();
        new ViewerReadThread(this);
        viewer.setShowViewerList(true);
        networkUtil.getOos().reset();
        showViewerPage=true;
        this.networkUtil.write( viewer);
        viewer.setShowViewerList(false);
        showViewerPage=true;


    }
    public void login(String userName,String Password)throws Exception{

            Manufacturer manufacturer = new Manufacturer(userName, Password);
            connectToServer();
            new ManufacturerReadThread(this);

            this.networkUtil.write("hello SERVER");
            System.out.println("sending manufacturer");
        System.out.println(manufacturer);

         this.networkUtil.write( manufacturer);
         //showManufacturerPage=true;

    }
    public void ShowAddPage()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddCar.fxml"));
        Parent root = loader.load();
        AddController controller = loader.getController();
        controller.setMain(this);
        stage1.setTitle("Add New Car");
        stage1.setScene(new Scene(root ));
        stage1.show();


    }
    public void logout() throws Exception{

        showLoginPage();
    }




    public static void main(String[] args) {
        launch(args);
    }


    public void changeValue(String s)throws Exception{
        System.out.println(s);

        manufacturer.setEditCar(true);
        manufacturer.setEditField(s);
        networkUtil.getOos().reset();
        manufacturer.setShowManufacturerPage(true);
        networkUtil.write(manufacturer);
        manufacturer.setEditCar(false);

       // manufacturer.setShowManufacturerPage(true);
        showManufacturerPage=true;
    }
    public void deleteCar(String deleteCarReg)throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Car");
        alert.setHeaderText("Conformation of Removal :");
        alert.setContentText("Are you sure you want to delete this car?");

        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            manufacturer.setDeleteCar(true);
            manufacturer.setDeleteCarReg(deleteCarReg);

            networkUtil.getOos().reset();
            networkUtil.write(manufacturer);
            manufacturer.setDeleteCar(false);
            showManufacturerPage = true;
        } else {
            showManufactureUpdatePage();
        }
    }




    public void AddCar(Car carToAdd) throws Exception{

                manufacturer.setAddCar(true);
                manufacturer.setCarToadd(carToAdd);
                manufacturer.setShowManufacturerPage(true);
                networkUtil.getOos().reset();
                networkUtil.write(manufacturer);
        manufacturer.setAddCar(false);


        showManufacturerPage=true;

    }
    public  void searchCar(String  s)throws Exception{
        System.out.println();
        System.out.println("Sending Data "+s);
          viewer.setSearchCar(true);
          viewer.setSearchField(s);
          networkUtil.getOos().reset();
          networkUtil.write(viewer);




    }
    public void SearchCarAlert(Car foundCar){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Result");
        alert.setHeaderText("Car Information :");
        alert.setContentText(foundCar.toString());
        alert.showAndWait();
    }
    public void showErrorSearchAlert(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Search Result");
        alert.setHeaderText("Car Information :");
        alert.setContentText("No such Cars Found! ");
        alert.showAndWait();
    }
    public void showSearchBuyPage()throws  Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Search&BuyCar.fxml"));
        Parent root = loader.load();
        SearchBuyController controller = loader.getController();
        controller.setMain(this);
        stage1.setTitle("Viewer Options");
        stage1.setScene(new Scene(root ));
        stage1.show();

    }
    public void BuyCar(String BuyCarReg)throws Exception{
        viewer.setBuyCar(true);
        viewer.setBuyCarReg(BuyCarReg);
        networkUtil.getOos().reset();
        networkUtil.write(viewer);
       }
        public void SuccessfulBuyAlert(Car car){

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conformation message");
            alert.setHeaderText("CONGRATULATIONS :");
            alert.setContentText("Now you are the proud owner of A brand new "+car.getCarMake()+" !");
            alert.showAndWait();

    }
        public void showErrorBuyAlert(){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Buying Request Denied! ");
            alert.setHeaderText("Error Information :");
            alert.setContentText(" Sorry! We don't have this car in our stock right now!");
            alert.showAndWait();
        }

    public void showManufactureUpdatePage()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MnEditRemoveCar.fxml"));
        Parent root = loader.load();
        MnEditRemoveControllerCar controller = loader.getController();
        controller.setMain(this);
        stage1.setTitle("Updating Database...");
        stage1.setScene(new Scene(root ));
        stage1.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
}
