import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

 
public class ServerThread extends Thread {
    static String hello = "HELO ";
    Socket sock;
    public ServerThread(Socket s)
    {
        sock =s ;
    }
    public void run()
    {
        try{
            /*InputStream in = sock.getInputStream();
            DataInputStream din = new DataInputStream(in);
            OutputStream out = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);*/
            
        	BufferedReader br = getReader(sock);
        	PrintWriter pw = getWriter(sock);
            
            String localIp = InetAddress.getLocalHost().getHostAddress();

            String name = "";
	        while(true){        	
        	  if((name = br.readLine()) != null){
        		 
        		  if(name.equalsIgnoreCase("KILL_SERVICE")){
					  
					  MyServer.setDown(true);
					  new Socket("localhost", 54321);
					  sock.close();
					  
					  
					  break;
        			  
  	            	}
	                pw.println(name + "\n" + "IP:" + localIp + "\n" + "Port: " + 54321 + "\n" + "StudentId: 16308222");
	                pw.flush();
	                br.readLine();
            	}     
	        }
	        
            br.close();
            pw.close();
            //sock.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }finally{
            try {
                if(sock!=null){
                    sock.close();
                }
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
	
    
}