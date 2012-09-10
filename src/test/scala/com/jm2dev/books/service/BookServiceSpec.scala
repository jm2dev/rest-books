package com.jm2dev.books.service

import org.specs2.mutable._
import com.mongodb.casbah.Imports._
import com.jm2dev.books.model.{Book, Authors, Author}

class BookServiceSpec extends Specification {
  private val bookService = new BookService
  private val title = "Title"
  private val book = new Book(title, new Authors(List()))
  private val authoredBook = new Book(title, new Authors(List(new Author("author name", "author surname"))))

  "Book service" should {
    "persists a book to database" in {
      bookService.persist(authoredBook) must beAnInstanceOf[MongoDBObject]
    }

    "finds a book by title" in {
      val book = bookService.findByTitle(title)
      book must beSome
    }

    "finds all books" in {
      val books = bookService.findAll()
      books.size must beGreaterThanOrEqualTo(0)
    }
  }
}

trait BookCollection extends Before {
  def before = MongoConnection()("books-db")("books_data").dropCollection()
}


