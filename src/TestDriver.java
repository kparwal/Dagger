/**
 * Created by Keshav Parwal
 */
public class TestDriver {
    public static void main(String [] args) throws Exception {
        TaskGroup taskGroup = new TaskGroup();
        SleepTask firstTask = new SleepTask("Sleep1");
//        ExceptionTask firstTask = new ExceptionTask("Sleep1");
        SleepTask secondTask = new SleepTask("Sleep2", firstTask);
//        Task secondTask = new Task("Task2", firstTask) {
//            @Override
//            public Object run() throws Exception {
//                System.out.println(arguments.get(firstTask.getName()));
//                return 7;
//            }
//        };
        SleepTask thirdTask = new SleepTask("Sleep3", firstTask);
        taskGroup.addTasks(firstTask, secondTask, thirdTask);
        taskGroup.runTasks();
    }
}
