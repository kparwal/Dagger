import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by Keshav Parwal
 */
public abstract class Task<T> implements Callable {

    private ArrayList<Task> ancestors;
    HashMap<String, T> arguments = new HashMap<>();
    String name;

    public ArrayList<Task> getDescendants() {
        return descendants;
    }

    private ArrayList<Task> descendants;

    public int getNumRemainingParents() {
        return numRemainingParents;
    }

    int numRemainingParents;

    public Task(String name) {
        descendants = new ArrayList<>();
        this.name = name;
    }

    public void setAncestors(Task... parentTasks) {
        ancestors = new ArrayList<>(Arrays.asList(parentTasks));
        for (Task ancestor : ancestors) {
            ancestor.descendants.add(this);
        }
        numRemainingParents = parentTasks.length;
    }

    public String getName() {
        return name;
    }

    public int getNumParents() {
        return ancestors.size();
    }

    public boolean relax(Task parent, ReturnValue<T> argument) {
        if (argument != null && parent != null) {
            if (ancestors.contains(parent)) {
                arguments.put(argument.getKey(), argument.getValue());
                numRemainingParents--;
                if (numRemainingParents == 0) {
                    numRemainingParents = ancestors.size();
                    return true;
                }
            }
        }
        return false;
    }

    public Object call() throws Exception {
        ReturnValue<T> returnObject = null;
        returnObject = this.run();
        return returnObject;
    }

    public abstract ReturnValue<T> run() throws Exception;
}