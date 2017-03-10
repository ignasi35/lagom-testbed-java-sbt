package com.lightbend.lagom.testbed.javasbt.testrun.api;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.pcollections.PSequence;
import lombok.Value;

@Value
public class TestResults {

    private PSequence<TestResult> results;
    private int testsPassed;
    private int testsRun;


    @JsonCreator
    public TestResults(PSequence<TestResult> results, int testsPassed, int testsRun) {
        this.results = results;
        this.testsPassed = testsPassed;
        this.testsRun = testsRun;
    }

}
