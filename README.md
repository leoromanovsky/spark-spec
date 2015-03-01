# Spark Spec

This project is a collection of utility classes to make ease testing Apache Spark programs.

```
class SimpleExampleSpec extends SparkSpecUtils with ShouldMatchers {
  sparkTest("spark parallelize") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 2 == 0}.count
    println("Result:", result)
    result should be (5e5.toInt)
  }
}
```

The Spark cluster was launched in local mode behind the scenes, kept around for the duration of the specs in the 
class, and shutdown afterwards.

I've divided it into two directories:

* core - The library which a developer includes into their project.
* examples - A standalone sbt project meant to be fully transferable.

I welcome feedback, issue reports and pull requests.

Leo
