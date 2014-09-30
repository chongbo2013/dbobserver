package com.ferris.service;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ferris.beam.Murl;
import com.ferris.db.DBOpenHelper;
import com.ferris.observeer.ListemDbChange;
import com.ferris.observeer.ObserverBase;
import com.ferris.observeer.SubjectBase;

public class TestService implements DbServiceBase<Murl> {
	private DBOpenHelper dbOpenHelper;
	private Context context;


	public static TestService testService;
	
	public static TestService getTestSservice(Context context1){
		
		if(testService==null){
			testService=new TestService(context1);
		}
		return testService;
		
	}
	
	
	public TestService(Context context1) {
		// TODO Auto-generated constructor stub
		this.context = context1;
		this.dbOpenHelper = DBOpenHelper.getDBOpenHelper(context1);
	}



	/**
	 * ��Ӽ�¼
	 * 
	 * @param user
	 *            DELETE FROM TableName
	 */
	public boolean cleartable() {

		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();
			db.execSQL("DELETE FROM murl");
			flag = true;
			performNotify(null, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;

	}

	public boolean save(Murl murl) {

		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();

			db.execSQL(
					"insert into murl(title, url, image,aimage,type) values(?,?,?,?,?)",
					new Object[] { murl.getTitle(), murl.getUrl(),
							murl.getImage(), murl.getAimage(), murl.getType() });
			flag = true;
			performNotify(null, murl);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;

	}

	/**
	 * �������
	 * 
	 * @param user
	 */
	public boolean saveAll(List<Murl> murls) {

		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();
			db.beginTransaction(); // �ֶ����ÿ�ʼ����
			for (Murl murl : murls) {
				db.execSQL(
						"insert into murl(title, url, image,aimage,type) values(?,?,?,?,?)",
						new Object[] { murl.getTitle(), murl.getUrl(),
								murl.getImage(), murl.getAimage(),
								murl.getType() });
			}
			db.setTransactionSuccessful(); // ����������ɹ��������û��Զ��ع����ύ

			db.endTransaction(); // �������

			flag = true;
			performNotify(murls, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;

	}

	/**
	 * ɾ����¼
	 * 
	 * @param id
	 *            ��¼ID
	 */
	public boolean delete(Integer id) {

		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();
			db.execSQL("delete from murl where id=?", new Object[] { id });
			flag = true;
			performNotify(null, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;

	}

	/**
	 * ɾ����¼����name
	 * 
	 * @param id
	 *            ��¼name
	 */
	public boolean deletebyname(String name) {
		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();
			db.execSQL("delete from murl where name=?", new Object[] { name });
			flag = true;
			performNotify(null, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param id
	 *            ��¼ID
	 */
	public boolean deletes(List<integer> delId) {

		boolean flag = false;
		SQLiteDatabase db = null;
		try {

			db = dbOpenHelper.getWritableDatabase();
			db.beginTransaction(); // �ֶ����ÿ�ʼ����

			for (int i = 0; i < delId.size(); i++) {
				db.execSQL("delete from murl where id=?",
						new Object[] { delId.get(i) });
			}
			db.setTransactionSuccessful(); // ����������ɹ��������û��Զ��ع����ύ

			db.endTransaction(); // �������
			flag = true;
			performNotify(null, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;

	}

	/**
	 * ���¼�¼ ͨ��id
	 * 
	 * @param
	 */

	public Boolean update(Murl murl) {

		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();
			db.execSQL(
					"update murl set title=?,url=?,image=?,aimage=?,type=? where id=?",
					new Object[] { murl.getTitle(), murl.getUrl(),
							murl.getImage(), murl.getAimage(), murl.getType(),
							murl.getId() });
			flag = true;
			performNotify(null, murl);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;

	}

	/**
	 * ���¼�¼�����޸�
	 * 
	 * @param
	 */
	public boolean updatebyname(Murl murl) {
		boolean flag = false;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getWritableDatabase();
			db.execSQL(
					"update murl set title=?,url=?,image=?,aimage=?,type=? where title=?",
					new Object[] { murl.getTitle(), murl.getUrl(),
							murl.getImage(), murl.getAimage(), murl.getType(),
							murl.getTitle() });
			flag = true;
			performNotify(null, murl);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return flag;
	}

	/**
	 * ��ѯ��¼
	 * 
	 * @param id
	 *            ��¼ID
	 * @return
	 */

	public Murl find(Integer id) {

		Murl newmurl = null;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from murl where id=?",
					new String[] { id.toString() });

			if (cursor.moveToFirst()) {
				int id2 = cursor.getInt(cursor.getColumnIndex("id"));
				String title = cursor.getString(cursor.getColumnIndex("title"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				int image = cursor.getInt(cursor.getColumnIndex("image"));
				String aimage = cursor.getString(cursor
						.getColumnIndex("aimage"));
				int type = cursor.getInt(cursor.getColumnIndex("type"));

				newmurl = new Murl(id2, title, url, image, aimage, type);
			}
			cursor.close();
			performNotify(null, newmurl);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}

		}

		return newmurl;
	}

	/**
	 * ��ѯ��¼
	 * 
	 * @param ���Ʋ�
	 * @return
	 */
	public Murl findByName(String fromname) {
		Murl newmurl = null;
		SQLiteDatabase db = null;
		try {
			db = dbOpenHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from murl where id=?",
					new String[] { fromname });

			if (cursor.moveToFirst()) {
				int id2 = cursor.getInt(cursor.getColumnIndex("id"));
				String title = cursor.getString(cursor.getColumnIndex("title"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				int image = cursor.getInt(cursor.getColumnIndex("image"));
				String aimage = cursor.getString(cursor
						.getColumnIndex("aimage"));
				int type = cursor.getInt(cursor.getColumnIndex("type"));

				newmurl = new Murl(id2, title, url, image, aimage, type);
			}
			cursor.close();
			performNotify(null, newmurl);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}

		}

		return newmurl;
	}

	/**
	 * ��ҳ��ȡ��¼
	 * 
	 * @param offset
	 *            ����ǰ���������¼
	 * @param maxResult
	 *            ÿҳ��ȡ��������¼
	 * @return
	 */
	// private int id;
	// private String title;
	// private String url;
	// private int image;
	// private String aimage;
	public List<Murl> getScrollData() {

		List<Murl> murls = null;
		SQLiteDatabase db = null;
		try {
			murls = new ArrayList<Murl>();
			db = dbOpenHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from murl order by id desc ",
					null);
			while (cursor.moveToNext()) {
				Murl murl = new Murl();
				int id2 = cursor.getInt(cursor.getColumnIndex("id"));
				String title = cursor.getString(cursor.getColumnIndex("title"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				int image = cursor.getInt(cursor.getColumnIndex("image"));
				String aimage = cursor.getString(cursor
						.getColumnIndex("aimage"));
				int type = cursor.getInt(cursor.getColumnIndex("type"));
				murl.setId(id2);
				murl.setTitle(title);
				murl.setUrl(url);
				murl.setImage(image);
				murl.setAimage(aimage);
				murl.setType(type);

				murls.add(murl);
				murl = null;
			}
			cursor.close();
			performNotify(murls, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}

		}

		return murls;
	}

	public List<Murl> getScrollDataByType(int type) {

		List<Murl> murls = null;
		SQLiteDatabase db = null;
		try {
			murls = new ArrayList<Murl>();
			db = dbOpenHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from murl where type=? ",
					new String[] { String.valueOf(type) });

			while (cursor.moveToNext()) {
				Murl murl = new Murl();
				int id2 = cursor.getInt(cursor.getColumnIndex("id"));
				String title = cursor.getString(cursor.getColumnIndex("title"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				int image = cursor.getInt(cursor.getColumnIndex("image"));
				String aimage = cursor.getString(cursor
						.getColumnIndex("aimage"));
				int type2 = cursor.getInt(cursor.getColumnIndex("type"));
				murl.setId(id2);
				murl.setTitle(title);
				murl.setUrl(url);
				murl.setImage(image);
				murl.setAimage(aimage);
				murl.setType(type2);

				murls.add(murl);
				murl = null;
			}
			cursor.close();

			performNotify(murls, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (db != null) {
				db.close();
			}

		}

		return murls;
	}

	/**
	 * ��ҳ��ȡ��¼
	 * 
	 * @param offset
	 *            ����ǰ���������¼
	 * @param maxResult
	 *            ÿҳ��ȡ��������¼
	 * @return
	 */
	public Cursor getCursorScrollData(int offset, int maxResult) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select personid as _id,name,phone,amount from person order by personid asc limit ?,?",
						new String[] { String.valueOf(offset),
								String.valueOf(maxResult) });
		return cursor;
	}

	/**
	 * ��ȡ��¼����
	 * 
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}

	private List<ListemDbChange<Murl>> obList = new ArrayList<ListemDbChange<Murl>>();

	private boolean isNotify = true;


	private static final Object lock = new Object();

	public void performNotify(List<Murl> ts, Murl t) {
		synchronized (lock) {
			if (isNotify != true) {
				return;
			}

			if (obList.size() <= 0) {
				return;
			}
			for (ListemDbChange<Murl> ob : obList) {
				if (ts != null) {
					ob.getChageTs(ts);
				}
				if (t != null) {
					ob.getChangeT(t);
				}

			}
		}

	}

	public void registerObjserver(ListemDbChange<Murl> ob) {
		// TODO Auto-generated method stub
		if(obList==null){
			return;
		}
		if(!obList.contains(ob)){
			obList.add(ob);
		}
		
	}

	public void removeObserver(ListemDbChange<Murl> ob) {
		// TODO Auto-generated method stub
		if(obList==null){
			return;
		}
		if(obList.contains(ob)){
			obList.remove(ob);
		}
		
	}

}