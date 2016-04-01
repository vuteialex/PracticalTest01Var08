package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var08Service extends Service {
    public PracticalTest01Var08Service() {
    }

    private ProcessingThread processingThread = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int nrTries = intent.getIntExtra("nrOfTries", -1);
        int nrFail = intent.getIntExtra("nrOfSucc", -1);
        int nrSucc = intent.getIntExtra("nrOfFail", -1);
        processingThread = new ProcessingThread(getApplicationContext(), nrTries, nrFail, nrSucc);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
