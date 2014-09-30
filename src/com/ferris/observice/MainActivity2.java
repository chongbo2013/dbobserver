package com.ferris.observice;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ferris.beam.Murl;
import com.ferris.observeer.ListemDbChange;
import com.ferris.service.TestService;

public class MainActivity2 extends Activity implements ListemDbChange<Murl>{
	private TextView tv_2;
	private int index=0;
	private Button btn_2;
	private TestService testService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);
		tv_2=(TextView)findViewById(R.id.tv_2);
		btn_2=(Button)findViewById(R.id.btn_2);
		testService=TestService.getTestSservice(this);
		testService.registerObjserver(this);
		testService.getScrollData();
		btn_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index+=1;
				Murl  murl=new Murl();
				murl.setImage(index);
				murl.setTitle("title"+index);
				murl.setUrl("url"+index);
				testService.save(murl);
			}
		});
	}
	@Override
	public void getChangeT(Murl t) {
		// TODO Auto-generated method stub
		if(t!=null){
			tv_2.setText(t.getTitle());
		}
	}
	@Override
	public void getChageTs(List<Murl> ts) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer();
		if(ts==null){
			return;
		}
		for(int z=0;z<ts.size();z++){
			stringBuffer.append(ts.get(z).getTitle().toString()+"\r\n");
			
		}
		tv_2.setText(stringBuffer.toString());
	}
	



}
