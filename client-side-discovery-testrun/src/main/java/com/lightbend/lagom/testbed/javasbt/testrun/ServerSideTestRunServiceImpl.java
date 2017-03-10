/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.testrun;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.testbed.javasbt.testrun.api.ServerSideTestRunService;
import com.lightbend.lagom.testbed.javasbt.testrun.api.TestResult;
import com.lightbend.lagom.testbed.javasbt.testrun.api.TestResults;
import com.lightbend.lagom.testbed.javasbt.testrun.clients.Goodbye002Service;
import com.lightbend.lagom.testbed.javasbt.testrun.clients.Hello001Service;
import com.lightbend.lagom.testbed.javasbt.testrun.clients.Hello002Service;
import org.pcollections.TreePVector;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ServerSideTestRunServiceImpl implements ServerSideTestRunService {

    private Hello001Service hello001;
    private Hello002Service hello002;
    private Goodbye002Service goodbye002;

    @Inject
    public ServerSideTestRunServiceImpl(
            Hello001Service hello001,
            Hello002Service hello002,
            Goodbye002Service goodbye002Service
    ) {
        this.hello001 = hello001;
        this.hello002 = hello002;
        this.goodbye002 = goodbye002Service;
    }


    @Override
    public ServiceCall<NotUsed, TestResults> runTests() {
        return req -> CompletableFuture.completedFuture(
                collectResults(
                        runAssertion("001-Monoservice: the primary service is available (client-side discovery)",
                                hello001.notusedStringString("bob"),
                                "bob"
                        ),
                        runAssertion("002-Multiservice: the primary service is available (client-side discovery)",
                                hello002.notusedStringString("bob"),
                                "bob"
                        ),
                        runAssertion("002-Multiservice: the secondary service is available (client-side discovery)",
                                goodbye002.notusedStringString("bob"),
                                "bob"
                        )
                )
        );
    }

    private <T> CompletionStage<TestResult> runAssertion(String testName, ServiceCall<NotUsed, T> call, T expected) {
        return call.invoke()
                .thenApply(
                        resp -> {
                            if (expected.equals(resp))
                                return new TestResult.PassedTest(testName);
                            else
                                return new TestResult.FailedTest(testName, "[" + resp.toString() + "] was not equal to [" + expected + "]");
                        }
                ).exceptionally(
                        t -> {
                            t.printStackTrace();
                            return new TestResult.FailedTest(testName, t.getMessage());
                        }
                );
    }

    private TestResults collectResults(CompletionStage<TestResult>... results) {
        List<TestResult> testResults = Arrays.asList(results)
                .parallelStream()
                .map(this::<TestResult>await)
                .collect(Collectors.toList());

        int testsPassed = (int) testResults.stream().filter(tr -> tr instanceof TestResult.PassedTest).count();
        return new TestResults(TreePVector.from(testResults), testResults.size(), testsPassed);
    }

    private <TR> TR await(CompletionStage<TR> cs) {
        try {
            return cs.toCompletableFuture().get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
