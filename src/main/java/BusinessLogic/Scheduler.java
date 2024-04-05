package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    ///lista cu Ghisee
    List<Server> servers;
    ///lista cu thread-uri
    List<Thread> threads;
    Strategy strategia;

    Scheduler(int nServers) {
        servers = new ArrayList<>();
        threads = new ArrayList<>();
        strategia = new ConcreteSTrategyTime();

        for (int i = 0; i < nServers; i++) {
            Server server = new Server();
            servers.add(server);
            threads.add(new Thread(server));
        }
    }

    void alegeStrategia(SelectionPolicy policy) {
        if (policy == SelectionPolicy.timeStrategy)
            strategia = new ConcreteSTrategyTime();
    }

    void dispatchTask(Task task) {
        strategia.addTask(servers, task);
    }

    void runThreads() {
        for (Thread thread : threads)
            thread.start();
    }
}
