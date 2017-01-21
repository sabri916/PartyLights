package om.gov.ita.partylights;

import android.content.Intent;
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
        final Random random = new Random(32);

        //0 - 108 (5 seconds of random colours)
        for (int i=0; i < 108000 ; i += 5000) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int red = (int) (random.nextInt(255));
                    int green = (int) (random.nextInt(255));
                    int blue = (int) (random.nextInt(255));
                    relativeLayout.setBackgroundColor(Color.rgb(red,blue,green));
                }
            },i);
        }

        //108 - 112 (2 seconds of random colours)
        for (int i=108000; i < 112000 ; i += 2000) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int red = (int) (random.nextInt(255));
                    int green = (int) (random.nextInt(255));
                    int blue = (int) (random.nextInt(255));
                    relativeLayout.setBackgroundColor(Color.rgb(red,blue,green));
                }
            },i);
        }

        //112 - 205 (5 seconds of random colours)
        for (int i=112000; i < 205000 ; i += 5000) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int red = (int) (random.nextInt(255));
                    int green = (int) (random.nextInt(255));
                    int blue = (int) (random.nextInt(255));
                    relativeLayout.setBackgroundColor(Color.rgb(red,blue,green));
                }
            },i);
        }

        //205 - 222 (5 seconds of b&w colours)
        for (int i=205000; i < 222000 ; i += 5000) {

            if(i%10000 == 0) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.BLACK);
                    }
                }, i);
            }
            else{
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.WHITE);
                    }
                }, i);
            }

        }

        //222 - 250 (5 seconds of random colours)
        for (int i=222000; i < 250000 ; i += 5000) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int red = (int) (random.nextInt(255));
                    int green = (int) (random.nextInt(255));
                    int blue = (int) (random.nextInt(255));
                    relativeLayout.setBackgroundColor(Color.rgb(red,blue,green));
                }
            },i);
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LightsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        },26000);


//        for (int i=5000; i < 300000 ; i += 5000) {
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    int red = (int) (Math.random()*255);
//                    int green = (int) (Math.random()*255);
//                    int blue = (int) (Math.random()*255);
//                    relativeLayout.setBackgroundColor(Color.rgb(red,blue,green));
//                }
//            },i);
//        }

    }
}
