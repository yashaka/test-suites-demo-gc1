package com.tasj;

import com.codeborne.selenide.Configuration;

/**
 * Created by yashaka on 7/2/15.
 */
public class BaseTest {

    {
        Configuration.timeout = Integer.parseInt(System.getProperty("driver.timeout"));
        Configuration.browser = System.getProperty("driver.browser");
    }
}
