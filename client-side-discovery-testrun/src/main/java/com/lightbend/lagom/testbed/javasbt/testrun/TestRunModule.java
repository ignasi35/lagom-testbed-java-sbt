/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.testrun;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.lightbend.lagom.testbed.javasbt.testrun.api.ServerSideTestRunService;
import com.lightbend.lagom.testbed.javasbt.testrun.clients.Goodbye002Service;
import com.lightbend.lagom.testbed.javasbt.testrun.clients.Hello001Service;
import com.lightbend.lagom.testbed.javasbt.testrun.clients.Hello002Service;

public class TestRunModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(
                serviceBinding(ServerSideTestRunService.class, ServerSideTestRunServiceImpl.class)
        );
        bindClient(Hello001Service.class);
        bindClient(Hello002Service.class);
        bindClient(Goodbye002Service.class);
    }

}
