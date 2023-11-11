package sample;

import javafx.application.Platform;

import java.util.List;

public class ViewerReadThread implements Runnable{
    private Thread thread;
    private Main main;
    List<Car> carList;

    public ViewerReadThread(Main main) {
        System.out.println("Inside Constructor");
        this.main = main;

        this.thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {

        try {
            while (true) {


                synchronized (main.networkUtil) {
                    Object object = main.networkUtil.read();
                    System.out.println(object.toString());
                    if (object instanceof List) {
                        carList = (List) object;
                        if (main.showViewerPage) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        main.showViewerPage(carList);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            });
                        }


                    } else if (object instanceof Car) {
                        Car car = (Car) object;


                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (main.viewer.isSearchCar()) {

                                    main.SearchCarAlert(car);
                                    main.viewer.setSearchCar(false);

                                } else if (main.viewer.isBuyCar()) {
                                    main.SuccessfulBuyAlert(car);
                                    main.viewer.setBuyCar(false);
                                }

                            }
                        });


                    } else if (object instanceof String) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                if (main.viewer.isSearchCar()) {
                                    main.showErrorSearchAlert();
                                    main.viewer.setSearchCar(false);
                                } else if (main.viewer.isBuyCar()) {
                                    main.showErrorBuyAlert();
                                    main.viewer.setBuyCar(false);
                                }

                            }
                        });

                    }


                }
            }
        }
        catch (Exception e){
            System.out.println(e);

        }

    }


}
