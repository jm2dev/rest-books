package com.jm2dev.books.model

import com.codahale.jerkson.Json._

case class Author(firstName: String, familyName: String) {
  def toJSON = generate(this)
}

class Authors(authors: List[Author]) {
  val knownAuthors: List[Author] = authors

  def howMany = authors.size

  def add(author: Author) = {
    val authorsUpdated = author :: knownAuthors
    new Authors(authorsUpdated)
  }

  def toJSON = generate(this.knownAuthors)
}
