package com.ferris.observeer;

import java.util.List;

public interface ListemDbChange<T> {

	public void getChangeT(T t);
	public void getChageTs(List<T> ts);
	
}
