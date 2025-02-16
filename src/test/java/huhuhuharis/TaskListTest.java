package huhuhuharis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private TaskList taskList;
    private Task t1;
    private Task t2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        t1 = new Todo("Task 1");
        t2 = new Deadline("Task 2", LocalDateTime.of(2025, 1, 11, 23, 59));
    }

    @Test
    public void testAddTask() {
        taskList.addTask(t1);
        assertEquals(1, taskList.getListCount());
        assertEquals(t1, taskList.getTask(0));
    }

    @Test
    public void testRemoveTask() {
        taskList.addTask(t1);
        taskList.addTask(t2);
        Task removedTask = taskList.removeTask(1);
        assertEquals(1, taskList.getListCount());
        assertEquals(t2, removedTask);
    }
}
