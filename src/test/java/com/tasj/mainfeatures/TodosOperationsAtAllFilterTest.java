package com.tasj.mainfeatures;

import com.codeborne.selenide.Configuration;
import com.tasj.BaseTest;
import com.tasj.categories.Smoke;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.tasj.pages.TodoMVC.*;

/**
 * Created by yashaka on 7/1/15.
 */

public class TodosOperationsAtAllFilterTest extends BaseTest {

    @Before
    public void loadTodoMVCPage(){
        openTodoMVC();
    }

    @After
    public void clearTasks(){
        executeJavaScript("localStorage.clear()");
        open("http://todomvc.com/");
    }

    @Test
    @Category(Smoke.class)
    public void testTasksLifeCycle(){
        add("a", "b", "c", "d");

        edit("b", "b edited");

        toggle("b edited");
        toggle("c");
        toggle("b edited");
        clearCompleted();
        assertTasksAre("a", "b edited", "d");

        delete("b edited");
        assertTasksAre("a", "d");

        toggleAll();
        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testCreateTasks(){
        add("a", "b", "c");
        assertTasksAre("a", "b", "c");
        assertItemsLeft(3);
    }

    @Test
    public void testDeleteTask() {
        add("a", "b", "c");
        delete("b");
        assertTasksAre("a", "c");
        assertItemsLeft(2);
    }

    @Test
    public void testMarkTasksCompletedReopenAndClear() {
        add("a", "b", "c");
        toggle("a");
        toggle("c");
        assertItemsLeft(1);

        toggle("a");
        assertItemsLeft(2);

        clearCompleted();
        assertTasksAre("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testEditTask() {
        add("a", "b", "c");
        edit("b", "b edited");
        assertItemsLeft(3);
        assertTasksAre("a", "b edited", "c");
        assertItemsLeft(3);
    }

    @Test
    public void testMarkAllCompletedAndClear() {
        add("a", "b", "c");
        toggleAll();
        assertItemsLeft(0);
        clearCompleted();
        assertNoTasks();
    }
}
