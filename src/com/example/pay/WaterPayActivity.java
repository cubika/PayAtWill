package com.example.pay;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class WaterPayActivity extends FragmentActivity{

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_pay);
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);  
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator(addIndicator("水费")).setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
        		.setIndicator(addIndicator("电费")).setContent(R.id.tab2));
    }
	
	private View addIndicator(String text){
        View view = getLayoutInflater().inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
	}

}
