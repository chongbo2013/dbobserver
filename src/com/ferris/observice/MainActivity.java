package com.ferris.observice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ferris.beam.Murl;
import com.ferris.observeer.ListemDbChange;
import com.ferris.service.TestService;

public class MainActivity extends Activity implements ListemDbChange<Murl>{
	private TextView tv_show;
	private Button add,list;
	private List<Murl> murls=new ArrayList<Murl>();
	private TestService testService;
	private int index=0;
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(testService!=null){
			testService.registerObjserver(this);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		testService=TestService.getTestSservice(this);
		testService.registerObjserver(this);
		tv_show = (TextView) findViewById(R.id.tv_show);
		add = (Button) findViewById(R.id.add);
		list = (Button) findViewById(R.id.list);
		
		add.setOnClickListener(new OnClickListener() {
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
	
		list.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setAction("com.ferris.testactivity2");
				startActivity(intent);
			}
		});
		

	}
	@Override
	public void getChangeT(Murl t) {
		// TODO Auto-generated method stub
		if(t!=null){
			tv_show.setText(t.getTitle());
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
			stringBuffer.append(murls.get(z).getTitle().toString()+"\r\n");
			
		}
		tv_show.setText(stringBuffer.toString());
	}
	

}
