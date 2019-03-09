package com.scala.demo

object ImplicitTest {

  implicit  def doubleToInt(d:Double)={d toInt}

  def sum(a:Int)(implicit b:Int)={a+b}

  def main(args: Array[String]): Unit = {

    implicit val a =3
    implicit val b=4
    println(sum(a)(b))

    implicit val c=1.8
  }
}
