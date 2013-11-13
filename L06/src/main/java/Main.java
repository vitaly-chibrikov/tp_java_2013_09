import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.TimerTask;

import base.Context;

import time.TimeHelper;
import time.TimeService;
import time.TimeServiceForContext;
import vfs.VFS;
import vfs.VFSImpl;

@SuppressWarnings("StringConcatenationInsideStringBufferAppend")
public class Main {
	
	public static void main(String[] args) {
		randomExample();
		//timeExample();
		//timerExample();
		//contextExample();
		//vfsExample();
	}

	private static void vfsExample() {
		VFS vfs= new VFSImpl("");
		System.out.append("Current path: " + vfs.getAbsolutePath(""));
		Iterator<String> iter = vfs.getIterator("./"); 
		while(iter.hasNext()){
			System.out.append(iter.next() + "\n");
		}
	}

	private static void contextExample() {
		System.out.append("ENGLISH full time: " + TimeHelper.getUserTimeFull(Locale.ENGLISH) + '\n');	
		int timeMs = 5000;
		final Context context = new Context();
		TimeServiceForContext timeService = new TimeServiceForContext(context);
		timeService.start();
		timeService.sheduleTask(new TimerTask(){
			 public void run() {
				System.out.append("Timer run!\n");
				System.out.append("ENGLISH full time: " + TimeHelper.getUserTimeFull(Locale.ENGLISH) + '\n');	
				TimeServiceForContext.getFromContext(context).stop();
			 }
			
		}, timeMs);
	}


	private static void timerExample() {
		System.out.append("ENGLISH full time: " + TimeHelper.getUserTimeFull(Locale.ENGLISH) + '\n');	
		final int timeMs = 10000;
		TimeService.instance().start();
		TimeService.instance().sheduleTask(new TimerTask(){
			 public void run() {
				System.out.append("Timer run!\n");
				System.out.append("ENGLISH full time: " + TimeHelper.getUserTimeFull(Locale.ENGLISH) + '\n');	
				TimeService.instance().stop();
			 }
			
		}, timeMs);		
	}
	
	
	private static void randomExample() {
		Random rnd = new Random();
		System.out.append("R0: " + rnd.nextInt(100) + '\n');
		System.out.append("R1: " + rnd.nextInt(100) + '\n');
		System.out.append("R2: " + rnd.nextInt(100) + '\n');
		System.out.append('\n');
		
		Random rndOnLong = new Random(100500L);
		System.out.append("R(1L)0: " + rndOnLong.nextInt(100) + '\n');
		System.out.append("R(1L)1: " + rndOnLong.nextInt(100) + '\n');
		System.out.append("R(1L)2: " + rndOnLong.nextInt(100) + '\n');
		System.out.append('\n');
		
		Random rndOnTime = new Random(TimeHelper.getTimeInMs());
		System.out.append("R(Time)0: " + rndOnTime.nextInt(100) + '\n');
		System.out.append("R(Time)1: " + rndOnTime.nextInt(100) + '\n');
		System.out.append("R(Time)2: " + rndOnTime.nextInt(100) + '\n');
		System.out.append('\n');		
		
		System.out.append("Math.random(): " + Math.random() + '\n');
	}

	private static void timeExample() {
		System.out.append("milliseconds: " + TimeHelper.getTimeInMs() + '\n');
		System.out.append("POSIX: " + TimeHelper.getPOSIX() + '\n');
		System.out.append('\n');
		System.out.append("ENGLISH full date: " + TimeHelper.getUserDateFull(Locale.ENGLISH) + '\n');
		System.out.append("GERMAN full date: " + TimeHelper.getUserDateFull(Locale.GERMAN) + '\n');
		System.out.append("FRENCH full date: " + TimeHelper.getUserDateFull(Locale.FRENCH) + '\n');
		System.out.append('\n');
		System.out.append("ENGLISH short date: " + TimeHelper.getUserDateShort(Locale.ENGLISH) + '\n');
		System.out.append("GERMAN short date: " + TimeHelper.getUserDateShort(Locale.GERMAN) + '\n');
		System.out.append("FRENCH short date: " + TimeHelper.getUserDateShort(Locale.FRENCH) + '\n');
		System.out.append('\n');
		System.out.append("ENGLISH full time: " + TimeHelper.getUserTimeFull(Locale.ENGLISH) + '\n');
		System.out.append("GERMAN full time: " + TimeHelper.getUserTimeFull(Locale.GERMAN) + '\n');
		System.out.append("FRENCH full time: " + TimeHelper.getUserTimeFull(Locale.FRENCH) + '\n');
		System.out.append('\n');
	}

}
