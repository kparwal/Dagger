import java.util.HashSet;

/**
 * Created by Keshav Parwal
 */
public class TestDriver {
    public static void main(String [] args) throws Exception {
        SleepTask secondTask = new SleepTask("Sleep2");
        SleepTask firstTask = new SleepTask("Sleep1");

        secondTask.setAncestors(firstTask);
        firstTask.setAncestors(secondTask);

        TaskGraph taskGraph = new TaskGraph();
        taskGraph.addTasks(firstTask, secondTask);
        taskGraph.addStartingSet(firstTask);

        taskGraph.runTasks();
    }
}
