package om.gov.ita.partylights;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverManagerService extends Service {

    private BroadcastReceiver mBroadcastReceiver;

    public BroadcastReceiverManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(mBroadcastReceiver,intentFilter);
        Log.i("LightsPartyMain","BroadcastReceiver Registered");
        Toast.makeText(this,"onCreat() Service",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        Toast.makeText(this,"onDestroy() Service",Toast.LENGTH_SHORT).show();
    }
}
