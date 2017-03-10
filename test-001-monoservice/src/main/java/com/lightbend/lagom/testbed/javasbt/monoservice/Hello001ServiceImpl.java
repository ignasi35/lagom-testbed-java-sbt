/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.monoservice;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class Hello001ServiceImpl implements Hello001Service {


  @Inject
  public Hello001ServiceImpl() {
  }

  @Override
  public ServiceCall<NotUsed, String> hello(String id) {
    return request -> CompletableFuture.completedFuture(id);
  }

}
