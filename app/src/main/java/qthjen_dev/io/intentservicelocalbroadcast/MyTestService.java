package qthjen_dev.io.intentservicelocalbroadcast;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class MyTestService extends IntentService {
    public static final String ACTION = "qthjen.LocalBroadcast";

    public MyTestService() {
        super("LocalBroadcast");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Fetch data
        String val = intent.getStringExtra("foo");

        // add ACTION
        Intent intent1 = new Intent(ACTION);
        intent1.putExtra("resultCode", Activity.RESULT_OK);
        intent1.putExtra("resultValue", "My result value. Passed in: " + val);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
    }
}
