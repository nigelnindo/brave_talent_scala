package utils

import com.typesafe.config.ConfigFactory

/**
  * Created by nigelnindo on 8/17/17.
  */
object Config {
  val factory = ConfigFactory.load()
  val server = factory.getConfig("server")
}
