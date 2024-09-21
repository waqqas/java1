import java.io.*;
import java.net.*;

public class SecondServer {
  public static void main(String[] args) throws IOException {
    
    try{
    ServerSocket serverSocket = new ServerSocket(6868);
    Socket socketConnection = serverSocket.accept();

    DataInputStream dataStreamIn = new DataInputStream(socketConnection.getInputStream());
    DataOutputStream dataStreamOut = new DataOutputStream(socketConnection.getOutputStream());

    BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));  

    String inputString = "", outputString = "";

    while (inputString != "bye") {
      outputString = (String)dataStreamIn.readUTF();
      System.out.println("Message from client: " + outputString);

      inputString = bufferedReader.readLine();
      dataStreamOut.writeUTF(inputString);
      dataStreamOut.flush();      
    }

    dataStreamIn.close();
    dataStreamOut.close();

    serverSocket.close();
    }
    catch(Exception e){
      System.err.println(e);
    }


  }
}
