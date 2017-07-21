/**
 * Created by Keshav Parwal
 */
public class SleepTask<T> extends Task {

    public SleepTask(String name, Task... parentTasks) {
        super(name);
    }

    @Override
    public ReturnValue<Integer> run() throws Exception {
        System.out.println("Sleeping for 3 seconds...");
        Thread.sleep(3000);
        System.out.println("Task complete: " + this.name);
        ReturnValue<Integer> sleepReturn;

        if (arguments.containsKey("sleepReturn")) {
            int returnInt = (int)arguments.get("sleepReturn") + 1;
            sleepReturn =  new ReturnValue<Integer>("sleepReturn", returnInt);
        } else {
            sleepReturn = new ReturnValue<Integer>("sleepReturn", 4);
        }
        System.out.println(name + " " + sleepReturn.getValue());
        return sleepReturn;
    }
}
