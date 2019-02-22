package ru.primetalk.scalajs101

import org.scalajs.dom.CanvasRenderingContext2D

object TurtleView {
  type Ctx2D =
    CanvasRenderingContext2D

  def drawTurtlesOnCtx2D(ctx: Ctx2D, turtles: Seq[Turtle]): Unit = {
    ctx.moveTo(turtles.head.point.x, turtles.head.point.y)
//    ctx.strokeStyle = turtles.head.color.toHex
//    ctx.beginPath()
//    var i = 0
    turtles.sliding(2).foreach { case Seq(t0, t) =>
      ctx.strokeStyle = t.color.toHex
      ctx.moveTo(t0.point.x, t0.point.y)
      ctx.lineTo(t.point.x, t.point.y)
//      ctx.beginPath()
//      if (i % 2 == 0) {
        ctx.stroke()
        ctx.beginPath()
//      }
//      i += 1
    }


  }
}
