package org.bblaine

import java.net.{ URL, URLConnection }
import scala.util.parsing.json.JSON
import scala.xml._

sealed abstract class API
case object City extends API {
  override def toString = "ip-city"
}
case object Country extends API {
  override def toString = "ip-country"
}

class GeoIP(key: String) {

  //Defaults.
  var ipInfoDBURL = new URL("http://api.ipinfodb.com/v3/" + Country.toString + "/?key=" + key + "&ip=" + "" + "&format=" + "raw")

  def getGeoXML(ipAddress: String, api: API): Elem = {
    ipInfoDBURL = new URL("http://api.ipinfodb.com/v3/" + api.toString + "/?key=" + key + "&ip=" + ipAddress + "&format=" + "xml")
    val conn = ipInfoDBURL.openConnection

    XML.load(conn.getInputStream)
  }

  def getGeoRaw(ipAddress: String, api: API): String = {
    ipInfoDBURL = new URL("http://api.ipinfodb.com/v3/" + api.toString + "/?key=" + key + "&ip=" + ipAddress + "&format=" + "raw")
    val conn = ipInfoDBURL.openConnection

    convertStreamToString(conn.getInputStream)
  }

  def getGeoJSON(ipAddress: String, api: API): Option[Any] = {
    ipInfoDBURL = new URL("http://api.ipinfodb.com/v3/" + api.toString + "/?key=" + key + "&ip=" + "" + "&format=" + "json")
    val conn = ipInfoDBURL.openConnection

    JSON.parseFull(convertStreamToString(conn.getInputStream))
  }

  //Credit and upvote to http://stackoverflow.com/questions/309424/in-java-how-do-i-read-convert-an-inputstream-to-a-string
  private def convertStreamToString(is: java.io.InputStream): String = {
    try {
      new java.util.Scanner(is).useDelimiter("\\A").next()
    } catch {
      case (e: java.util.NoSuchElementException) => return ""
    }
  }

}
