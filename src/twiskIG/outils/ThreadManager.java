package twiskIG.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadManager {
    private ArrayList<Thread> lesThreads;
    public static ThreadManager instance = new ThreadManager();

    private ThreadManager(){
        lesThreads = new ArrayList<>();
    }

    public static ThreadManager getInstance(){
        return instance;
    }

    public void lancer(Task task){
        Thread thread = new Thread(task);
        lesThreads.add(thread);
        thread.start();
    }

    public void detruireTout(){
        for (Thread thr: lesThreads
             ) {
            thr.interrupt();
        }
        lesThreads = new ArrayList<>();
    }
}
