package com.ferris.observeer;


public interface SubjectBase {
	void registerObjserver(ObserverBase ob);
	void removeObserver(ObserverBase ob);
	void notifyObservers();
}