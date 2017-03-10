/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.testrun;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface ServiceTemplate extends Service {

    String name();

    ServiceCall<NotUsed, String> notusedStringString(String id);

    @Override
    default Descriptor descriptor() {
        return named(name()).withCalls(
                pathCall("/api/" + name() + "/:id", this::notusedStringString)
        ).withAutoAcl(true);
    }


}
