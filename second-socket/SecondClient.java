
import java.io.*;
import java.net.*;

public class SecondClient {
  public static void main(String[] args) {

    try{
      Socket clientSocket = new Socket("localhost",6868);

      DataInputStream dataStreamIn = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream dataStreamOut = new DataOutputStream(clientSocket.getOutputStream());

      BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));  

      String inputString = "", outputString = "";

      while(inputString != "bye") {
        inputString = bufferedReader.readLine();
        dataStreamOut.writeUTF(inputString);
        dataStreamOut.flush();

        outputString = (String)dataStreamIn.readUTF();

        System.out.println("Message from server: " + outputString);
  
      }

      dataStreamIn.close();
      dataStreamOut.close();
      clientSocket.close();

    }
    catch(Exception e){
      System.err.println(e);
    }

  }
}
