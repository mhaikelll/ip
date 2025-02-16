package huhuhuharis;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskLists;
    private int listCount;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        taskLists = new ArrayList<>();
        listCount = 0;
    }

    /**
     * Constructs a TaskList object with a given list of tasks.
     *
     * @param taskLists The tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskLists) {
        this.taskLists = taskLists;
        this.listCount = taskLists.size();
    }

    /**
     * Handles the adding of a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskLists.add(task);
        listCount++;
    }

    /**
     * Handles the removal of a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        Task removedTask = taskLists.remove(index);
        listCount--;
        return removedTask;
    }

    public Task getTask(int index) {
        return taskLists.get(index);
    }

    public ArrayList<Task> getTasks() {
        return taskLists;
    }
    public int getListCount() {
        return listCount;
    }

    /**
     * Handles the displaying of all tasks in the task list.
     *
     * @return A string representing all tasks in the list.
     */
    public String fullList() {
        StringBuilder fullList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < listCount; i++) {
            fullList.append(i + 1).append(".").append(taskLists.get(i)).append("\n");
        }
        return fullList.toString();
    }

    public void markDone(int taskId) {
        taskLists.get(taskId).mark();
    }

    public void unmarkTask(int taskId) {
        taskLists.get(taskId).unmark();
    }

    /**
     * Handles the adding of all tasks from a given list to the current task list.
     *
     * @param tasks The tasks to be added to the current task list.
     */
    public void addAll(ArrayList<Task> tasks) {
        if (tasks == null) {
            throw new NullPointerException("List cannot be null.");
        }
        tasks.addAll(taskLists);
    }
}

