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
	public MyServer() throws IOException{
		
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
                if(isDown == true) {
            		System.out.println();
            		break;
            	}
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
                if(s != null){
                	s.close();
                }
            }
        }
        executorService.shutdown();
        s.close();
	}
	
	 
    public static boolean isDown() {
		return isDown;
	}


	public static void setDown(boolean isDown) {
		MyServer.isDown = isDown;
	}


	public static ExecutorService getExecutorService() {
		return executorService;
	}

	public static void setExecutorService(ExecutorService executorService) {
		MyServer.executorService = executorService;
	}


	public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
    	new MyServer();
    }
}
