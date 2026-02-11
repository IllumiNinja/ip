package talkative;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_increasesSize() {
        TaskList list = new TaskList(new ArrayList<>());

        list.add(new Todo("read book"));

        assertEquals(1, list.size());
    }

    @Test
    public void removeTask_decreasesSize() {
        TaskList list = new TaskList(new ArrayList<>());
        list.add(new Todo("read book"));

        list.remove(0);

        assertEquals(0, list.size());
    }
}
