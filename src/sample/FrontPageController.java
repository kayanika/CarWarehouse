package sample;

import javafx.event.ActionEvent;

public class FrontPageController {
    private Main main;
    public void ManufacturerButtonClicked (ActionEvent actionEvent) throws Exception {
        main.showLoginPage();

    }

    public void ViewerButtonClicked(ActionEvent actionEvent)throws Exception {
        main.showViewPage();
    }

    public void setMain(Main main) {
        this.main=main;
    }
}
