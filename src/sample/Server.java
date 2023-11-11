package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server {
   static int updateCount;
    private static CarDataBase carDataBase=new CarDataBase();
   public static boolean updatestatus=false;
    public static void loadFromFile(){


        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] str=line.split(",");
                System.out.println("getting car from file");

                carDataBase.addCar(new Car(str[0],Integer.parseInt(str[1]),str[2],str[3],str[4],str[5],str[6],Integer.parseInt(str[7]),Integer.parseInt(str[8])));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private ServerSocket serverSocket;
    public HashMap<String, String> ManufacturerInfo;

    Server() {
        ManufacturerInfo = new HashMap<>();
        ManufacturerInfo.put("a", "a");
        ManufacturerInfo.put("b", "b");
        ManufacturerInfo.put("c", "c");
        ManufacturerInfo.put("d", "d");
        ManufacturerInfo.put("e", "e");
        try {        updateCount=0;
            serverSocket = new ServerSocket(12444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
     //   System.out.println("writing the first list");
       // List list=carDataBase.getCars();
       // networkUtil.write(list);
        System.out.println("connection established");
         new ServerReadThread(networkUtil,ManufacturerInfo);
         new UpdateThread(networkUtil,updateCount,this);
//        new WriteThread(networkUtil, "Server");
    }

    public static void main(String args[]) {
        loadFromFile();
        Server server = new Server();

    }
    public static List AddCar(Car carToAdd){
        System.out.println("In server");
        System.out.println(carToAdd);
        carDataBase.addCar(carToAdd);
        loadToFile(); updateCount++;
        return carDataBase.getCars();


    }
    public static Car buyRequest(String str){

      int index=  carDataBase.queryCar(str);
      if(index==-1){
          Car o=null;
          return o;
      }
      else {
          int quantity = carDataBase.getCars().get(index).getQuantity();
          if (quantity > 0) {
              quantity = quantity - 1;

              carDataBase.getCars().get(index).setQuantity(quantity);

              loadToFile(); updateCount++;
              return carDataBase.searchCar(str);

          }
      }

              Car o=null;
              return o;





    }
    public static Car search(String string){
        String[] str=string.split(" ");

        if(str.length==2){
            System.out.println("Searching for "+str[0]+" "+str[1]);
           return carDataBase.searchCar(str[0],str[1]);
        }else {
            System.out.println("Searching for "+str[0]);
            return carDataBase.searchCar(str[0]);
        }

    }
    public static List changeData(String data){

        System.out.println("In change data Method");
        System.out.println(data);
        String[] str=data.split(" ");
        int index=carDataBase.queryCar(str[0]);
        carDataBase.EditCar(Integer.parseInt(str[1]),Integer.parseInt(str[2]),index);


        loadToFile();updateCount++;
        System.out.println("update count print kori "+ updateCount);

        return (carDataBase.getCars());

    }
    public static List DeleteCar(String str){


        carDataBase.removeCar(str);
        loadToFile();updateCount++;
        return (carDataBase.getCars());
    }
    public static void loadToFile(){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("cars.txt"));


            for(Car c:carDataBase.getCars()){
                bw.write(c.toString());

                bw.write("\n");}
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List SendUpdatedList(){
        return carDataBase.getCars();
    }
}
