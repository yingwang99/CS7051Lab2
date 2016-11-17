import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 
public class MyServer {
	
	private static ExecutorService executorService = null;
	private static ServerSocket s = null;
	private static ArrayList<Socket> listners = null;
	private static boolean isDown = false;
	public MyServer(){
		
   	    final int POOL_SIZE=10;
   	    listners = new ArrayList<Socket>();
        
	    
        try{
	        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
            s= new ServerSocket(54321);
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
        	if(isDown == true){
        		break;
        	}
            
            try{
                Socket cs = s.accept();
                executorService.execute(new ServerThread(cs));
                listners.add(cs);
                //new ServerThread(cs).start();
                System.out.println("Client number: " + i);
                i++;
                
               
            }
            catch(IOException e)
            {
                //System.out.println(e);
                try {
                	if(!s.isClosed()){
                		s.close();
                	}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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




	public static ArrayList<Socket> getListners() {
		return listners;
	}




	public static void setListners(ArrayList<Socket> listners) {
		MyServer.listners = listners;
	}




	public static boolean isDown() {
		return isDown;
	}




	public static void setDown(boolean isDown) {
		MyServer.isDown = isDown;
	}




	public static void main(String[] args) {
        // TODO Auto-generated method stub
    	new MyServer();
    }
}