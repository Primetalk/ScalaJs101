package ru.primetalk.scalajs101

import org.querki.jquery._

object Main {
  def main(args: Array[String]): Unit = {
    $("body").append("<p>Hello, World!</p>")
  }
}
