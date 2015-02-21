package hu.r00ts.beesmarter.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client  {

    private String serverIP;
    private int serverPort = 9999;

    private Socket socket;

    public Client(String serverIP){
        this.serverIP = serverIP;
    }

    public boolean connect(){
        try{
            InetAddress serverAddress = InetAddress.getByName(serverIP);
            socket = new Socket(serverAddress, serverPort);
            socket.setSoTimeout(1000);
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return isConnected();
    }

    public boolean close(){
        if(isConnected()){
            try {
                socket.close();
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    public boolean isConnected(){
        return socket != null;
    }

    public void sendMessage(String text){
        try{
            if(isConnected()){
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                out.println(text);
            }
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String receiveMessage2(){
        String response = "";
        try{
            if(isConnected()){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                InputStream inputStream = socket.getInputStream();
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                }

                return response;
            }
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public String receiveMessage(){
        String response = "";
        try{
            if(isConnected()){
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                response = bufferedReader.readLine();
                return response;
            }
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
