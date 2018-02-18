package Chapter_15;

public class TestThreads {

    public static void main(String[] args) {
        ThreadOne t1 = new ThreadOne();
        ThreadTwo t2 = new ThreadTwo();
        Thread one = new Thread(t1);
        Thread two = new Thread(t2);
        one.start();
        two.start();
    }
}

class ThreadOne implements Runnable {
    @Override
    public void run() {
        Accum a = Accum.getAcuum();
        for (int i = 0; i < 98; i++) {
            a.updateCounter(1000);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("First " + a.getCounter());
    }
}

class ThreadTwo implements Runnable {

    @Override
    public void run() {
        Accum a = Accum.getAcuum();
        for (int i = 0; i < 99; i++) {
            a.updateCounter(1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Second " + a.getCounter());
    }
}

class Accum {
    private static Accum a = new Accum();
    private int counter = 0;

    private Accum() { }

    public static Accum getAcuum(){
        return a;
    }

    public int  getCounter() {
        return counter;
    }

    public void updateCounter(int add){
        counter += add;
    }
}


