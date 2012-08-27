package com.jm2dev.books.service

import org.specs2.mutable._

import com.mongodb.casbah.Imports._
import com.jm2dev.books.model.{Book, Authors}

class BookServiceSpec extends Specification {
  private val bookService = new BookService
  private val title = "Title"
  private val book = new Book(title, new Authors(List()))

  "Book service" should {
    "persists a book to database" in {
      bookService.persist(book) must beAnInstanceOf[MongoDBObject]
    }

    "finds a book by title" in {
      val book = bookService.findByTitle(title)
      book must beSome
    }
  }
}

trait BookCollection extends After {
  def after = MongoConnection()("books-db")("books_data").dropCollection()
}

class BookService {
  val mongoColl = MongoConnection()("books-db")("books_data")

  def persist(book: Book): MongoDBObject = {
    val bookObj = MongoDBObject("title" -> book.title)
    mongoColl += bookObj
    bookObj
  }

  def findByTitle(title: String) = {
    val book = mongoColl.findOne(MongoDBObject("title" -> title))
    book
  }
}

