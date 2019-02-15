package ru.primetalk.scalajs101

import org.scalajs.dom
import org.scalajs.dom.html

case class Point(x: Double, y: Double)
case class Line(p0: Point, p1: Point)

sealed trait TurtleCommand
case class Move(length: Double) extends TurtleCommand
case class RotateClockwise(angleDegree: Double) extends TurtleCommand

case class Turtle(point: Point, angleDegree: Double)

object FractalPict {

  type Path = Seq[Line]

  type TurtleCommands = Seq[TurtleCommand]

  def step3(l: Double): TurtleCommands = Seq(
    Move(l/3),
    RotateClockwise(-60),
    Move(l/3),
    RotateClockwise(120),
    Move(l/3),
    RotateClockwise(-60),
    Move(l/3)
  )

  def step4(l: Double): TurtleCommands = Seq(
    Move(l/3),
    RotateClockwise(-90),
    Move(l/3),
    RotateClockwise(90),
    Move(l/3),
    RotateClockwise(90),
    Move(l/3),
    RotateClockwise(-90),
    Move(l/3)
  )

  def deepen(commands: TurtleCommands): TurtleCommands = {
    commands.flatMap{
      case Move(length) => step4(length)
      case r => Seq(r)
    }
  }

  def pictiD(d: Int, pict: TurtleCommands): TurtleCommands = {
    if (d == 0) pict
    else pictiD(d - 1, deepen(pict))
  }

  def execute(cmd: TurtleCommand)(state: Turtle): Turtle = state match {
    case Turtle(p@Point(x, y), currentAngle) =>
      cmd match {
        case Move(length) =>
          Turtle(
            Point(
              x + length * math.cos(math.toRadians(currentAngle)),
              y + length * math.sin(math.toRadians(currentAngle))
            ),
            currentAngle
          )
        case RotateClockwise(angleDegree) =>
          Turtle(p, currentAngle + angleDegree)
      }
  }

  def executeMany(commands: TurtleCommands)(state: Turtle): Turtle =
    commands.reverse.foldRight(state)(execute(_)(_))

  def trace(commands: TurtleCommands)(state: Turtle): Seq[Turtle] =
    commands.reverse.scanRight(state)(execute(_)(_))

  def draw(c: html.Canvas): Unit = {
    type Ctx2D =
      dom.CanvasRenderingContext2D
    val ctx = c.getContext("2d")
      .asInstanceOf[Ctx2D]
//    val w = 300
    val width = c.width
    val height = c.height

    val pict0: TurtleCommands = Seq(Move(width))
    val turtle0 = Turtle(Point(0, height), 0)
//    val pict1 = deepen(pict0)
//    val pict2 = deepen(pict1)
    val pict = pictiD(6, pict0)
    val turtles: Seq[Turtle] = trace(pict)(turtle0)
    val points: Seq[Point] = turtles.map(_.point)

    ctx.strokeStyle = "green"
    ctx.lineWidth = 1
    ctx.beginPath()
    ctx.moveTo(turtle0.point.x, turtle0.point.y)
    points.foreach(p => ctx.lineTo(p.x, p.y))

    ctx.stroke()
  }
}
