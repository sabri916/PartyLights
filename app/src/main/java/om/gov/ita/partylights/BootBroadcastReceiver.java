package om.gov.ita.partylights;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sabri on 14/01/17.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("LightParty","Lightparty Boot Broadcast receiver started");
        Intent serviceIntent = new Intent(context, BroadcastReceiverManagerService.class);
        context.startService(serviceIntent);
    }
}
