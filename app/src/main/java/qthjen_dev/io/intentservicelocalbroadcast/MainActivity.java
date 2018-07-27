package qthjen_dev.io.intentservicelocalbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onStartService();
    }

    public void onStartService() {
        Intent i = new Intent(this, MyTestService.class);
        i.putExtra("foo", "qthjen");
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register broadcast based on ACTION string
        IntentFilter intentFilter = new IntentFilter(MyTestService.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(testBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(testBroadcastReceiver);
    }

    private BroadcastReceiver testBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                String resultVal = intent.getStringExtra("resultValue");
                Toast.makeText(context, "" + resultVal, Toast.LENGTH_SHORT).show();
                Log.d("hi", "" + resultVal);
            }
        }
    };
}
