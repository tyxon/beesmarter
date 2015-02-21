package hu.r00ts.beesmarter.communication;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, Void> {

    private String serverIP = "192.168.2.8";
    private int serverPort = 9999;
    private String response = "";
    private Activity activity;

    public Client(Activity activity){
        this.activity = activity;
    }

    public Client(Activity activity, String serverIP){
        this.activity = activity;
        this.serverIP = serverIP;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Socket socket = null;

        try{
            socket = new Socket(serverIP, serverPort);
            /*ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream inputStream = socket.getInputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }*/
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT);
        super.onPostExecute(result);
    }

}
