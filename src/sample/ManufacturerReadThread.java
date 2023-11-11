package sample;

import javafx.application.Platform;

import java.util.List;

public class ManufacturerReadThread implements Runnable{

    private Thread thread;
    private Main main;
    List<Car> carList;

    public ManufacturerReadThread(Main main) {
        System.out.println("Inside Constructor");
        this.main = main;

        this.thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {

        try {
            while (true) {

                //Object object=main.networkUtil.read();

                    Object object = main.networkUtil.read();
                    if (object instanceof List) {
                        carList = (List) object;
                        if (main.showManufacturerPage) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        main.showManufacturerPage(carList);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            });
                        }

                    } else if (object instanceof Manufacturer) {
                        Manufacturer manufacturer = (Manufacturer) object;
                        if (manufacturer.isStatus()) {

                            main.manufacturer=manufacturer;
                            main.showManufacturerPage=true;
                        }
                        else {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        //System.out.println("the credentials arewrong");
                                        main.showAlert();
                                    } catch (Exception e) {
                                        e.printStackTrace();
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
