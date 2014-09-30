package com.ferris.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public static  DBOpenHelper dbOpenHelper;
	public DBOpenHelper(Context context) {
		super(context, "chongbo.db", null,4);
		
	}

	public static DBOpenHelper getDBOpenHelper(Context context){
		if(dbOpenHelper==null){
			dbOpenHelper=new DBOpenHelper(context);
		}
		return dbOpenHelper;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE murl(id integer primary key autoincrement, title varchar(64), url varchar(120), image integer, aimage varchar(120), type integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    
    	
	}

}
