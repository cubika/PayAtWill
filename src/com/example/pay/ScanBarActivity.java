package com.example.pay;

import com.example.pay.MipcaActivityCapture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanBarActivity extends Activity{
	private final static int SCANNIN_GREQUEST_CODE = 1;
	private TextView mTextView;
	private ImageView mImageView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_bar);
        mTextView = (TextView) findViewById(R.id.scan_result); 
		mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
        Intent intent = new Intent();
		intent.setClass(ScanBarActivity.this, MipcaActivityCapture.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				//显示扫描到的内容
				mTextView.setText(bundle.getString("result"));
				//显示
				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}
			break;
		}
    }
}
