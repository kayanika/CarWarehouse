package sample;

import java.io.Serializable;

public class Manufacturer implements Serializable {
    private    String userName;
      private String EditField;

    public void setEditField(String editField) {
        System.out.println("setting edifiel");
        EditField = editField;
    }

    public String getEditField() {
        System.out.println("setting edifiel");
        return EditField;
    }
// private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Manufacturer{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

   private String password;
     private  boolean Status =false;
    private boolean showHomePage =false;
    private  boolean EditCar=false;
    private  boolean DeleteCar=false;
    private boolean addCar=false;
    private String deleteCarReg;
    private boolean showManufacturerPage=false;

    public void setCarToadd(Car carToadd) {
        this.carToadd = carToadd;
    }

    public Car getCarToadd() {
        return carToadd;
    }

    private Car carToadd;

    public void setShowManufacturerPage(boolean showManufacturerPage) {
        this.showManufacturerPage = showManufacturerPage;
    }

    public boolean isShowManufacturerPage() {
        return showManufacturerPage;
    }

    public void setDeleteCarReg(String deleteCarReg) {
        this.deleteCarReg = deleteCarReg;
    }

    public String getDeleteCarReg() {
        return deleteCarReg;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.Status = status;
    }

    public void setShowHomePage(boolean showHomePage) {
        this.showHomePage = showHomePage;
    }

    public void setEditCar(boolean editCar) {
        EditCar = editCar;
    }

    public void setDeleteCar(boolean deleteCar) {
        DeleteCar = deleteCar;
    }

    public void setAddCar(boolean addCar) {
        this.addCar = addCar;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return Status;
    }

    public boolean isShowHomePage() {
        return showHomePage;
    }

    public boolean isEditCar() {
        return EditCar;
    }

    public boolean isDeleteCar() {
        return DeleteCar;
    }

    public boolean isAddCar() {
        return addCar;
    }

    public Manufacturer(String userName, String Password) {
         this.userName=userName;
         this.password =Password;

    }











}
