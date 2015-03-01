package com.leoromanovsky.sparkspec.examples

import com.leoromanovsky.sparkspec.core.SparkSpecUtils
import org.scalatest.ShouldMatchers

class SimpleDemoTest extends SparkSpecUtils with ShouldMatchers {
  sparkTest("spark filter mod 2") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 2 == 0}.count
    println("Result:", result)
    result should be (5e5.toInt)
  }

  sparkTest("spark filter mod 5") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 5 == 0}.count
    println("Result:", result)
    result should be (200000)
  }

  sparkTest("spark filter mod 9") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 9 == 0}.count
    println("Result:", result)
    result should be (111111)
  }

  sparkTest("spark filter mod 13") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 13 == 0}.count
    println("Result:", result)
    result should be (76923)
  }

  sparkTest("spark filter mod 21") {
    val data = sc.parallelize(1 to 1e6.toInt)
    val result = data.filter{_ % 21 == 0}.count
    println("Result:", result)
    result should be (47619)
  }
}
