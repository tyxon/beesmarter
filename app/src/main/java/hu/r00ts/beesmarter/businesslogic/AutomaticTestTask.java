package hu.r00ts.beesmarter.businesslogic;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;
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
    private String clientId;
    private String goodbyText = "GOODBYE - CORRECTANSWERS=[";

    private boolean isSuccessfullyConnected = false;

    public AutomaticTestTask(Activity activity, String serverIP, String clientId) {
        this.activity = activity;
        this.serverIP = serverIP;
        this.clientId = clientId;
    }

    @Override
    protected Void doInBackground(Void... params) {
        client = new Client(serverIP);

        if (client.isConnected() || client.connect()) {
            isSuccessfullyConnected = true;

            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "answer is null");

            client.sendMessage(START);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "answer is null");

            client.sendMessage(clientId);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "answer is null");

            //current password
            client.sendMessage(REQUEST_DATA);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "answer is null");
            String password = answer.substring(9);

            //current behaviour
            client.sendMessage(REQUEST_TRAIN);
            answer = client.receiveMessage();
            Log.d("answer", answer != null ? answer : "answer is null");
            Training training = XmlParser.parseTraining(answer);
            PasswordBehaviorChecker passwordBehaviorChecker = new PasswordBehaviorChecker(password, training);

            //tests
            boolean isTestData = true;
            client.sendMessage(REQUEST_TEST);
            while (isTestData) {
                answer = client.receiveMessage();
                Log.d("answer", answer != null ? answer : "answer is null");

                if (answer != null && answer.contains("GOODBYE")) {
                    isTestData = false;
                } else {
                    Pattern testPattern = XmlParser.parsePattern(answer);
                    boolean isOk = passwordBehaviorChecker.isOk(testPattern);
                    client.sendMessage(isOk ? ACCEPT : REJECT);
                    goodbyText += (isOk ? ACCEPT : REJECT) + " ";
                    Log.d("answer", isOk ? ACCEPT : REJECT);
                    Log.d("tyxon", isOk ? ACCEPT : REJECT);
                }
            }

            Log.d("answer", goodbyText + "]");
            Log.d("answer", answer.equals(goodbyText)? "GOOD" : "BAD");

            client.close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (!isSuccessfullyConnected) {
            Toast.makeText(activity, "Connection fail", Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(result);
    }

}
