/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.multiservice;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface Goodbye002Service extends Service {

    ServiceCall<NotUsed, String> goodbye(String id);

    @Override
    default Descriptor descriptor() {
        return named("goodbye002").withCalls(
                pathCall("/api/goodbye002/:id", this::goodbye)
        ).withAutoAcl(true);
    }
}
