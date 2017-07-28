import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Keshav Parwal
 */
public abstract class Task implements Callable {

    private ArrayList<Task> ancestors;
    Map<String, Object> arguments = new ConcurrentHashMap<>();
    String name;
    int numRemainingParents;
    private ArrayList<Task> descendants;

    public ArrayList<Task> getDescendants() {
        return descendants;
    }

    public int getNumRemainingParents() {
        return numRemainingParents;
    }

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

    public boolean relax(Task parent, ReturnValue argument) {
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
        ReturnValue returnObject = null;
        returnObject = this.run();
        return returnObject;
    }

    public void printString(String message) {
        System.out.println(name + ": " + message);
    }

    public abstract ReturnValue run() throws Exception;
}