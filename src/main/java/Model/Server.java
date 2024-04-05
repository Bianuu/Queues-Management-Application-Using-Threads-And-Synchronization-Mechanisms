package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    public BlockingQueue<Task> tasks;
    public AtomicInteger perioadaAsteptare;

    public Server() {
        tasks = new LinkedBlockingQueue<>();
        perioadaAsteptare = new AtomicInteger(0);
    }

    ////Această metodă este implementată din interfața Runnable. La fiecare secundă,
    // clientului din fața cozii îi este decrementat timpul de procesare. De asemenea,
    // timpul de procesare al cozii este decrementat în întregime. Dacă timpul de procesare este,
    // după decrementare, 0, clientul este scos din coada
    @Override
    public void run() {

        // ia urmatorul client din coada de asteptare
        // opreste firul pentru un timp egal cu timpul de procesare a clientului
        // descreste perioada de asteptare

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!tasks.isEmpty()) {
                tasks.element().timpServire--;
                perioadaAsteptare.set(perioadaAsteptare.decrementAndGet());
                if (tasks.element().timpServire == 0) {
                    try {
                        tasks.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void addTask(Task newTask) {

        // adauga client la coada de asteptare
        // creste perioada de asteptare

        try {
            tasks.put(newTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        perioadaAsteptare.set(perioadaAsteptare.intValue() + newTask.timpServire);
    }

    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Task t : tasks)
            string.append(t.toString()).append(" ");

        return string.toString();
    }

}
