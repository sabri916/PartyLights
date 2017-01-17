package om.gov.ita.partylights;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;


import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;


import java.util.Arrays;

public class PubnubListenerService extends Service {

    private final static String TAG = "LightsService";


    PNConfiguration pnConfiguration = new PNConfiguration();


    String channel = "my_channel";
    PowerManager.WakeLock wl = null;

    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String pnMsg = msg.obj.toString();

            final Toast toast = Toast.makeText(getApplicationContext(), pnMsg, Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 200);

        }
    };

    private void notifyUser(Object message) {

        Message msg = handler.obtainMessage();

        try {
            final String obj = (String) message;
            msg.obj = obj;
            handler.sendMessage(msg);
            Log.i("Received msg : ", obj.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate() {
        super.onCreate();

        pnConfiguration.setPublishKey(MainActivity.PUBLISH_KEY);
        pnConfiguration.setSubscribeKey(MainActivity.SUBSCRIBE_KEY);
        pnConfiguration.setSecure(false);
        PubNub pubnub = new PubNub(pnConfiguration);
        pubnub.subscribe().channels(Arrays.asList("my_channel")).execute();

        Toast.makeText(this, "PubnubService created...", Toast.LENGTH_LONG).show();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SubscribeAtBoot");
        if (wl != null) {
            wl.acquire();
            Log.i("PUBNUB", "Partial Wake Lock : " + wl.isHeld());
            Toast.makeText(this, "Partial Wake Lock : " + wl.isHeld(), Toast.LENGTH_LONG).show();
        }

        Log.i("PUBNUB", "PubnubService created...");

        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if(status.getCategory() == PNStatusCategory.PNUnknownCategory){
                    Log.i("pubnub",status.getErrorData().toString());
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                Intent intent = new Intent(PubnubListenerService.this, LightsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wl != null) {
            wl.release();
            Log.i("PUBNUB", "Partial Wake Lock : " + wl.isHeld());
            Toast.makeText(this, "Partial Wake Lock : " + wl.isHeld(), Toast.LENGTH_LONG).show();
            wl = null;
        }
        Toast.makeText(this, "PubnubService destroyed...", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
