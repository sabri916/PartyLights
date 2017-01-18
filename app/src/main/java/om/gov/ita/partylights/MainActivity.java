package om.gov.ita.partylights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.google.firebase.messaging.FirebaseMessaging.INSTANCE_ID_SCOPE;

public class MainActivity extends AppCompatActivity {

    final static String PUBLISH_KEY = "pub-c-416f0b87-4dc6-4274-bd4a-59e529b6d044";
    final static String SUBSCRIBE_KEY = "sub-c-8ee57362-dc91-11e6-a669-0619f8945a4f";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //topic subscription
        FirebaseMessaging.getInstance().subscribeToTopic("party");

        //
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://fcm.googleapis.com/fcm/send";
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject = new JSONObject("{\"to\": \"topics/party\",\"data\": {\"message\": \"Thisage!\"}}");

//            jsonObject.put("to","/topics/party");
//            jsonObject.put("data","{\"message\": \"Thisage!\"}");
            Log.i(TAG,"json: " + jsonObject.toString());
        } catch (JSONException e) {
            Log.e(TAG,e.toString());
        }
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Listener() {
            @Override
            public void onResponse(Object response) {
                Log.i(TAG, "response: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Authorization","key=AIzaSyB9IFO8NLmnByS2atm75xDI7L2W7PrYCkE");
                return params;
            }

            @Override
            public byte[] getBody() {
                return "{\"to\": \"/topics/party\",\"data\": {\"message\": \"Thisage!\"}}".getBytes();
            }
        };


        Button wakeUpButton = (Button) findViewById(R.id.btn_join_party);
        wakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                Log.i(TAG, "wake up pressed");
//                RemoteMessage remoteMessage = new RemoteMessage.Builder("party@gcm.googleapis.com")
//                        .setMessageId("1")
//                        .addData("from","party")
//                        .addData("category","om.gov.ita.partylights")
//                        .addData("message_id","3")
//                        .addData("yelloww","blue").build();
//
//                FirebaseMessaging.getInstance().send(remoteMessage);
                queue.add(jsonRequest);
            }
        });
    }
}
