import java.util.NoSuchElementException;

/**
 * Created by Keshav Parwal
 */
public class ExceptionTask extends Task {
    public ExceptionTask(String name, Task... parentTasks) {
        super(name);
    }

    @Override
    public ReturnValue<Integer> run() throws Exception {
        throw new NoSuchElementException("THIS IS WEIRD: " + name);
    }
}
