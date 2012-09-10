package com.jm2dev.books

import org.scalatra._
import scalate.ScalateSupport
import com.jm2dev.books.service.BookService
import com.codahale.jerkson.Json._

class BooksServlet extends ScalatraServlet with ScalateSupport {

  private val bookService = new BookService()

  get("/") {
    contentType="text/html"

    jade("/index")
  }

  get("/books") {
    contentType = "application/json"

    val books = bookService.findAll()
    generate(books)
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null 
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }
}
