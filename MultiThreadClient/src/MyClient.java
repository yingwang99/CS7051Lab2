import java.io.*; 
import java.net.*;
import java.util.Scanner; 
 
public class MyClient 
{ 
 
		public Scanner sc = new Scanner(System.in);
		public MyClient() 
		{ 

		    Socket s = null;
			
			try
			{ 
				//s = new Socket("10.62.0.8", 54321); 
				s = new Socket("localhost", 54321); 
				
				/*OutputStream out = s.getOutputStream(); 
				DataOutputStream dout = new DataOutputStream(out);
				InputStream in = s.getInputStream();
				DataInputStream din = new DataInputStream(in);*/
				 
				BufferedReader br = getReader(s);
	        	PrintWriter pw = getWriter(s);
				
				String name = "";

            	while(true){
					 System.out.println("Please input your name");
					 name = sc.nextLine();
					 if(name.equalsIgnoreCase("KILL_SERVICE") ){
						 pw.println("KILL_SERVICE\n");
						 pw.flush();
				
						 
						 break;
					 }else{
						 pw.println("HELO " + name + "\n");
						 pw.flush();
						 String st = "";
						 for(int i = 0; i < 4; i++) {
							System.out.println(br.readLine());
						 }
						 
					 }
            	}
	           
				pw.close();
				br.close();
				s.close(); 

				} 
				catch (IOException e) 
				{
					System.err.println("IO Exception..");
				}finally{
		            try {
		                if(s!=null){
		                    s.close();
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
			public static void main(String[] args) 
			{ 
				new MyClient(); 
			} 
}