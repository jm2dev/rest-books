package com.jm2dev.books.model

case class Author(firstName: String, familyName: String) 

class Authors(authors: List[Author]) {
  val knownAuthors: List[Author] = authors

  def howMany = authors.size

  def add(author: Author) = {
    val authorsUpdated = author :: knownAuthors
    new Authors(authorsUpdated)
  }
}
