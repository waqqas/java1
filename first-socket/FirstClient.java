
import java.io.*;
import java.net.*;

public class FirstClient {
  public static void main(String[] args) {

    try{
      Socket clientSocket = new Socket("localhost",6868);

      DataOutputStream dataStreamOut = new DataOutputStream(clientSocket.getOutputStream());

      dataStreamOut.writeUTF("Happy Coding!");

      dataStreamOut.flush();

      dataStreamOut.close();
      clientSocket.close();

    }
    catch(Exception e){
      System.err.println(e);
    }

  }
}
