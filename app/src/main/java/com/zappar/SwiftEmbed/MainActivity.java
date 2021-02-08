package com.zappar.SwiftEmbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.ideeolabs.ideearframework.clInit;
import com.zappar.ZapparEmbed;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity  {

    Context mContext;
    LocalBroadcastManager mManager;

    private BroadcastReceiver mReceiver = null;
    Intent iZappar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        final Button btnDeep = findViewById(R.id.btnDeepLink);
        btnDeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirExperiencia();
            }
        });

        mManager = LocalBroadcastManager.getInstance(mContext);
        // Listen for the Z-specific actions
        IntentFilter filter = new IntentFilter();
        filter.addAction(ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_START);
        filter.addAction(ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_END);
        filter.addAction(ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_CUSTOM_EVENT);
        filter.addAction(ZapparEmbed.ACTION_HOST_MESSAGE);
        filter.addAction("ideearMessage");
        filter.addAction("openZappar");


        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent zIntent) {
                String deepLinkId = zIntent.getStringExtra(ZapparEmbed.EXTRA_ANALYTICS_DEEP_LINK_ID);

                switch (zIntent.getAction()) {
                    case ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_START:
                        Log.d("CustomAnalytics", "Zappar experience started: " + deepLinkId);
                        break;
                    case ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_END:
                        Log.d("CustomAnalytics", "Zappar experience ended: " + deepLinkId);
                        break;
                    case ZapparEmbed.ACTION_ANALYTICS_EXPERIENCE_CUSTOM_EVENT:
                        String eventName = zIntent.getStringExtra(ZapparEmbed.EXTRA_ANALYTICS_EVENT_NAME);
                        Log.d("CustomAnalytics", "Zappar experience " + deepLinkId + " emitted custom event: " + eventName);
                        break;
                    case ZapparEmbed.ACTION_HOST_MESSAGE:
                        String messageHost = zIntent.getStringExtra(ZapparEmbed.EXTRA_HOST_MESSAGE);
                        Log.d("CustomAnalytics", "Zappar extra host mesage: " + messageHost);
                        break;
                }

                if (zIntent.hasExtra("link")){
                    String data = zIntent.getStringExtra("link");

                    botonDeep(data);
                }

                if (zIntent.hasExtra("message")){
                    String message = zIntent.getStringExtra("message");
                    Log.d("idezap","mensage: "+message);
                }
            }
        };
        mManager.registerReceiver(mReceiver, filter);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    private void abrirExperiencia() {
        String jsonName = "{\n" +
                "   \"data\":[\n" +
                "      {\n" +
                "         \"scenes\":[\n" +
                "            {\n" +
                "               \"name\":\"MP_CASCADA_DE_PAGOS\",\n" +
                "               \"description\":\"Cascada de pagos sobre un extracto\",\n" +
                "               \"id\":\"z/sxjl1c\",\n" +
                "               \"relations\":[\n" +
                "                  {\n" +
                "                     \"contentType\":\"OBJECT\",\n" +
                "                     \"id\":\"OBJ0000000001\",\n" +
                "                     \"order\":\"1\"\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"businessSubtype\":\"MP\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"businessType\":\"MP\",\n" +
                "         \"actions\":[\n" +
                "            {\n" +
                "               \"configurationData\":[\n" +
                "                  {\n" +
                "                     \"dataSet\":[\n" +
                "                        {\n" +
                "                           \"id\":\"customer_name\",\n" +
                "                           \"value\":\"My Android\"\n" +
                "                        }\n" +
                "                     ],\n" +
                "                     \"id\":\"nnn\"\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"id\":\"customer_name\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"objects\":[\n" +
                "            {\n" +
                "               \"name\":\"zapOBJ0000000001\",\n" +
                "               \"description\":\"Objeto de Negocio de MP\",\n" +
                "               \"id\":\"OBJ0000000001\",\n" +
                "               \"businessSubtype\":\"MP\",\n" +
                "               \"contents\":[\n" +
                "                  {\n" +
                "                     \"id\":\"general_info\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"start_date\",\n" +
                "                           \"value\":\"15-oct-19\",\n" +
                "                           \"id\":\"start_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"payment_to_avoid_interest\",\n" +
                "                           \"value\":\"10,758.49\",\n" +
                "                           \"id\":\"payment_to_avoid_interest\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"minimum_payment\",\n" +
                "                           \"value\":\"805.12\",\n" +
                "                           \"id\":\"minimum_payment\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"pay_day_limit\",\n" +
                "                           \"value\":\"04-nov-19\",\n" +
                "                           \"id\":\"pay_day_limit\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"purchase_interest_rate\",\n" +
                "                           \"value\":\"4.38\",\n" +
                "                           \"id\":\"purchase_interest_rate\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"average_purchase_balance\",\n" +
                "                           \"value\":\"0.00\",\n" +
                "                           \"id\":\"average_purchase_balance\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"disposal_interest_rate\",\n" +
                "                           \"value\":\"4.38\",\n" +
                "                           \"id\":\"disposal_interest_rate\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"average_with_drawal_balance\",\n" +
                "                           \"value\":\"2,416.96\",\n" +
                "                           \"id\":\"average_with_drawal_balance\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_balance_average\",\n" +
                "                           \"value\":\"2,416.96\",\n" +
                "                           \"id\":\"sum_balance_average\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"ppngi_after_payments\",\n" +
                "                           \"value\":\"3,734.21\",\n" +
                "                           \"id\":\"ppngi_after_payments\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"number_of_payments_before_flp\",\n" +
                "                           \"value\":\"3\",\n" +
                "                           \"id\":\"number_of_payments_before_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"num_payments_before_after_flp\",\n" +
                "                           \"value\":\"6\",\n" +
                "                           \"id\":\"num_payments_before_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"num_returns_before_after_flp\",\n" +
                "                           \"value\":\"2\",\n" +
                "                           \"id\":\"num_returns_before_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"return_number_before_flp\",\n" +
                "                           \"value\":\"2\",\n" +
                "                           \"id\":\"return_number_before_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_of_payments_before_flp\",\n" +
                "                           \"value\":\"5,826.00\",\n" +
                "                           \"id\":\"sum_of_payments_before_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_of_returns_before_flp\",\n" +
                "                           \"value\":\"1,198.28\",\n" +
                "                           \"id\":\"sum_of_returns_before_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_payments_subs_before_flp\",\n" +
                "                           \"value\":\"7,024.28\",\n" +
                "                           \"id\":\"sum_payments_subs_before_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"minimum_payment_after_payments\",\n" +
                "                           \"value\":\"0.00\",\n" +
                "                           \"id\":\"minimum_payment_after_payments\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_of_payments_after_flp\",\n" +
                "                           \"value\":\"3,483.56\",\n" +
                "                           \"id\":\"sum_of_payments_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"number_of_payments_after_flp\",\n" +
                "                           \"value\":\"3\",\n" +
                "                           \"id\":\"number_of_payments_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"interest_of_the_period\",\n" +
                "                           \"value\":\"105.75\",\n" +
                "                           \"id\":\"interest_of_the_period\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"iva\",\n" +
                "                           \"value\":\"446.99\",\n" +
                "                           \"id\":\"iva\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_of_interest_and_iva\",\n" +
                "                           \"value\":\"552.74\",\n" +
                "                           \"id\":\"sum_of_interest_and_iva\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"new_ppngi\",\n" +
                "                           \"value\":\"22,969.92\",\n" +
                "                           \"id\":\"new_ppngi\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"new_minimum_payment\",\n" +
                "                           \"value\":\"1,493.04\",\n" +
                "                           \"id\":\"new_minimum_payment\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"new_payment_dead_line\",\n" +
                "                           \"value\":\"04-dic-19\",\n" +
                "                           \"id\":\"new_payment_dead_line\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"month_end_date\",\n" +
                "                           \"value\":\"diciembre\",\n" +
                "                           \"id\":\"month_end_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"year_end_date\",\n" +
                "                           \"value\":\"2019\",\n" +
                "                           \"id\":\"year_end_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"end_date\",\n" +
                "                           \"value\":\"14-nov-19\",\n" +
                "                           \"id\":\"end_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"month_start_date\",\n" +
                "                           \"value\":\"noviembre\",\n" +
                "                           \"id\":\"month_start_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"year_start_date\",\n" +
                "                           \"value\":\"2019\",\n" +
                "                           \"id\":\"year_start_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"day_cut_date\",\n" +
                "                           \"value\":\"14\",\n" +
                "                           \"id\":\"day_cut_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"extract_number\",\n" +
                "                           \"value\":\"2\",\n" +
                "                           \"id\":\"extract_number\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"available_balance\",\n" +
                "                           \"value\":\"13,510.65\",\n" +
                "                           \"id\":\"available_balance\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"final_balance\",\n" +
                "                           \"value\":\"31,789.35\",\n" +
                "                           \"id\":\"final_balance\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"total_balance\",\n" +
                "                           \"value\":\"45,300.00\",\n" +
                "                           \"id\":\"total_balance\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"number_of_the_flp_days\",\n" +
                "                           \"value\":\"20\",\n" +
                "                           \"id\":\"number_of_the_flp_days\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"day_indicator\",\n" +
                "                           \"value\":\"siguiente\",\n" +
                "                           \"id\":\"day_indicator\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_of_returns_after_flp\",\n" +
                "                           \"value\":\"0.00\",\n" +
                "                           \"id\":\"sum_of_returns_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_returns_before_after_flp\",\n" +
                "                           \"value\":\"1,198.28\",\n" +
                "                           \"id\":\"sum_returns_before_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"sum_payments_before_after_flp\",\n" +
                "                           \"value\":\"9,309.56\",\n" +
                "                           \"id\":\"sum_payments_before_after_flp\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"number_of_returns_after_flp\",\n" +
                "                           \"value\":\"0\",\n" +
                "                           \"id\":\"number_of_returns_after_flp\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"detail_of_the_ppngi\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.ACLARACION\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.ACLARACION\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.DISPO.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.DISPO.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTAS/COMIS.vigente\",\n" +
                "                           \"value\":\"312.0\",\n" +
                "                           \"id\":\"CUOTAS/COMIS.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTA.ANUALvigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTA.ANUALvigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.ORD.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.ORD.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REVOLV.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.REVOLV.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.REEST.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.REEST.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REES/PPI.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.REES/PPI.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"COMIS.PAGO.TARDIOvencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"COMIS.PAGO.TARDIOvencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.MORAT.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.MORAT.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"IVAvigente\",\n" +
                "                           \"value\":\"446.99\",\n" +
                "                           \"id\":\"IVAvigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.DISPO.vigente\",\n" +
                "                           \"value\":\"105.75\",\n" +
                "                           \"id\":\"INT.DISPO.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.ORD.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.ORD.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REVOLV.vigente\",\n" +
                "                           \"value\":\"9893.75\",\n" +
                "                           \"id\":\"CAP.REVOLV.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.REEST.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.REEST.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.MORAT.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.MORAT.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTA.ANUALvencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTA.ANUALvencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"COMIS.PAGO.TARDIOvigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"COMIS.PAGO.TARDIOvigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTAS/COMIS.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTAS/COMIS.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REES/PPI.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.REES/PPI.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"IVAvencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"IVAvencido\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"summary_of_the_ppngi\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"capital\",\n" +
                "                           \"value\":\"21,846.65\",\n" +
                "                           \"id\":\"capital\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"iva\",\n" +
                "                           \"value\":\"221.59\",\n" +
                "                           \"id\":\"iva\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"commissions\",\n" +
                "                           \"value\":\"0.00\",\n" +
                "                           \"id\":\"commissions\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"interests\",\n" +
                "                           \"value\":\"901.68\",\n" +
                "                           \"id\":\"interests\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"detail_of_the_new_ppngi\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.ACLARACION\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.ACLARACION\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.DISPO.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.DISPO.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTAS/COMIS.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTAS/COMIS.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTA.ANUALvigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTA.ANUALvigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.ORD.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.ORD.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REVOLV.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.REVOLV.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.REEST.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.REEST.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REES/PPI.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.REES/PPI.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"COMIS.PAGO.TARDIOvencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"COMIS.PAGO.TARDIOvencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.MORAT.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.MORAT.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"IVAvigente\",\n" +
                "                           \"value\":\"221.59\",\n" +
                "                           \"id\":\"IVAvigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.DISPO.vigente\",\n" +
                "                           \"value\":\"483.65\",\n" +
                "                           \"id\":\"INT.DISPO.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.ORD.vigente\",\n" +
                "                           \"value\":\"418.03\",\n" +
                "                           \"id\":\"INT.ORD.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REVOLV.vigente\",\n" +
                "                           \"value\":\"21846.65\",\n" +
                "                           \"id\":\"CAP.REVOLV.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.REEST.vigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.REEST.vigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"INT.MORAT.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"INT.MORAT.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTA.ANUALvencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTA.ANUALvencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"COMIS.PAGO.TARDIOvigente\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"COMIS.PAGO.TARDIOvigente\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CUOTAS/COMIS.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CUOTAS/COMIS.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"CAP.REES/PPI.vencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"CAP.REES/PPI.vencido\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"IVAvencido\",\n" +
                "                           \"value\":\"0.0\",\n" +
                "                           \"id\":\"IVAvencido\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_payments_before_flp_1\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"3,000.00\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"BMOVIL.PAG\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"18-oct-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PM\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PM\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_SP\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_SP\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PPNGI\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PPNGI\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_payments_before_flp_2\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"2,000.00\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"BMOVIL.PAG\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"03-nov-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PM\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PM\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_SP\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_SP\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PPNGI\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PPNGI\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_payments_before_flp_3\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"826.00\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"BMOVIL.PAG\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"30-oct-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PM\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PM\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_SP\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_SP\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PPNGI\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PPNGI\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_of_returns_before_flp_1\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"165.28\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"ABONO IVA CUOTA ANUAL \",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"16-oct-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PM\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PM\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_SP\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_SP\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PPNGI\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PPNGI\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_of_returns_before_flp_2\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"1,033.00\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"FELICIDADES  ABONO CUOTA ANUAL\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"16-oct-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PM\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PM\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_SP\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_SP\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"indicator_PPNGI\",\n" +
                "                           \"value\":\"SI\",\n" +
                "                           \"id\":\"indicator_PPNGI\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_of_payments_after_flp_1\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"50.00\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"BMOVIL.PAG\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"12-nov-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_of_payments_after_flp_2\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"2,933.56\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"BMOVIL.PAG\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"14-nov-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"id\":\"break_down_of_payments_after_flp_3\",\n" +
                "                     \"contentDetails\":[\n" +
                "                        {\n" +
                "                           \"name\":\"amount\",\n" +
                "                           \"value\":\"500.00\",\n" +
                "                           \"id\":\"amount\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"description\",\n" +
                "                           \"value\":\"SU PAGO EN PRACTICAJA REF 1111\",\n" +
                "                           \"id\":\"description\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                           \"name\":\"operation_date\",\n" +
                "                           \"value\":\"12-nov-19\",\n" +
                "                           \"id\":\"operation_date\"\n" +
                "                        }\n" +
                "                     ]\n" +
                "                  }\n" +
                "               ]\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        try {

            JSONObject obj = new JSONObject(jsonName);
            //estado de cuenta (flujo)
            String zid = "z/YYel1c";
            //zappar ar (flujo)
            zid = "z/hzLp1ccc";
            //blue2 soto
            zid = "z/cDzo1c";

            //otraprueba fer
           // zid = "z/sxjl1c";




            String data = zid + "?data=" + obj.toString();




            Intent iFW = new Intent(MainActivity.this,  clInit.getClassForIntent());
            iFW.putExtra(clInit.DATA, data);
            iFW.putExtra(clInit.SHOW_BAR, "false");

            startActivity(iFW);

        }
        catch (Exception e){
            Log.e("MainActivity", " malformed JSON: "+ e.getMessage());
        }

    }

    public void botonDeep(String mdata){
        boolean isCompatible = ZapparEmbed.isCompatible(mContext);

        try {

            if (isCompatible) {
                iZappar = new Intent(this, ZapparEmbed.getZapcodeClassForIntent());
                iZappar.putExtra(ZapparEmbed.EXTRA_LAUNCH_DEEP_LINK, mdata);
                iZappar.putExtra(ZapparEmbed.EXTRA_SHOW_FAVORITE_BUTTON, false);
                iZappar.putExtra(ZapparEmbed.EXTRA_SHOW_HISTORY_BUTTON, false);
                iZappar.putExtra(ZapparEmbed.EXTRA_BAR_COLOR, Color.rgb(0, 102, 204));
                iZappar.putExtra(ZapparEmbed.EXTRA_SHOW_FULL_SCREEN, true);
                iZappar.putExtra(ZapparEmbed.EXTRA_SHOW_RESCAN_BUTTON, false);

               startActivity(iZappar);

            }

        } catch (Throwable t) {
            Log.e("MainActivity", "Could not parse malformed JSON: \"" + mdata + "\"");
        }


    }

}
