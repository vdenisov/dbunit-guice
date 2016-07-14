package org.plukh.dbunitguice.dbunit;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestClassWatcher extends TestWatcher {
    private Class<?> testClass;
    @Override
    protected void starting(Description description) {
        testClass = description.getTestClass();
    }

    public Class<?> getTestClass() {
        return testClass;
    }
}
