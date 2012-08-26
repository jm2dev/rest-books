package com.jm2dev.books.model

import org.specs2.mutable._

class BookSpec extends Specification {
  private val title = "Title"
  private val book = Book(title, new Authors(List()))

  "A book" should {
    "have a title" in {
      book.title must not be empty
    }
    
    "have one or more authors" in {
      book.authors must not be empty
    }
  }
}
