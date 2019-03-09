package com.scala.demo

object SclalaDemo {

  /**
    * Seq 序列
    * head 取头元素
    * tail 取尾元素 除了头部元素
    *
    * 5::Nil 空列表增加一个元素
    * +： 字符串拼接
    * ++ 两个集合相加
    * ++： 合并集合
    * .+：  头部追加元素
    * :+    尾部追加元素
    * :::  两个集合相加
    * count() 统计个数
    * filter 过滤
    * sortBy 排序 sortBy(x -> - x) 负号降序排序
    * sortWith 排序
    * grouped 分组
    * fold 折叠   fold(初始值)（(x,y)->x+y）
    * foldRight 右折叠
    * reduce 聚合
    * aggregate 先局部聚合，再全局聚合  底层调用 foldLeft
    * zip 拉链
    * sum 求和
    *
    *
    *
    * Set  Map集合
    * Set 不可变集合
    * HashSet 可变集合
    *
    * Map 不可变集合
    * HashMap 可变
    *
    * 元组 tuple
    * 元组中可以是任意元素
    * 取元素：t._1
    * 对偶元组： 有两个元素的元组
    *
    *
    */

  /**
    * Trait(特征）
    * 相同于Java 接口，还可定义属性和方法实现
    *
    * 模式匹配  case  使用case 是样例类
    *
    */

  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }

  //样例类
  case class Person(name: String, age: Int)

  /**
    * 异常处理
    * throw
    *
    *
    */

  /**
    * 提取器 Extractor
    * 提取器从传递给它的对象中提取出构造函数对象的参数
    *
    *
    */

  //注入方法 必填
  def apply(user: String, domain: String) = {
    user + "@" + domain
  }

  //提取方法 必填
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) {
      Some(parts(0), parts(1))
    } else {
      None
    }
  }

  def main(args: Array[String]): Unit = {
    //    println(matchTest(1))
//    println("Apply 方法"+apply("Zara","gmail.com"))
//    println("UnApply 方法"+unapply("Zara@gmail.com"))
//    println("UnApply 方法"+unapply("Zara Ali"))


  }


}
