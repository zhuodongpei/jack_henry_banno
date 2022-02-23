package com.github.zhuodongpei.app

import org.scalatra.test.scalatest._

class MyServerServletTests extends ScalatraFunSuite {

  addServlet(classOf[MyServerServlet], "/*")

  test("GET / on MyServerServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
