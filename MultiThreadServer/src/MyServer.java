import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.midi.Soundbank; 

public class MyServer {
	
	private static ExecutorService executorService = null;
	private static ServerSocket s = null;
	private static ArrayList<ServerThread> listners = null;
	private static boolean isDown = false;
	public MyServer(){
		
   	    final int POOL_SIZE=10;
   	    listners = new ArrayList<ServerThread>();
        
	    
        try{
	        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
            s = new ServerSocket(54321);
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(1);
        }
        System.out.println("Server start!");
        int i = 1;
        while(true)
        {
        	     
            try{
                Socket cs = s.accept();
                ServerThread serverThread = new ServerThread(cs);
                executorService.execute(serverThread);
                listners.add(serverThread);
                //new ServerThread(cs).start();
                System.out.println("Client number: " + i);
                i++;
                   
            }
            catch(IOException e)
            {
                //System.out.println(e);
                
            }
        }
        
    }
	
	
	
	 
    public static ExecutorService getExecutorService() {
		return executorService;
	}

	public static void setExecutorService(ExecutorService executorService) {
		MyServer.executorService = executorService;
	}


	public static ServerSocket getS() {
		return s;
	}




	public static void setS(ServerSocket s) {
		MyServer.s = s;
	}




	public static ArrayList<ServerThread> getListners() {
		return listners;
	}




	public static void setListners(ArrayList<ServerThread> listners) {
		MyServer.listners = listners;
	}

	public static void main(String[] args) {
        // TODO Auto-generated method stub
    	new MyServer();
    }
}
