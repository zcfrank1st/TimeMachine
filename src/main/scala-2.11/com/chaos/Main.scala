package com.chaos

import com.chaos.timemachine.{TimeMachine, TimeMachineModule}
import com.google.inject.Guice
import org.joda.time.DateTime
/**
  * Created by zcfrank1st on 6/22/16.
  */
case class Config (file: String = "", content: String = "", date: String = "")

object Main extends App {
  implicit val defaultTime: String  = new DateTime().toString("yyyy-MM-dd")

  val injector = Guice.createInjector(new TimeMachineModule)
  val machine = injector.getInstance(classOf[TimeMachine])

  val parser = new scopt.OptionParser[Config]("timemachine") {
    head("<time machine>", "1.0")

    opt[String]('f', "file").action( (x, c) =>
      c.copy(file = x) ).text("the path of the file")

    opt[String]('c', "content").action( (x, c) =>
      c.copy(content = x) ).text("pure string content")

    opt[String]('d', "date").optional().action( (x, c) =>
      c.copy(date = x)).text("init datetime yyyy-MM-dd")

    help("help").text("prints the usage text")

    note("\nAuthor: zcfrank1st")
  }

  val datePattern = """([0-9]{4}-[0-9]{2}-[0-9]{2})""".r

  parser.parse(args, Config()) match {
    case Some(config) =>
      val filePath = config.file
      val content = config.content
      val date = config.date

      date match {
        case "" =>
          if (filePath != "") {
            machine.file(filePath)
          }
          if (content != "") {
            println(machine.text(content))
          }
        case datePattern(value) =>
          if (filePath != "") {
            machine.file(filePath)(value)
          }
          if (content != "") {
            println(machine.text(content)(value))
          }
        case _ =>
          throw new RuntimeException("date value format error")
      }
    case None =>
      throw new RuntimeException("arguments wrong, please check")
  }

}
