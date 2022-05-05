//package com.clevertap.ct_android_full;
//
//import android.app.Application;
//import android.app.NotificationManager;
//import android.content.res.Configuration;
//import android.os.Build;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.clevertap.android.sdk.ActivityLifecycleCallback;
//import com.clevertap.android.sdk.CleverTapAPI;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.messaging.FirebaseMessaging;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//
//public class MyApplication extends Application {
//
//    private CleverTapAPI clevertapDefaultInstance;
//
//    // Called when the application is starting, before any other application objects have been created.
//    // Overriding this method is totally optional!
//    @Override
//    public void onCreate() {
//        ActivityLifecycleCallback.register(this);
//        super.onCreate();
//        singleton = this;
//        // Required initialization logic here!
//        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
//        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);
// //       clevertapDefaultInstance.pushEvent("Kris App Open");
////        String fcmRegId;
////        FirebaseMessaging.getInstance().getToken()
////                .addOnCompleteListener(new OnCompleteListener<String>() {
////                    @Override
////                    public void onComplete(@NonNull Task<String> task) {
////                        if (!task.isSuccessful()) {
////                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
////                            return;
////                        }
////
////                        // Get new FCM registration token
////                        String token = task.getResult();
////                        Log.v("TAG", "token: "+token);
////                        clevertapDefaultInstance.pushFcmRegistrationId(token,true);
////                        // Log and toast
//////                        String msg = getString(R.string.msg_token_fmt, token);
//////                        Log.d(TAG, msg);
//////                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
////                    }
////                });
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            CleverTapAPI.createNotificationChannel(getApplicationContext(),
////                    "testChannelId1","Test Channel 1",
////                    "Test Channel Description",
////                    NotificationManager.IMPORTANCE_MAX,true);
////        }
//
//    }
//
//
//    private static MyApplication singleton;
//
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//    }
//}
