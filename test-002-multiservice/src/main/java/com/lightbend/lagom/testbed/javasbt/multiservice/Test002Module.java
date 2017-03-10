/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.lightbend.lagom.testbed.javasbt.multiservice;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class Test002Module extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(
                serviceBinding(Hello002Service.class, Hello002ServiceImpl.class),
                serviceBinding(Goodbye002Service.class, Goodbye002ServiceImpl.class)
        );
    }
}
