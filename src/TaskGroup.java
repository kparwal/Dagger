import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Keshav Parwal
 */
public class TaskGroup {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void runTasks() throws InterruptedException {
        // Logging config
        Logger logger = Logger.getLogger("DaggerLogger");
        logger.setLevel(Level.SEVERE);

        // Initialize executors
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        ExecutorCompletionService<Task> executorCompletionService = new ExecutorCompletionService<>(executorService);
        Map<Future, Task> futureTaskMap = new HashMap<>();
        int numTasks = tasks.size();

        // Add preliminary tasks to queue
        for (Task task : tasks) {
            if (task.getNumRemainingParents() == 0) {
                Future future = executorCompletionService.submit(task);
                futureTaskMap.put(future, task);
                logger.info("Added task " + task.getName());
            }
        }

        // Consumer loop for Futures
        while (numTasks > 0) {
            Future future = executorCompletionService.take();
            try {
                Object taskResult = future.get();
                numTasks--;
                Task finishedTask = futureTaskMap.get(future);
                logger.info("Finished Task: " + finishedTask.getName() + ". " + numTasks + " remaining.");
                for (Task task : tasks) {
                    if (task.relax(finishedTask, taskResult)) {
                        Future f = executorCompletionService.submit(task);
                        futureTaskMap.put(f, task);
                        logger.info("Added task " + task.getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("ERROR: TASKS ABORTED");
                executorService.shutdownNow();
                numTasks = 0;
            }
        }
        executorService.shutdown();
    }

    public void addTasks(Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
    }
}
