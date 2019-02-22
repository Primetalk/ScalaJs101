package ru.primetalk.scalajs101

import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom.ext.Color

case class Point(x: Double, y: Double)
case class Line(p0: Point, p1: Point)

sealed trait TurtleCommand
case class Move(length: Double) extends TurtleCommand
case class RotateClockwise(angleDegree: Double) extends TurtleCommand
case class SetColor(color: Color) extends TurtleCommand

case class Turtle(point: Point, angleDegree: Double, color: Color = Color(200,200,0))

object Turtle {

  type Path = Seq[Line]

  type TurtleCommands = Seq[TurtleCommand]


  def execute(cmd: TurtleCommand)(state: Turtle): Turtle = state match {
    case Turtle(p@Point(x, y), currentAngle, color) =>
      cmd match {
        case Move(length) =>
          Turtle(
            Point(
              x + length * math.cos(math.toRadians(currentAngle)),
              y + length * math.sin(math.toRadians(currentAngle))
            ),
            currentAngle,
            color
          )
        case RotateClockwise(angleDegree) =>
          Turtle(p, currentAngle + angleDegree,
            color)
        case SetColor(color2) =>
          Turtle(p, currentAngle,
            color2)
      }
  }

  def executeMany(commands: TurtleCommands)(state: Turtle): Turtle =
    commands.reverse.foldRight(state)(execute(_)(_))

  def trace(commands: TurtleCommands)(state: Turtle): Seq[Turtle] =
    commands.reverse.scanRight(state)(execute(_)(_))


}