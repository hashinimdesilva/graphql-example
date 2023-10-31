package util

import java.sql.Timestamp
import java.text.SimpleDateFormat

object TimestampConverter {

  def convertToString(timestamp: Timestamp): String = {
    val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    df.format(timestamp)
  }
}