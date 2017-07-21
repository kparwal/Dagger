# ThreadMaster - Graph Based Concurrency in Java
This is a small little library for creating a Graph of tasks to be run in parallel, where the Graph contains dependency information. Essentially, the unit of work in ThreadMaster is the Task.

A task has a name and may have parent tasks it is dependent on. It receives arguments from its parents in a Map called `arguments`. The key is the name of the parent task, and the value is any arbitrary `Object`.
Returning a `ReturnValue` object from `run()` automatically gives the relevant children the argument. You can create your own tasks by extending the abstract class `Task` and implementing `run()`.

Check out `src/TestDriver.java` and `src/SleepTask.java` for example usage!
