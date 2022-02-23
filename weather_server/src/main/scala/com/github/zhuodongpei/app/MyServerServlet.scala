package com.github.zhuodongpei.app

import org.scalatra._
import play.api.libs.json._

class MyServerServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }
  get("/getweather/lat/:lat/lon/:lon") {
    getWeather(params("lat"), params("lon"))
  }
  def getWeather(lat: String, lon: String): String = {
    val APIkey = "ea0b6ce40771c3639a7247f99287d8fb"
    val url = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&exclude=hourly,daily&appid="+ APIkey
    val content = scala.io.Source.fromURL(url).mkString
    val json: JsValue = Json.parse(content)
    composeHtml(lat, lon, content)
  }
  def composeHtml(lat: String, lon: String, content: String): String = {
    val json: JsValue = Json.parse(content)
    val title = "Weather for lat= " + lat + " lon=" + lon + " is:\n"
    val condition = json("current")("weather")(0)("main").as[String]
    val hot_limit = 303
    val cold_limit = 283
    val temperature = json("current")("temp").as[Float]
    val warmth = if (temperature > hot_limit) "hot" else if (temperature < cold_limit) "cold" else "moderate"
    val alerts = json("alerts").as[JsArray].value.map{ jv:JsValue => 
      s"Event: ${jv("event")}\nDescription: ${jv("description")}\n"
    }.mkString("\n")
    s"Weather for lat=$lat lon=$lon is:\n\n" +
    s"Condition: $condition\n\n" +
    s"Warm/cold: $warmth\n\n" +
    s"Alerts:\n$alerts"
  }
}
