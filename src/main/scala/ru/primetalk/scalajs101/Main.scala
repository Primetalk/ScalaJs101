package ru.primetalk.scalajs101

import org.querki.jquery._
import org.scalajs.dom
import org.scalajs.dom.html.{Canvas, Input}

object Main {
  val mainCanvasId = "mainCanvas"
  val angleInputId = "angle"
  def addClickedMessage(): Unit = {
    val canvas = dom.document.getElementById(mainCanvasId).asInstanceOf[Canvas]
    val angleInput = dom.document.getElementById(angleInputId).asInstanceOf[Input]
    val angle = angleInput.value.toDouble
    InfSquarePict.draw(canvas, angle)
    $("body").append("<p>You clicked the button!</p>")
  }

  def setupUI(): Unit = {
//    $("body").append("<p>Hello, World!</p>")
    $("#click-me-button").click(() => addClickedMessage())
//    $("body").append("<canvas width='300' height='300'>Hello, World!</canvas>")
    val canvas = dom.document.createElement("canvas").asInstanceOf[Canvas]
    canvas.id = mainCanvasId
    canvas.width = (0.95 * dom.window.innerWidth).toInt
    canvas.height = (0.95 * dom.window.innerHeight).toInt
    dom.document.body.appendChild(canvas)

//    MandelbrotPict.draw(canvas)

  }

  def main(args: Array[String]): Unit = {
//    $("body").append("<p>Hello, World1!</p>")
    $(() => setupUI())
  }

}
