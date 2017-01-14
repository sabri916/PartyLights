package om.gov.ita.partylights;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

/**
 * Created by sabri on 13/01/17.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent activityIntent = new Intent(context,LightsActivity.class);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        //if(hour == 23 && minute == 19){
            activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityIntent);
            Intent serviceIntent = new Intent(context,BroadcastReceiverManagerService.class);
            context.stopService(serviceIntent);
        //}
    }
}
