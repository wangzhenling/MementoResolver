package com.example.mementoresolver;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnChooseDate,btnAdd,btnQuery;
	EditText etDate,etSubject,etBody;
	LinearLayout title;
	ListView result;
	
	private ContentResolver contentResolver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etDate=(EditText) this.findViewById(R.id.etDate);
		etSubject=(EditText) this.findViewById(R.id.etSubject);
		etBody=(EditText) this.findViewById(R.id.etBody);
		title=(LinearLayout) this.findViewById(R.id.title);
		result=(ListView) this.findViewById(R.id.result);
		btnAdd=(Button) this.findViewById(R.id.btnAdd);
		btnQuery=(Button) this.findViewById(R.id.btnQuery);
		btnChooseDate=(Button) this.findViewById(R.id.btnChooseDate);
		
		contentResolver = getContentResolver();
		
		title.setVisibility(View.INVISIBLE);
		
		btnChooseDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Calendar c=Calendar.getInstance();
				new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int month,
							int day) {
						// TODO Auto-generated method stub
						
						etDate.setText(year+"-"+(month+1)+"-"+day);
					}
				}, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
				
			}
		});
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ContentValues values=new ContentValues();//创建一个ContentValues对象
				values.put(Mementos.Memento.SUBJECT, etSubject.getText().toString());
				values.put(Mementos.Memento.BODY, etBody.getText().toString());
				values.put(Mementos.Memento.DATE, etDate.getText().toString());//values中存值
				contentResolver.insert(Mementos.Memento.MEMENTOS_CONTENT_URI, values);
				Toast.makeText(MainActivity.this, "添加生词成功！", 1000).show();
				
			}
		});
		
		btnQuery.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Cursor cursor=contentResolver.query(Mementos.Memento.MEMENTOS_CONTENT_URI, null, null, null, null);
				//查询所有记录
				SimpleCursorAdapter resultAdapter=new SimpleCursorAdapter(
						MainActivity.this, R.layout.result, cursor, new String[]{
								Mementos.Memento._ID,Mementos.Memento.SUBJECT,Mementos.Memento.BODY,Mementos.Memento.DATE
						}, new int[]{R.id.memento_num,R.id.memento_subject,R.id.memento_body,R.id.memento_date});
				result.setAdapter(resultAdapter);//设置数据的显示方式
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
