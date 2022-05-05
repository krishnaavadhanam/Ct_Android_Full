package com.clevertap.ct_android_full;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent;

import java.util.ArrayList;
import java.util.Map;

public class NativeDisplay  extends AppCompatActivity implements DisplayUnitListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nativediaplay);
        /////raise event for nativedisplay
        CleverTapAPI.getDefaultInstance(this).pushEvent("testnative");

        CleverTapAPI.getDefaultInstance(this).setDisplayUnitListener(this);

    }

    @Override
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        Toast.makeText(getApplicationContext(),"PAYLOAD Size:-"+ units.size(),Toast.LENGTH_LONG).show();

        for (CleverTapDisplayUnit cleverTapDisplayUnit: units) {
            Map<String,String> customMap = cleverTapDisplayUnit.getCustomExtras();

            ArrayList<CleverTapDisplayUnitContent> contents = cleverTapDisplayUnit.getContents();
            for (CleverTapDisplayUnitContent content: contents) {
                String title = content.getTitle();
                String message = content.getMessage();
                String mediaUrl = content.getMedia();
                Log.e("testnative",title+"   "+message);

                //  nativeText.setText(title+" "+message);

            }
        }

    }
}
