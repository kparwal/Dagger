import java.util.HashSet;

/**
 * Created by Keshav Parwal
 */
public class TestDriver {
    public static void main(String [] args) throws Exception {
        SleepTask firstTask = new SleepTask("Sleep1");
        SleepTask secondTask = new SleepTask("Sleep2");
        SleepTask thirdTask = new SleepTask("Sleep3");
        SleepTask fourthTask = new SleepTask("Sleep4");

        secondTask.setAncestors(firstTask);
        firstTask.setAncestors(fourthTask);

        thirdTask.setAncestors(secondTask);
        fourthTask.setAncestors(thirdTask);

        TaskGraph taskGraph = new TaskGraph();
        taskGraph.addTasks(firstTask, secondTask);
        taskGraph.addStartingSet(firstTask);

        taskGraph.runTasks();
    }
}
