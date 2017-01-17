package om.gov.ita.partylights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    final static String PUBLISH_KEY = "pub-c-416f0b87-4dc6-4274-bd4a-59e529b6d044";
    final static String SUBSCRIBE_KEY = "sub-c-8ee57362-dc91-11e6-a669-0619f8945a4f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button joinPartyButton = (Button) findViewById(R.id.btn_join_party);
        joinPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PubnubListenerService.class);
                startService(intent);
            }
        });
    }
}
