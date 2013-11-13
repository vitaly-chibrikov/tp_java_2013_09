package time;

import java.util.Timer;
import java.util.TimerTask;

import base.Context;

public class TimeServiceForContext {
	
	public static TimeServiceForContext getFromContext(Context context){
		return (TimeServiceForContext) context.get(TimeServiceForContext.class);
	}
	
	public TimeServiceForContext(Context context){
		context.add(this.getClass(), this);
	}
	
	private Timer timer = null;
	
	public void start(){
		timer = new Timer();
	}
	
	public void stop(){
		if(timer != null){
			timer.cancel();
		}
	}
	
	public boolean sheduleTask(TimerTask task, long timeMs){
		if(timer!=null){
			timer.schedule(task, timeMs);
			return true;
		}
		return false;
	}
}
