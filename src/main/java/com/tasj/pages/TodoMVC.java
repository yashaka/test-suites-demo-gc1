package com.tasj.pages;


import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TodoMVC {

    public static ElementsCollection tasks = $$("#todo-list>li");

    public static void openTodoMVC() {
        open("http://todomvc.com/examples/troopjs_require/#");
//        $("#new-todo").shouldBe(visible);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void add(String... taskTexts) {
        for(String text: taskTexts){
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    public static void assertTasksAre(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().find(".destroy").click();
    }

    public static void assertItemsLeft(int itemsLeft) {
        $("#todo-count>strong").shouldHave(exactText(String.valueOf(itemsLeft)));
    }

    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).find(".toggle").click();
    }

    public static void clearCompleted() {
        $("#clear-completed").click();
        $("#clear-completed").shouldBe(hidden);
    }

    public static void edit(String fromTaskText, String toTaskText) {
        tasks.find(exactText(fromTaskText)).find("label").doubleClick();
        tasks.find(cssClass("editing")).find(".edit").setValue(toTaskText).pressEnter();
    }

    public static void toggleAll() {
        $("#toggle-all").click();
    }

    public static void assertNoTasks() {
        tasks.shouldBe(empty);
    }
}