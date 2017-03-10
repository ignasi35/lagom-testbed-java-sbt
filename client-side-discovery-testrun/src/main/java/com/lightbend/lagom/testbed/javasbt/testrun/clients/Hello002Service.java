package com.lightbend.lagom.testbed.javasbt.testrun.clients;

import com.lightbend.lagom.testbed.javasbt.testrun.ServiceTemplate;


public interface Hello002Service extends ServiceTemplate {
    @Override
    default String name() {
        return "hello002";
    }
}
