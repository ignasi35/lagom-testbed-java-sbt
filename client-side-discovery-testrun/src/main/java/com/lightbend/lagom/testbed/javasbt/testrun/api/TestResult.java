package com.lightbend.lagom.testbed.javasbt.testrun.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "test-result",
        defaultImpl = Void.class)
@JsonSubTypes({
        @JsonSubTypes.Type(TestResult.PassedTest.class),
        @JsonSubTypes.Type(TestResult.FailedTest.class)
})
public interface TestResult {

    @Value
    @JsonTypeName("pass")
    class PassedTest implements TestResult {
        String name;

        @JsonCreator
        public PassedTest(String name) {
            this.name = name;
        }
    }

    @Value
    @JsonTypeName("fail")
    class FailedTest implements TestResult {
        String name;
        String failureMesssage;

        @JsonCreator
        public FailedTest(String name, String failureMesssage) {
            this.failureMesssage = failureMesssage;
            this.name = name;
        }
    }

}
