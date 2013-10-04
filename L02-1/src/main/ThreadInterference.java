package main;

public final class ThreadInterference extends Thread {
    private static final int HundredMillion = 100000000;
    private static int counter = 0;
    private static int i = 0;
    private static Object lock = new Object();

    public static void example() throws InterruptedException {
        (new ThreadInterference()).start();
        (new ThreadInterference()).start();
    }

    public void run() {
        while (counter < HundredMillion) {
            synchronized (lock) {
                counter++;
                //i++;
            }
            i++;
        }
        System.out.println(" i: " + i + " counter: " + counter);
    }

}
