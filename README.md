# Spark Spec

[![Build Status](https://travis-ci.org/leoromanovsky/spark-spec.svg?branch=master)](https://travis-ci.org/leoromanovsky/spark-spec)

This project is a collection of utilities to help test [Apache Spark](https://spark.apache.org) programs.

Run full integration tests of your jobs:

```
class SimpleExampleSpec extends SparkSpecUtils with ShouldMatchers {
  sparkTest("spark parallelize") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 2 == 0}.count
    println("Result:", result)
    result should be (5e5.toInt)
  }
}

> test-only com.strava.discovery.SimpleSparkTest
15/03/01 12:02:07 INFO SparkContext: Successfully started SparkContext
...
...
15/03/01 12:02:07 INFO SparkContext: Job finished: count at SimpleSparkTest.scala:9, took 0.206779 s
(Result:,500000)
15/03/01 12:02:08 INFO SparkContext: Successfully stopped SparkContext
[info] SimpleSparkTest:
[info] - spark filter
[info] Run completed in 3 seconds, 465 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 4 s, completed Mar 1, 2015 12:02:08 PM
```

In the example, a Spark cluster was launched in local mode, kept around for the duration of the specs and shutdown afterwards.

I've divided it into two directories:

* core - The library which a developer includes into their project.
* examples - A standalone sbt project meant to be fully transferable.

## Getting Started

In your Build.scala, at a new dependency: 

```
resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")

libraryDependencies ++= Seq("com.github.leoromanovsky" %% "spark-spec" % "0.0.1-SNAPSHOT" % "test")
```

The project is built against Scala v2.10.4.

## Example spec

For the ETL job `MyETLJob`:

```
object MyETLJob extends SparkJob {
  ...
  override def runJob(sc: SparkContext, config: Config): Long = {
    val outputWritePath = "foo"
  
    ...

    perform(rdd)
    
    ...
  }
  
  def perform(rdd: RDD[SomeCaseClass]): RDD[String] = {
    rdd.map { r => 
      s"${r.id},${r.userId},${r.distance},${r.elevationGain}"
    }
  }
}
```

Add a spec to test `MyETLJob` class:

```
package com.foo.etl

import com.github.leoromanovsky.sparkspec.SparkSpecUtils

import org.scalatest.ShouldMatchers
import com.foo.etl.MyETLJob

class MyETLJobSpec extends SparkSpecUtils with ShouldMatchers {
  sparkTest("my etl") {
    # Create input data
    val input = Seq(
      SomeCaseClass(1, 2, 3),
      SomeCaseClass(4, 5, 6),
    )
    val inputRDD = sc.parallelize(input)
    
    # Use the newly created RDD.
    val results = MyETLJob.perform(inputRDD, sc).collect()
    results.size should be (2)
  }
}
```

The test passed in a `SparkContext` and `Config` as `sc` and `config`, respectfully.

## Contributing

I welcome feedback, issue reports and pull requests.
