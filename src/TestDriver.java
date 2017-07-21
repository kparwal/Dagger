import java.util.HashSet;

/**
 * Created by Keshav Parwal
 */
public class TestDriver {
    public static void main(String [] args) throws Exception {
        SleepTask secondTask = new SleepTask("Sleep2");
        SleepTask firstTask = new SleepTask("Sleep1");
        SleepTask thirdTask = new SleepTask("Sleep3");
        SleepTask fourthTask = new SleepTask("Sleep4");

        secondTask.setAncestors(firstTask);
        firstTask.setAncestors(secondTask);

        thirdTask.setAncestors(fourthTask);
        fourthTask.setAncestors(thirdTask);

        TaskGraph taskGraph = new TaskGraph();
        taskGraph.addTasks(firstTask, secondTask);
        taskGraph.addStartingSet(firstTask, fourthTask);

        taskGraph.runTasks();
    }
}
