package ru.primetalk.scalajs101

import org.scalajs.dom
import org.scalajs.dom.ext.Color
import org.scalajs.dom.html
import TurtleView._
import org.scalajs.dom.html.Canvas

object InfSquarePict {

  type Path = Seq[Line]

  type TurtleCommands = Seq[TurtleCommand]




  def step4(l: Double): TurtleCommands = Seq(
    Move(l),
    RotateClockwise(90),
    Move(l),
    RotateClockwise(90),
    Move(l),
    RotateClockwise(90),
    Move(l),
    RotateClockwise(90)
  )
  def step44(l: Double): TurtleCommands =
    Seq(SetColor(Color(l.toInt, 256 - l.toInt, 128 + l.toInt/2))) ++
      step4(l) ++
      Seq(
        Move(l/96),
        RotateClockwise(1)
      )

  def step444(l: Double, n: Int, commands: TurtleCommands): TurtleCommands =
    if(n == 0) commands
    else step444(l * 0.98, n - 1, commands ++ step44(l))
//def turn(angle: Double),
//  def deepen(commands: TurtleCommands): TurtleCommands = {
//    commands.flatMap{
//      case Move(length) => step4(length)
//      case r => Seq(r)
//    }
//  }
//
//  def pictiD(d: Int, pict: TurtleCommands): TurtleCommands = {
//    if (d == 0) pict
//    else pictiD(d - 1, deepen(pict))
//  }

  var x: Double = 0.0
  var y: Double = 0.0
  var l: Double = 256.0

  def draw(c: Canvas, angle: Double): Unit = {
    val ctx = c.getContext("2d")
      .asInstanceOf[Ctx2D]
//    val w = 300
    val width = c.width
    val height = c.height

    val pict0: TurtleCommands = Seq(Move(width))
    val turtle0 = Turtle(Point(x, y), 0)
//    val pict1 = deepen(pict0)
//    val pict2 = deepen(pict1)
    val pict = step444(l, 256, Seq())
    val turtles: Seq[Turtle] = Turtle.trace(pict)(turtle0)
    ctx.strokeStyle = "lime"
    drawTurtlesOnCtx2D(ctx, turtles)

    x = x + 0.5
    y = y + 0.5
    l = l - 1.0
  }
}
