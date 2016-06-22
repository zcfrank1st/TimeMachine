package com.chaos.timemachine

import java.io.PrintWriter

import com.google.inject.Inject

import scala.io.Source
/**
  * Created by zcfrank1st on 6/22/16.
  */
class TimeMachine @Inject()(transformer: Transformer){
  def file (path: String)(implicit date: String): Unit = {
    val out = Source.fromFile(path)
    val newContent = transformer.transform(out.mkString)(date)

    new PrintWriter(path) {
      write(newContent)
      close()
    }
  }

  def text (content: String)(implicit date: String): String = {
    transformer.transform(content)(date)
  }
}
