/**
 * Created by Keshav Parwal
 */
public class SleepTask extends Task {

    public SleepTask(String name, Task... parentTasks) {
        super(name, parentTasks);
    }

    @Override
    public Object run() throws Exception {
        System.out.println("Sleeping for 3 seconds...");
        Thread.sleep(3000);
        System.out.println("Task complete: " + this.name);
        return 4;
    }
}
