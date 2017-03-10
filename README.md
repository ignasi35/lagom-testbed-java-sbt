# Lagom TestBed for Java-SBT combination

A Lagom sample app to include example services to test integration between Lagom and ConductR or other orchestration tools.

This Lagom app exemplifies several combinations of:

 * number of locatable ServiceDescriptor's per Service
 * number of ServiceDescriptor's per Service (locatable or not)


## Development tips

Services shuold be kept to a minimum complexity:

 * Unless it's required for a specific tests, calls should only use `Method.GET` and return `String`
 * no persistence should be required
 * tests should be selfcontained ina single sbt project (unless cross-service is being tested)
 * tests should use unique package names
 * service names must be unique across all tests (unless collision resolution is being tested)
 * call paths must be unique across all tests (independtly of being public or not)
 * each tests should be listed in this readme with a brief explanation of motivation and sample `curl` commands to run it


## Test list

### 001 - Monoservice

A Simple Lagom Service with a single service (includes `MetricsService`)

     curl http://localhost:9000/api/hello001/bob


