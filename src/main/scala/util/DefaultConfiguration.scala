package util

import com.typesafe.config.ConfigFactory

object DefaultConfiguration {
  private val config = ConfigFactory.load()

  lazy val HTTP_HOST: String = config.getString("http.host")
  lazy val HTTP_PORT: Int = config.getInt("http.port")

}