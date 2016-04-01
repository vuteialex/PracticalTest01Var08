package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;

import java.util.Date;


public class ProcessingThread extends Thread {
    Context context;
    private int nrTries;
    private int nrFail;
    private int nrSucces;

    public ProcessingThread(Context context, int nrTries,  int nrFail,  int nrSucces) {
        this.context = context;
        this.nrTries = nrTries;
        this.nrFail = nrFail;
        this.nrSucces = nrSucces;

    }

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        Intent intent = new Intent("broadcast_message_action");
        String message = new Date(System.currentTimeMillis()).toString() + " and result is: " + nrTries + " " + nrFail + " " + nrSucces;
        intent.putExtra("broadcast_message", message);
        context.sendBroadcast(intent);
    }
}