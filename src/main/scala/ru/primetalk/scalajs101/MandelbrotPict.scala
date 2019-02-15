package ru.primetalk.scalajs101

import org.scalajs.dom
import org.scalajs.dom.ext.Color
import org.scalajs.dom.html

case class Complex(x: Double, y: Double) {
  def `^2`: Complex =
    Complex(x * x - y * y, 2 * x * y)

  def +(other: Complex) : Complex =
    Complex(x + other.x, y + other.y)

  def r2: Double = x * x + y * y
}

object MandelbrotPict {

//  val c = Complex(-1.25, 0)
//  val c = Complex(-1.25, 0)
  val c = Complex(-0.48, -0.53)

  def f(x: Complex): Complex = x.`^2` + c

  val p0 = Point(0, 0)
  val p1 = Point(400, 300)

  val c0 = Complex(-2.4, 1.2)
  val c1 = Complex(1.5, -1.2)

  def scale(p: Point): Complex =
    Complex(
      x = (p.x - p0.x) / (p1.x - p0.x) * (c1.x - c0.x) + c0.x,
      y = (p.y - p0.y) / (p1.y - p0.y) * (c1.y - c0.y) + c0.y
    )

  val maxCount = 200

  def mandelbrot(p: Point): Int = {
    val xn = scale(p)
    def loop(n: Int, x: Complex): Int =
      if (n == 0 || x.r2 > 6.0) n
      else loop(n - 1, f(x))

    loop(maxCount, xn)
  }
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
//    ctx.beginPath()
    for{
      j <- 0 until p1.y.toInt
      i <- 0 until p1.x.toInt
    } {
      val p = Point(i, j)
      val col = mandelbrot(p)

        val tt = math.min(maxCount, (maxCount - col) * 10)
      ctx.strokeStyle = Color(tt + 50, maxCount - tt, maxCount-tt).toHex// RGBColor//() "rgb"
      ctx.strokeRect(i, j, 1, 1)
    }
  }
}
