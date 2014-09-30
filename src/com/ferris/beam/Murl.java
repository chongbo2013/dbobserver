package com.ferris.beam;

import com.ferris.observeer.ObserverBase;
import com.ferris.observeer.SubjectBase;

public class Murl implements ObserverBase{
	private int id;
	private String title;
	private String url;
	private int image;
	private String aimage;
	private int type;
	private UpdateContext updateContext;
	public UpdateContext getUpdateContext() {
		return updateContext;
	}

	public void setUpdateContext(UpdateContext updateContext) {
		this.updateContext = updateContext;
	}

	public Murl(int id,String title,String url,int image,String aimage,int type){
		this.id=id;
		this.title=title;
		this.url=url;
		this.image=image;
		this.aimage=aimage;
		this.type=type;
	}

	public Murl() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getAimage() {
		return aimage;
	}

	public void setAimage(String aimage) {
		this.aimage = aimage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void update(SubjectBase subject) {
		// TODO Auto-generated method stub
		if(subject!=null||updateContext!=null){
			updateContext.UpdateNow(subject);
		}
		
	}

	public interface UpdateContext{
		public void UpdateNow(SubjectBase subject);
	}
}
