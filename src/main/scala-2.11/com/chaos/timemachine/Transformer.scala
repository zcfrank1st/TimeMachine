package com.chaos.timemachine

import org.joda.time.format.DateTimeFormat

/**git
  * Created by zcfrank1st on 6/22/16.
  */
class Transformer {
  def transform(content: String)(date: String): String = {

    val pattern = """(.*)(\#\{)(yyyy-MM-dd) (\W) (\d)(\w+)(})(.*)""".r
    val pattern(prefix, _, timePattern, action, offset, unit, _, postfix) = content

    val dtf = DateTimeFormat.forPattern("yyyy-MM-dd")
    val now = dtf.parseDateTime(date)

    val realTime =
      action match {
        case "-" =>
          unit match {
            case "day" => now.minusDays(offset.toInt).toString(timePattern)
            case "week" => now.minusWeeks(offset.toInt).toString(timePattern)
            case "month" => now.minusMonths(offset.toInt).toString(timePattern)
            case "year" => now.minusYears(offset.toInt).toString(timePattern)
            case _ => throw new RuntimeException("no such unit")
          }
        case "+" =>
          unit match {
            case "day" => now.plusDays(offset.toInt).toString(timePattern)
            case "week" => now.plusWeeks(offset.toInt).toString(timePattern)
            case "month" => now.plusMonths(offset.toInt).toString(timePattern)
            case "year" => now.plusYears(offset.toInt).toString(timePattern)
            case _ => throw new RuntimeException("no such unit")
          }
        case _ => throw new RuntimeException("no such action")
      }

    prefix + realTime + postfix
  }
}
