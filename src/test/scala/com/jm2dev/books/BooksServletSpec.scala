package com.jm2dev.books

import org.scalatra.test.specs2._

class BooksServletSpec extends ScalatraSpec { def is =
  "GET / on BooksServlet"                     ^
    "should return status 200"                  ! root200^
                                                end^
  "GET /hello-scalate on BooksServlet"        ^
    "should return status 200"                  ! hello_scalate200^
                                                end^
  "GET /books on BooksServlet"                ^
    "should return status 200"                  ! books200^
                                                end
                                             
  addServlet(classOf[BooksServlet], "/*")

  def root200 = get("/") { 
    status must_== 200
  }

  def hello_scalate200 = get("/hello-scalate") {
    status must_== 200
  }

  def books200 = get("/books") {
    status must_== 200
  }
}
