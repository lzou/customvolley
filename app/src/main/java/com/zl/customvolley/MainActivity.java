package com.zl.customvolley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zl.library.VolleyErrorHelper;

public class MainActivity extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        textView = (TextView) findViewById(R.id.text);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData(){
        HttpRequestManager.getInstance().orderList(new Response.Listener<DouBanRadioBean>() {
            @Override
            public void onResponse(DouBanRadioBean response) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0 ; i < response.getChannels().size(); i++){
                    stringBuilder.append(response.getChannels().get(i).getName());
                    stringBuilder.append("\t");
                }
                textView.setText(stringBuilder.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyErrorHelper.showErrorMessage(MainActivity.this,error);
            }
        }).commit(MainActivity.this);
    }
}
