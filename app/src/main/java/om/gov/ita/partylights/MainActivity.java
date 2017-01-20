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
        FirebaseMessaging.getInstance().subscribeToTopic("lights");
        FirebaseMessaging.getInstance().subscribeToTopic("wakeup");

    }
}
