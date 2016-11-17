import java.io.*; 
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 
public class MyServer {

	 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	ExecutorService executorService = null;
   	    final int POOL_SIZE=10;
   	  
        ServerSocket s = null;
	    
        try{
	        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
            s= new ServerSocket(5432);
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
                executorService.execute(new ServerThread(cs));
                //new ServerThread(cs).start();
                System.out.println("Client number: " + i);
                i++;
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
        }
    }

}