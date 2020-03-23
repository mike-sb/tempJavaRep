package com.company;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;

/* --------------------------------------------------- *
 * All tests in one suite.                             *
 * Run only this test class.                           *
 * You can run only some of them,                      *
 * just comment the tests you don't want to run.       *
 * --------------------------------------------------- */

@RunWith(Suite.class)
@SuiteClasses({
    TaskTest.class,
    CloneTaskTest.class,
    EqualsTaskTest.class,
    TaskListTest.class,
    CloneListTest.class,
    EqualsListTest.class
})
public class AllTests { }

