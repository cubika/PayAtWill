package com.example.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SelectTypeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_type);
        ImageButton chargePhoneBTN=(ImageButton) findViewById(R.id.chargePhone);
        chargePhoneBTN.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(SelectTypeActivity.this,PhoneChargeActivity.class);
				startActivity(intent);
			}
        	
        });
    }
}
