package om.gov.ita.partylights;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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

    public PubnubListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG,"On Create()");

        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey(MainActivity.PUBLISH_KEY);
        pnConfiguration.setSubscribeKey(MainActivity.SUBSCRIBE_KEY);
        pnConfiguration.setSecure(false);

        PubNub pubnub = new PubNub(pnConfiguration);


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

        pubnub.subscribe().channels(Arrays.asList("my_channel")).execute();
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
        Log.i(TAG,"On Destroy()");
    }
}
