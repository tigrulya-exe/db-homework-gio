package university.homework.state;

import java.util.ArrayList;
import java.util.List;

public class ExecutionState {
    private final List<Worker> createdWorkers = new ArrayList<>();

    public void addWorker(Worker worker) {
        createdWorkers.add(worker);
    }

    public List<Worker> getCreatedWorkers() {
        return createdWorkers;
    }

    public void clearCreatedWorkers() {
        createdWorkers.clear();
    }
}
