/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.testrun.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface ServerSideTestRunService extends Service {

  ServiceCall<NotUsed, TestResults> runTests();

  @Override
  default Descriptor descriptor() {
    return named("serverside").withCalls(
        pathCall("/api/testrun",  this::runTests)
      ).withAutoAcl(true);
  }
}
