package events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

public class MyEventSource {
	  private List<MyEventListener> listeners = 
			  Collections.synchronizedList(new ArrayList<MyEventListener>());
	  
	  public void addListener(MyEventListener listener)	{
	    listeners.add(listener);
	  }
	  
	  public void removeListener(MyEventListener listener)	{
	    listeners.remove(listener);
	  }

	  public void fireEvent()	{
	    EventObject event = new EventObject(this);
	    for(MyEventListener listner : listeners){
	    	listner.handle(event);
	    }
	  }
}

