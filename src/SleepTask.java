import javax.print.attribute.IntegerSyntax;

/**
 * Created by Keshav Parwal
 */
public class SleepTask extends Task {

    public SleepTask(String name) {
        super(name);
    }

    @Override
    public ReturnValue<Integer> run() throws Exception {
        long sleepTime = (long) (Math.random() * 5000);
        this.printString(" Sleeping for " + sleepTime/1000. + " seconds...");
        Thread.sleep(sleepTime);

        ReturnValue<Integer> sleepReturn;

        if (arguments.containsKey("sleepReturn")) {
            int returnInt = (int)arguments.get("sleepReturn") + 1;
            sleepReturn =  new ReturnValue<>("sleepReturn", returnInt);
        } else {
            sleepReturn = new ReturnValue<>("sleepReturn", 1);
        }
        printString(Integer.toString(sleepReturn.getValue()));
        return sleepReturn;
    }
}