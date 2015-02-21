package hu.r00ts.beesmarter.businesslogic;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import hu.r00ts.beesmarter.communication.Client;

public class AutomaticTestTask extends AsyncTask<Void, Void, Void> {

    private static final String START = "BSP 1.0 CLIENT HELLO";
    private static final String CLIENT_ID = "TEST1";
    private static final String REQUEST_DATA = "RQSTDATA";
    private static final String REQUEST_TRAIN = "RQSTTRAIN";
    private static final String REQUEST_TEST = "RQSTTEST";
    private static final String ACCEPT = "ACCEPT";
    private static final String REJECT = "REJECT";


    private Activity activity;
    private String serverIP;
    private Client client;
    private String answer;

    private boolean isSuccessfullyConnected = false;

    public AutomaticTestTask(Activity activity, String serverIP){
        this.activity = activity;
        this.serverIP = serverIP;
    }

    @Override
    protected Void doInBackground(Void... params) {
        client = new Client(serverIP);

        if(client.isConnected() || client.connect()) {
            //Todo: logic
            isSuccessfullyConnected = true;

            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "");

            client.sendMessage(START);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "");

            client.sendMessage(CLIENT_ID);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "");

            //current password
            client.sendMessage(REQUEST_DATA);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "");

            //current behaviour
            client.sendMessage(REQUEST_TRAIN);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "");

            //tests
            boolean isTestData = true;
            client.sendMessage(REQUEST_TEST);
            while(isTestData) {
                answer = client.receiveMessage();
                Log.d("answer", answer != null ? answer : "");

                if(answer != null && answer.contains("GOODBYE")){
                    isTestData = false;
                }else{
                    //Todo: test process logic
                    client.sendMessage(ACCEPT);
                }
            }

            client.close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(activity, isSuccessfullyConnected ? "Connection success" : "Connection fail", Toast.LENGTH_SHORT).show();

        super.onPostExecute(result);
    }

}
