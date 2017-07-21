import java.util.*;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Keshav Parwal on 7/20/17.
 */

public class TaskGraph {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Set<Task> startingSet = new HashSet<>();

    public void runTasks() throws InterruptedException {
        // Logging config
        Logger logger = Logger.getLogger("DaggerLogger");
        logger.setLevel(Level.SEVERE);

        // Initialize executors
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        ExecutorCompletionService<Task> executorCompletionService = new ExecutorCompletionService<>(executorService);
        Map<Future<ReturnValue>, Task> futureTaskMap = new HashMap<>();
        int numTasks = tasks.size();

        // Add preliminary tasks to queue
        startingSet.forEach(task -> {
            Future<ReturnValue> future = executorCompletionService.submit(task);
            futureTaskMap.put(future, task);
            logger.info("Added task " + task.getName());
        });

        // Consumer loop for Futures
        while (futureTaskMap.size() > 0) {
            Future future = executorCompletionService.take();
            try {
                ReturnValue taskResult = (ReturnValue)future.get();
                Task finishedTask = futureTaskMap.get(future);
                futureTaskMap.remove(future);
                logger.info("Finished Task: " + finishedTask.getName() + ". " + numTasks + " remaining.");
                finishedTask.getDescendants().forEach(taskObj -> {
                    Task task = (Task)taskObj;
                    if (task.relax(finishedTask, taskResult)) {
                        Future f = executorCompletionService.submit(task);
                        futureTaskMap.put(f, task);
                        logger.info("Added task " + task.getName());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("ERROR: TASKS ABORTED");
                executorService.shutdownNow();
                break;
            }
        }
        executorService.shutdown();
    }

    public void addTasks(Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
    }

    public void addStartingSet(Task... tasks) {
        this.startingSet.addAll(Arrays.asList(tasks));
    }
}
