package sample;

import java.io.Serializable;

public class Viewer implements Serializable {

    private boolean searchCar=false;
    private boolean buyCar=false;
    private String searchField;
    private String BuyCarReg;
    private boolean showViewerList=false;



    public Viewer() {

    }

    public void setSearchCar(boolean searchCar) {
        this.searchCar = searchCar;
    }

    public void setBuyCar(boolean buyCar) {
        this.buyCar = buyCar;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public void setBuyCarReg(String buyCarReg) {
        BuyCarReg = buyCarReg;
    }

    public boolean isSearchCar() {
        return searchCar;
    }

    public boolean isBuyCar() {
        return buyCar;
    }

    public String getSearchField() {
        return searchField;
    }

    public String getBuyCarReg() {
        return BuyCarReg;
    }

    public void setShowViewerList(boolean showViewerList) {
        this.showViewerList = showViewerList;
    }

    public boolean isShowViewerList() {
        return showViewerList;
    }
}