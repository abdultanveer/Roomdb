package com.next.roomdb;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AdditionService extends Service {
    public AdditionService() {
    }

    private final IBinder binder = new LocalBinder(); // binder is for synchronous ipc--1

    public class LocalBinder extends Binder {//2
        AdditionService getService() {
            // Return this instance of LocalService so clients can call public methods
            return AdditionService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;//4
    }

    public int add(int a, int b){//3
        return a+b;
       // stopSelf();
    }
}