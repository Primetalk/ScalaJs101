package ru.primetalk.scalajs101

import org.querki.jquery._
import org.scalajs.dom
import org.scalajs.dom.html.Canvas

object Main {
  def addClickedMessage(): Unit = {
    $("body").append("<p>You clicked the button!</p>")
  }

  def setupUI(): Unit = {
    $("body").append("<p>Hello, World!</p>")
    $("#click-me-button").click(() => addClickedMessage())
    val canvas = dom.document.createElement("canvas").asInstanceOf[Canvas]
    canvas.width = (0.95 * dom.window.innerWidth).toInt
    canvas.height = (0.95 * dom.window.innerHeight).toInt
    dom.document.body.appendChild(canvas)

    SimplePict.draw(canvas)
  }

  def main(args: Array[String]): Unit = {
    $("body").append("<p>Hello, World1!</p>")
    $(() => setupUI())
  }

}
