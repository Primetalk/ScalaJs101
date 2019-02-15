package ru.primetalk.scalajs101

import org.scalajs.dom
import org.scalajs.dom.html

object SimplePict {

  def draw(c: html.Canvas): Unit = {
    type Ctx2D =
      dom.CanvasRenderingContext2D
    val ctx = c.getContext("2d")
      .asInstanceOf[Ctx2D]
//    val w = 300
    val width = c.width
    val height = c.height

    ctx.strokeStyle = "red"
    ctx.lineWidth = 3
    ctx.beginPath()
    ctx.moveTo(width/3, 0)
    ctx.lineTo(width/3, height/3)
    ctx.moveTo(width*2/3, 0)
    ctx.lineTo(width*2/3, height/3)
    ctx.moveTo(width, height/2)
    ctx.arc(width/2, height/2, height/2, 0, 3.14)

    ctx.stroke()
  }
}
