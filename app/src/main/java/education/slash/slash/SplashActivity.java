    package education.slash.slash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.Inet4Address;

import education.slash.slash.view.MainActivity;

    public class SplashActivity extends AppCompatActivity {

    private final int SplashScreenDelay=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(in);
                SplashActivity.this.finish();
            }
        },SplashScreenDelay);
    }
}
