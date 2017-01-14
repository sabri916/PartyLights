package om.gov.ita.partylights;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class LightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lights);

        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_lights);
        relativeLayout.setBackgroundColor(Color.BLUE);

        Handler mHandler = new Handler();

        for (int i=5000; i < 300000 ; i += 5000) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int red = (int) (Math.random()*255);
                    int green = (int) (Math.random()*255);
                    int blue = (int) (Math.random()*255);
                    relativeLayout.setBackgroundColor(Color.rgb(red,blue,green));
                }
            },i);
        }

    }
}
