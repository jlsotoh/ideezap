package com.zappar.SwiftEmbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zappar.ZapparEmbed;


public class MainActivity extends AppCompatActivity  {

    Context mContext;
    LocalBroadcastManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        /*boolean isCompatible = ZapparEmbed.isCompatible(mContext);

        if (isCompatible) {
            Intent i = new Intent(this, ZapparEmbed.getZapcodeClassForIntent());
            startActivity(i);
        }*/

        final Button btnARG = findViewById(R.id.btn_AR);
        btnARG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonAR();
            }
        });

        final Button btnDeep = findViewById(R.id.btnDeepLink);
        btnDeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonDeep();
            }
        });


        // The Zappar component report analytics events using the LocalBroadcastManager
        mManager = LocalBroadcastManager.getInstance(mContext);

// Listen for the Zappar-specific actions
        IntentFilter filter = new IntentFilter();
        filter.addAction(ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_START);
        filter.addAction(ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_END);
        filter.addAction(ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_CUSTOM_EVENT);

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent zapparIntent) {
                String deepLinkId = zapparIntent.getStringExtra(ZapparEmbed.EXTRA_ANALYTICS_DEEP_LINK_ID);

                switch (zapparIntent.getAction()) {
                    case ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_START:
                        Log.d("CustomAnalytics", "Zappar experience started: " + deepLinkId);
                        break;
                    case ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_END:
                        Log.d("CustomAnalytics", "Zappar experience ended: " + deepLinkId);
                        break;
                    case ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_CUSTOM_EVENT:
                        String eventName = zapparIntent.getStringExtra(ZapparEmbed.EXTRA_ANALYTICS_EVENT_NAME);
                        Log.d("CustomAnalytics", "Zappar experience " + deepLinkId + " emitted custom event: " + eventName);
                        break;
                }
            }
        };
        mManager.registerReceiver(mReceiver, filter);
    }

    public void botonDeep(){
        boolean isCompatible = ZapparEmbed.isCompatible(mContext);

        if (isCompatible) {
            Intent i = new Intent(this, ZapparEmbed.getZapcodeClassForIntent());
            //i.putExtra(ZapparEmbed.EXTRA_LAUNCH_DEEP_LINK, "z/0ANb1c");
            i.putExtra(ZapparEmbed.EXTRA_LAUNCH_DEEP_LINK, "z/NjAj1c");

            startActivity(i);

        }
    }

    public void botonAR(){
        Intent i = new Intent(this, ZapparEmbed.getZapcodeClassForIntent());
        startActivity(i);
    }

}
