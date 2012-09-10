package com.jm2dev.books.model

import org.specs2.mutable._

class AuthorSpec extends Specification {
  private val firstName = "Name"
  private val familyName = "Family"
  private val author = Author(firstName, familyName)

  "An author" should {
    "have a first name" in {
      author.firstName must not be empty
    }

    "have a family name" in {
      author.familyName must not be empty
    }

    "have a JSON representation" in {
      val jsonAuthor = author.toJSON 
      jsonAuthor must */("firstName" -> firstName) 
      jsonAuthor must */("familyName" -> familyName)
    }
  }
}

class AuthorsSpec extends Specification {
  private val firstName = "Name"
  private val familyName = "Family"
  private val author = Author(firstName, familyName)
  private val authors = new Authors(List(author))

  "Authors" should {
    "have at least one member" in {
      authors.howMany must beGreaterThan(0)
    }

    "add new members" in {
      val initialNumberOfAuthors = authors.howMany
      val newAuthors = authors.add(author)
      newAuthors.howMany must beGreaterThan(initialNumberOfAuthors)
    }

    "have a JSON representation" in {
      val jsonAuthors = authors.toJSON
      jsonAuthors must */("firstName" -> firstName) 
      jsonAuthors must */("familyName" -> familyName)
    }
  }
}
