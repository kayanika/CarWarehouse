package sample;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ServerReadThread implements Runnable{

    private Thread thr;
    private NetworkUtil networkUtil;
    public HashMap<String, String> ManufacturerInfo;
    //CarDataBase carDataBase;

    public ServerReadThread(NetworkUtil networkUtil,HashMap<String, String> map) {
        this.networkUtil = networkUtil;
       // this.carDataBase=carDataBase;
        this.thr = new Thread(this);
        thr.start();
        ManufacturerInfo=map;
    }



    public void run() {

        try {

            while (true) {
                System.out.println("in readthread");

                    Object object = networkUtil.read();
                    if (object != null) {
                        if (object instanceof Manufacturer) {

                            Manufacturer manufacturer = (Manufacturer) object;

                            if (!manufacturer.isStatus()) {
                                String password = ManufacturerInfo.get(manufacturer.getUserName());
                                System.out.println(password);
                                manufacturer.setStatus(manufacturer.getPassword().equals(password));
                                networkUtil.getOos().reset();

                                networkUtil.write(manufacturer);
                                networkUtil.getOos().reset();

                                networkUtil.write(Server.SendUpdatedList());


                            } else if (manufacturer.isAddCar()) {

                                Server.AddCar(manufacturer.getCarToadd());

                            } else if (manufacturer.isDeleteCar()) {


                               List list= Server.DeleteCar(manufacturer.getDeleteCarReg());

                                networkUtil.getOos().reset();
                                networkUtil.write(list);

                            } else if (manufacturer.isEditCar()) {

                                List list = Server.changeData(manufacturer.getEditField());

                                networkUtil.getOos().reset();
                                networkUtil.write(list);

                            }
                            if(manufacturer.isShowManufacturerPage()){
                                System.out.println("I was here");

                                networkUtil.write(Server.SendUpdatedList());
                            }


                        } else if (object instanceof Viewer) {
                            System.out.println("got the object");
                            Viewer viewer=(Viewer) object;
                           if(viewer.isSearchCar()){
                               System.out.println("inside searching");

                               //viewer.setSearchField(null);
                               Car car=Server.search(viewer.getSearchField());

                               networkUtil.getOos().reset();
                              if(car!=null)
                               networkUtil.write(car);
                               else
                                   networkUtil.write("No such cars found!");

                           }
                           if(viewer.isBuyCar()){
                               Car car=Server.buyRequest(viewer.getBuyCarReg());
                              // Server.buyRequest()
                              if(car!=null)
                               networkUtil.write(car);
                              else
                                  networkUtil.write("No such cars found!");

                           }
                            if(viewer.isShowViewerList()){
                                System.out.println("inside sending updated list");

                                networkUtil.getOos().reset();
                                networkUtil.write(Server.SendUpdatedList());
                            }

                        }
                    }

                }


        }

         catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
