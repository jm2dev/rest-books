package com.jm2dev.books.service

import com.mongodb.casbah.Imports._
import com.jm2dev.books.model.{Book, Authors}
import com.codahale.jerkson.Json._

class BookService {
  val mongoColl = MongoConnection()("books-db")("books_data")

  def persist(book: Book): MongoDBObject = {
    val bookObj = MongoDBObject("title" -> book.title, "authors" -> generate(book.authors))
    mongoColl += bookObj
    bookObj
  }

  def findByTitle(title: String) = {
    val book = mongoColl.findOne(MongoDBObject("title" -> title))
    book
  }

  def findAll() = {
    mongoColl.find()
    for { x <- mongoColl} yield x("title")
  }
}
