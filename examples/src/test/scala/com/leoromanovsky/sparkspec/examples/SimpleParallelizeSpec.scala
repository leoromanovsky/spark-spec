package com.leoromanovsky.sparkspec.examples

import com.leoromanovsky.sparkspec.core.SparkSpecUtils
import org.scalatest.ShouldMatchers

class SimpleExampleSpec extends SparkSpecUtils with ShouldMatchers {
  sparkTest("spark filter") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 2 == 0}.count
    println("Result:", result)
    result should be (5e5.toInt)
  }
}
