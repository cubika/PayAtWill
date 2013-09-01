package com.example.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class PhoneChargeActivity extends Activity {

	private static final int[] backIndex={R.drawable.list_switch,R.drawable.list_switch2};
	private ViewGroup list,chart;
	private ViewSwitcher switcher;
	private ImageButton swBtn;
	private int index=0;
	private static final int[] units={1,10,20,30,50,100,200,300,500};
	private static final String[] discounts={"1.20","10.04","20.10","29.09","49.98","99.98","199.60","299.50","498.00"};

	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_charge);
        switcher=(ViewSwitcher) findViewById(R.id.amount_switcher);
        list=(ViewGroup)getLayoutInflater().inflate(R.layout.amount_list, null);
        chart=(ViewGroup)getLayoutInflater().inflate(R.layout.amount_chart, null);
        switcher.addView(chart);
        switcher.addView(list);
        swBtn=(ImageButton) findViewById(R.id.switcher_btn);
        swBtn.setBackgroundResource(backIndex[index]);
        swBtn.setOnClickListener(switchListener);
        addItemToChart(getLayoutInflater());
        addItemToList();
    }
    
    OnClickListener switchListener=new OnClickListener(){

		@Override
		public void onClick(View v) {
			index=(index+1)%2;
			swBtn.setBackgroundResource(backIndex[index]);
			switcher.showNext();
		}
    	
    };

    AdapterView.OnItemClickListener itemListener=new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			View history=(View) list.getTag();
			if(history!=null)
				((ImageView)history).setImageState(new int[]{android.R.attr.state_empty},true);
			ImageView indicator=(ImageView)view.findViewById(R.id.list_item_indicator);
			indicator.setImageState(new int[]{android.R.attr.state_checked}, true);
			list.setTag(indicator);
		}
	};

    private void addItemToList()
    {
    	ListView lv=(ListView) list.findViewById(R.id.list_view);
    	lv.setAdapter(new CustomListAdapter(this));
    	list.findViewById(R.id.hint_info).setVisibility(8);
    	lv.setOnItemClickListener(itemListener);
    }
    
    
    private void addItemToChart(LayoutInflater paramLayoutInflater)
    {
    	LinearLayout ll=null;
    	for(int i=0;i<units.length;i++){
    		int amount=units[i];
    		String discount=discounts[i];
    		if(i%3==0){
    			ll=new LinearLayout(this);
    			ll.setPadding(5, 0, 5, 0);
    			LinearLayout contentView=(LinearLayout)chart.findViewById(R.id.chart_content_view);
    			contentView.addView(ll);
    		}
    		View amountChartView=paramLayoutInflater.inflate(R.layout.amount_chart_item, null);
    		TextView amtv=(TextView) amountChartView.findViewById(R.id.chart_amount);
    		amtv.setText(amount+"Ôª");
    		TextView ditv=(TextView) amountChartView.findViewById(R.id.chart_discount);
    		ditv.setText(discount);
    		ll.addView(amountChartView,new LinearLayout.LayoutParams(-1, -2, 1.0F));
    		chart.findViewById(R.id.chart_hint_info).setVisibility(8);
    		//amountChartView.findViewById(R.id.chart_content).addOnClickListener();
    	}
    }
    
    class CustomListAdapter extends BaseAdapter {   
        private LayoutInflater mInflater;   
        private Context mContext = null;   
        public CustomListAdapter(Context context) {   
            mContext = context;   
            mInflater = LayoutInflater.from(mContext);   
        }   
        public Object getItem(int arg0) {   
            return null;   
        }   
        public long getItemId(int position) {   
            return position;   
        }   
        public int getCount() {   
            return units.length;   
        }   
        public View getView(int position, View convertView,   
                android.view.ViewGroup parent) {   
            if (convertView == null) {   
                convertView = mInflater.inflate(R.layout.amount_list_item, null);   
            }   
            ((TextView)(convertView.findViewById(R.id.amount))).setText("³ä"+units[position]+"Ôª");
            ((TextView)(convertView.findViewById(R.id.discount))).setText("¸¶"+discounts[position]+"Ôª");
            return convertView;   
        }   
    }   
}
