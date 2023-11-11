package sample;

import java.io.IOException;
import java.util.List;

public class UpdateThread implements Runnable{
    int updateCount;
    private Thread thread;
    Server server;
    private NetworkUtil networkUtil;

    public UpdateThread(NetworkUtil networkUtil,int updateCount,Server server) {
        this.networkUtil = networkUtil;
        this.updateCount=updateCount;
        this.server=server;
        this.thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {

                if (this.updateCount != Server.updateCount) {
                    System.out.println("mile na");
                    try {
                        List list=Server.SendUpdatedList();
                        System.out.println(list);
                        networkUtil.getOos().reset();
                        networkUtil.write(list);
                        this.updateCount = Server.updateCount;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


    }
}
