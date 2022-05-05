package com.clevertap.ct_android_full.fcm;


import android.app.NotificationManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessageListenerService extends FirebaseMessagingService {

	CleverTapAPI clevertapDefaultInstance;
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);


		////pay load recived from ct defalut notification builder
		boolean status = new CTFcmMessageHandler().createNotification(getApplicationContext(), remoteMessage);
		Log.e("test",status+"");





	}

	@Override
	public void onNewToken(@NonNull String s) {
		super.onNewToken(s);
		clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
		clevertapDefaultInstance.pushFcmRegistrationId(s,true);
		//CleverTapAPI.createNotificationChannel(this,"Test","Test","Channel for Push in App", NotificationManager.IMPORTANCE_HIGH,true);
	}


}