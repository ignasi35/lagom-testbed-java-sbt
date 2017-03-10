/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.multiservice;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class Goodbye002ServiceImpl implements Goodbye002Service {


  @Inject
  public Goodbye002ServiceImpl() {
  }

  @Override
  public ServiceCall<NotUsed, String> hello(String id) {
    return request -> CompletableFuture.completedFuture(id);
  }

}
