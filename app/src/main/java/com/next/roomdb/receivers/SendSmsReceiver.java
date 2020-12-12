package com.next.roomdb.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class SendSmsReceiver  extends BroadcastReceiver {
    public static String TAG = SendSmsReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"sending birthday wishes to sunil");
         SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("5556","9880979732","happy birthday sunil from alarm",null,null);

    }
}
