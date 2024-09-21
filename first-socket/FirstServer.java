import java.io.*;
import java.net.*;

public class FirstServer {
  public static void main(String[] args) throws IOException {
    
    try{
    ServerSocket firstServerSocket = new ServerSocket(6868);
    Socket socketConnection = firstServerSocket.accept();

    DataInputStream dataStreamIn = new DataInputStream(socketConnection.getInputStream());

    String readString = (String)dataStreamIn.readUTF();

    System.out.println("Message from client: " + readString);

    firstServerSocket.close();
    }
    catch(Exception e){
      System.err.println(e);
    }


  }
}
