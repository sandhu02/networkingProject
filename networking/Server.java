package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public String hostIp;
    public int portNumber;
    public String inMessage;
    public Socket socket;

    public Server(int port){
        try { hostIp = InetAddress.getLocalHost().getHostAddress();} catch (Exception e) { e.getMessage(); }
        portNumber = port;

        //make socket and connect to client
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is listening on port : "+portNumber);
            System.out.println(hostIp);

            while (true) {
                socket = serverSocket.accept();
                System.out.println("New client connected");
            }    
        }
        catch (IOException ioexception) {
            System.out.println("Server exception : "+ioexception.getMessage());
        }
    }


    public void sendMessage (String message){
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output , true);
            writer.println(message);
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    public void listenforMessage (){
        try {
            while (true) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                inMessage = reader.readLine();
            }
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
}    

//     public static void main(String[] args) {
//         Scanner scan = new Scanner(System.in);

//         System.out.println("Enter port number : ");
//         int port = 6666;   /*scan.nextInt();*/

//         try (ServerSocket serverSocket = new ServerSocket(port)) {
//             System.out.println("Hostname : "+InetAddress.getLocalHost());
//             System.out.println("Server is listening on port : "+port);

//             while (true) {
//                 Socket socket = serverSocket.accept();
//                 System.out.println("New client connected");

//                 OutputStream output = socket.getOutputStream();
//                 PrintWriter writer = new PrintWriter(output , true);
//                 while (true) {
//                     System.out.print("Enter a message to send : ");
//                     String outmessage = scan.nextLine();

//                     writer.println(outmessage);
                    
//                     // listen for incoming message
//                     InputStream input = socket.getInputStream();
//                     BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                     String inmessage = reader.readLine();
//                     System.out.println(socket.getInetAddress()+" : "/*replace with client */ +inmessage);
//                 }

//             }


//         } catch (IOException ioexception) {
//             System.out.println("Server exception : "+ioexception.getMessage());
//         }

//     }
//  }