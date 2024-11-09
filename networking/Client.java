package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public String hostIp;
    public int hostPortNumber;
    public String inMessage;
    public Socket socket; 

    public Client(String ip , int port){
        hostIp = ip;
        hostPortNumber = port;
        inMessage = "null";

        try {
            socket = new Socket(hostIp , hostPortNumber);
        }
        catch (UnknownHostException ex) {
            System.out.println("Server not found :"+ex.getMessage());
        } catch (IOException ioe) {
            System.out.println(" I/O Error : "+ioe.getMessage());
        }
    }

    public void getMessage(){
        try {
            while (true) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                inMessage = reader.readLine();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String outMessage) {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output , true);
            writer.println(outMessage);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}    

    // public static void main(String[] args) {
    //     Scanner scan = new Scanner(System.in);
    //     System.out.println("Enter host name : ");
    //     // String hostname = "localhost";  
    //     String hostname = scan.next();

    //     System.out.println("Enter port number  : ");
    //     int port = scan.nextInt();

    //     try (Socket socket = new Socket(hostname , port)){
    //         while (true) {
    //             InputStream input = socket.getInputStream();

    //             BufferedReader reader = new BufferedReader(new InputStreamReader(input));

    //             String inmessage = reader.readLine();
    //             System.out.println("Server : "+inmessage);

    //             // send outgoing message
    //             OutputStream output = socket.getOutputStream();
    //             PrintWriter writer = new PrintWriter(output , true);

    //             System.out.print("Enter a message to send : ");
    //             String outmessage = scan.nextLine();

    //             writer.println(outmessage);
    //         }

    //     } 
    //     catch (UnknownHostException ex) {
    //         System.out.println("Server not found :"+ex.getMessage());
    //     } catch (IOException ioe) {
    //         System.out.println(" I/O Error : "+ioe.getMessage());
    //     }
    // }    
// }
