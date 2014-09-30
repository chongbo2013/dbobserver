package com.ferris.service;

import java.util.List;

import android.R.integer;
import android.database.Cursor;

import com.ferris.beam.Murl;

public interface DbServiceBase<T> {
	public  boolean cleartable();
	public  boolean save(T murl);
	public  boolean saveAll(List<T> murls);
	public  boolean delete(Integer id);
	public  boolean deletebyname(String name);
	public  boolean deletes(List<integer> delId);
	public  Boolean update(T murl);
	public  boolean updatebyname(Murl murl);
	public  T find(Integer id);
	public  T findByName(String fromname);
	public  List<T> getScrollData();
	public  List<T> getScrollDataByType( int type);
	public  Cursor getCursorScrollData(int offset, int maxResult);
	public  long getCount();
}
