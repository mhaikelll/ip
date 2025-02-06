package Huhuhuharis;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskLists;
    private int listCount;

    public TaskList() {
        taskLists = new ArrayList<>();
        listCount = 0;
    }

    public TaskList(ArrayList<Task> taskLists) {
        this.taskLists = taskLists;
        this.listCount = taskLists.size();
    }

    public void addTask(Task task) {
        taskLists.add(task);
        listCount++;
    }

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

    public void addAll(ArrayList<Task> tasks) {
        if (tasks == null) {
            throw new NullPointerException("List cannot be null.");
        }
        tasks.addAll(taskLists);
    }
}

