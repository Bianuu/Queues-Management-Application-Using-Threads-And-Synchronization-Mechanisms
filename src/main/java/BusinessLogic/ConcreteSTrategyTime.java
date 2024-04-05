package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteSTrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {

        int timpAsteptareMediu = servers.get(0).perioadaAsteptare.intValue();

        Server minim = servers.get(0);

        for (Server server : servers) {
            if ( timpAsteptareMediu> server.perioadaAsteptare.intValue()) {
                minim = server;
                timpAsteptareMediu= server.perioadaAsteptare.intValue();
            }
        }

        minim.addTask(task);
    }

}
