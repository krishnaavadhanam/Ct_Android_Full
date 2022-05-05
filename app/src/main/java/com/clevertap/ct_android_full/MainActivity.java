package com.clevertap.ct_android_full;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CTInboxListener {
    public  static String identity="AVDPV8987R";
    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////init
         clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

         ////creating push channel

        ////setting loglevel

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.INFO);    //Default Log level

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);   //Set Log level to DEBUG log warnings or other important messages

        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);     //Set Log level to VERBOSE

        //CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.OFF); //Switch off logs for Production environment





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CleverTapAPI.createNotificationChannel(this,"Test","Test","Channel for Push in App", NotificationManager.IMPORTANCE_HIGH,true);


        }
/////App in box(init appinbox)


        if (clevertapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox();
        }
//////////////////



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {

                        if (!task.isSuccessful()) {
                            //    Log.e('test', "Fetching FCM registration token failed", task.getException());
                            return;
                        }


                        String token = task.getResult();
                        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_LONG).show();
                        clevertapDefaultInstance.pushFcmRegistrationId(token,true);


                    }
                });



        findViewById(R.id.send_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                clevertapDefaultInstance.pushEvent("viewed");


            }
        });


        findViewById(R.id.onuserlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", "Krishna Avadhanam");    // String
                profileUpdate.put("Identity", identity);      // String or number
                profileUpdate.put("Email", "avd@gmail.com"); // Email address of the user
                profileUpdate.put("Phone", "+9987866544");   // Phone (with the country code, starting with +)
                profileUpdate.put("Gender", "M");             // Can be either M or F
                profileUpdate.put("DOB", new Date());         // Date of Birth. Set the Date object to the appropriate value first
// optional fields. controls whether the user will be sent email, push etc.
                profileUpdate.put("MSG-email", true);        // Disable email notifications
                profileUpdate.put("MSG-push", true);          // Enable push notifications
                profileUpdate.put("MSG-sms",true);          // Disable SMS notifications
                profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications
                ArrayList<String> stuff = new ArrayList<String>();
                stuff.add("bag");
                stuff.add("shoes");
                profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings
                String[] otherStuff = {"Jeans","Perfume"};
                profileUpdate.put("MyStuff", otherStuff);                   //String Array

                clevertapDefaultInstance.onUserLogin(profileUpdate);


            }
        });



        findViewById(R.id.profilepush).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Identity", identity);                    // String or number
                profileUpdate.put("Phone", "+14155551234");                 // Phone (with the country code, starting with +)
                profileUpdate.put("Gender", "M");                           // Can be either M or F
                profileUpdate.put("Employed", "Y");                         // Can be either Y or N
                profileUpdate.put("Education", "Graduate");                 // Can be either Graduate, College or School
                profileUpdate.put("Married", "Y");                          // Can be either Y or N
                profileUpdate.put("DOB", new Date());                       // Date of Birth. Set the Date object to the appropriate value first
                profileUpdate.put("Tz", "Asia/Kolkata");                    //an abbreviation such as "PST", a full name such as "America/Los_Angeles",
                //or a custom ID such as "GMT-8:00"
                profileUpdate.put("Photo", "www.foobar.com/image.jpeg");    // URL to the Image

//                                                            number from SMS                                                                  notifications
                ArrayList<String> stuff = new ArrayList<String>();
                stuff.add("bag");
                stuff.add("shoes");
                profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings

                String[] otherStuff = {"Jeans","Perfume"};
                profileUpdate.put("MyStuff", otherStuff);                   //String Array

                clevertapDefaultInstance.pushProfile(profileUpdate);


            }
        });




        findViewById(R.id.sendprodprop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Name", "Casio Chronograph Watch");
                prodViewedAction.put("Category", "Mens Accessories");
                prodViewedAction.put("Price", 59.99);
                prodViewedAction.put("Date", new java.util.Date());

                clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);



            }
        });



        findViewById(R.id.intentnative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NativeDisplay.class));

            }

        });






    }

    @Override
    public void inboxDidInitialize() {


        findViewById(R.id.appinbox).setOnClickListener(v -> {
            ArrayList<String> tabs = new ArrayList<>();
            tabs.add("Promotions");
            tabs.add("Offers");//We support upto 2 tabs only. Additional tabs will be ignored

            CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
            styleConfig.setFirstTabTitle("First Tab");
            styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
            styleConfig.setTabBackgroundColor("#FF0000");
            styleConfig.setSelectedTabIndicatorColor("#0000FF");
            styleConfig.setSelectedTabColor("#0000FF");
            styleConfig.setUnselectedTabColor("#FFFFFF");
            styleConfig.setBackButtonColor("#FF0000");
            styleConfig.setNavBarTitleColor("#FF0000");
            styleConfig.setNavBarTitle("MY INBOX");
            styleConfig.setNavBarColor("#FFFFFF");
            styleConfig.setInboxBackgroundColor("#ADD8E6");
            if (clevertapDefaultInstance != null) {
                clevertapDefaultInstance.showAppInbox(styleConfig); //With Tabs
            }
            //ct.showAppInbox();//Opens Activity with default style configs
        });

    }
//Callback on Inbox Message update/delete/read (any activity)

    @Override
    public void inboxMessagesDidUpdate() {

    }



}