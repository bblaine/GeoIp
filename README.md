# GeoIP

Retreive the geolocation of an IP address based on the ipinfodb.com IP location API. 

## Build
Compile using 'sbt compile' and create jar with 'sbt package'.

## Usage

API keys can be obtained from ipinfodb.com

### Set API Key
    val geoip = new GeoIP("API_KEY")
    
### Choose API Precision
Each method takes an IP address and Precision case class.

Choose one of:

    org.bblaine.City
    org.bblaine.Country

    
### Get geo IP info in raw format
    g.getGeoRaw("64.233.160.0", org.bblaine.City)
    
returns a String
    
    res0: String = OK;;64.233.160.0;US;UNITED STATES;DISTRICT OF COLUMBIA;WASHINGTON;20001;38.9048;-77.0354;-05:00

### Get geo IP info in XML format
        
    g.getGeoXML("64.233.160.0", org.bblaine.City)

returns a scala.xml.Elem

    res1: scala.xml.Elem = 
    <Response>
      <statusCode>OK</statusCode>
      <statusMessage></statusMessage>
	    <ipAddress>64.233.160.0</ipAddress>
	    <countryCode>US</countryCode>
	    <countryName>UNITED STATES</countryName>
	    <regionName>DISTRICT OF COLUMBIA</regionName>
	    <cityName>WASHINGTON</cityName>
	    <zipCode>20001</zipCode>
	    <latitude>38.9048</latitude>
	    <longitude>-77.0354</longitude>
	    <timeZone>-05:00</timeZone>
    </Response>

### Get geo IP info in JSON format    
    g.getGeoJSON("64.233.160.0", org.bblaine.City)
    
Return an Option[Any]
    
    res2: Option[Any] = Some(Map(statusCode -> OK, cityName -> , latitude -> , countryName -> , zipCode -> , longitude -> , countryCode -> , ipAddress -> , statusMessage -> , regionName -> , timeZone -> ))
    
### Usage from Java
The library can bee easily used from Java by referencing the built jar or classes and the scala-library.jar on the classpath.

For example, here is a simple program where the library is used:
import org.bblaine.*;

    class TestFromJava{
      public static void main(String args[]){
        GeoIP geoip = new GeoIP("<API Key>");
        //Note the $.MODULE$ syntax to refer to the case class in Scala.
        System.out.println(geoip.getGeoRaw("<IP Address>", City$.MODULE$));
     }
    }

To build `javac -cp ipgeomap_2.9.1-0.1-SNAPSHOT.jar:scala-library.jar TestFromJava.java`

To run `java -cp ipgeomap_2.9.1-0.1-SNAPSHOT.jar:scala-library.jar:.`
OK;;0.0.0.0;US;UNITED STATES;DISTRICT OF COLUMBIA;WASHINGTON;20001;38.9048;-77.0354;-05:00


