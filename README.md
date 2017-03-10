# Lagom TestBed for Java-SBT combination

A Lagom sample app to include example services to test integration between Lagom and ConductR or other orchestration tools.

This Lagom app exemplifies several combinations of:

 * number of locatable ServiceDescriptor's per Service
 * number of ServiceDescriptor's per Service (locatable or not)
 * TODO: more combinations (?)

## Test types

The execution of the tests considers two different scenarios:

 * server-side discovery: try to consume an API from the outside world via the Service Gateway. These tests only make sense for API with ACL. Server-side discovery tests are implemented as a separate process (currently a BASH script) that will `curl` into several endpoints through the Service Gateway. See [server-side-discovery-testrun.sh](bin/server-side-discovery-testrun.sh)

 * client-side discovery: simulate inter-service communication with Service Locator lookup. A [special service](client-side-discovery-testrun) is provided that consumes locatable services in the farm. This service is implemeted so that there's no binary dependency with consumed services, it would easily be reused when implementing this repo for LagomScala. Tests are run in parallel and results are gathered and presented via the Service Gateway using a JSON response of the form:

```json
{
  "results": [
    {
      "test-result": "pass",
      "name": "001-Monoservice: the primary service is available (client-side discovery)"
    },
    {
      "test-result": "pass",
      "name": "002-Multiservice: the primary service is available (client-side discovery)"
    },
    {
      "test-result": "fail",
      "name": "002-Multiservice: the secondary service is available (client-side discovery)",
      "failureMesssage": "java.net.ConnectException: Connection refused: /192.168.10.3:10166"
    }
  ],
  "testsRun": 3,
  "passedCount": 2
}
```

 * **TODO**: consume non-locatable service. Using some form of custom method ---depending on the environment--- there should be a Lagom service that located all instances and stressed their non-locatable services.

## Running

### Lagom dev mode

Clone the repo and use `sbt runAll` to get the system started locally in Lagom's Dev Mode. On a separate terminal use

    bin/server-side-discovery-testrun.sh
    curl -s http://localhost:9000/api/testrun


### ConductR

Clone the repom and use `sbt install` to get the system started locally in a running [ConductR sandbox](http://www.lagomframework.com/documentation/1.3.x/java/ConductRSbt.html#Run-it-all). On a separate terminal use

    bin/server-side-discovery-testrun.sh conductr
    curl -s http://192.168.10.1:9000/api/testrun


 - - - - - - - - 

## Test list

### 001 - Monoservice

A Simple Lagom Service with a single locatable service with ACLS (includes `MetricsService`).

     curl http://localhost:9000/api/hello001/bob


### 002 - Multiservice

A Lagom Service with a two locatable services (both with ACLs): "hello002" and "goodbye002" (includes `MetricsService`).

     curl http://localhost:9000/api/hello002/bob
     curl http://localhost:9000/api/goodbye002/bob


 - - - - - - - - 


## Development tips

Services shuold be kept to a minimum complexity:

 * Unless it's required for a specific tests, calls should only use `Method.GET` and return `String`
 * no persistence should be required
 * tests should be selfcontained ina single sbt project (unless cross-service is being tested)
 * tests should use unique package names
 * service names must be unique across all tests (unless collision resolution is being tested)
 * call paths must be unique across all tests (independtly of being public or not)
 * each tests should be listed in this readme with a brief explanation of motivation and sample `curl` commands to run it
