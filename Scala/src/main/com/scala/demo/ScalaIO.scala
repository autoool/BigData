package com.scala.demo

import scala.io.Source

object ScalaIO {

  //  def main(args: Array[String]): Unit = {
  //    val writer = new PrintWriter(new File("test.txt"))
  //    writer.write("test")
  //    writer.close()
  //  }

//  def main(args: Array[String]): Unit = {
//    print("please input:")
//    val line = StdIn.readLine()
//    println("您输入的内容是：" + line)
//  }

  def main(args: Array[String]): Unit = {
    println("文件内容为：")
    Source.fromFile("test.txt").foreach{
      print
    }
  }
}
