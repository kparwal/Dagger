import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by Keshav Parwal
 */
public abstract class Task implements Callable {

    private ArrayList<Task> parents;
    HashMap<String, Object> arguments = new HashMap<>();
    String name;

    public int getNumRemainingParents() {
        return numRemainingParents;
    }

    int numRemainingParents;

    public Task(String name, Task... parentTasks) {
        parents = new ArrayList<>(Arrays.asList(parentTasks));
        numRemainingParents = parentTasks.length;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumParents() {
        return parents.size();
    }

    public boolean relax(Task parent, Object argument) {
        if (argument != null && parent != null) {
            arguments.put(parent.getName(), argument);
        }
        numRemainingParents--;
        return (numRemainingParents == 0);
    }

    public Object call() throws Exception {
        Object returnObject = null;
        returnObject = this.run();
        return returnObject;
    }

    public abstract Object run() throws Exception;
}