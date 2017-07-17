# ThreadMaster - DAG Based Concurrency in Java
This is a small little library for creating a DAG of tasks to be run in parallel, where the DAG contains dependency information. Essentially, the unit of work in Dagger is the Task.

A task has a name and may have parent tasks it is dependent on. It receives arguments from its parents in a HashMap called `arguments`. The key is the name of the parent task, and the value is any arbitrary `Object`. Returning from `run()` automatically gives the relevant children the argument. You can create your own tasks by extending the abstract class `Task`.

Check out `src/TestDriver.java` for example usage!
