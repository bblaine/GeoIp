#GeoIP

Retreive the geolocation of an IP address based on the ipinfodb.com IP location API. 

##Usage

API keys can be obtained from ipinfodb.com

###Set API Key
    val geoip = new GeoIP("API_KEY")
    
###Choose API Precision
Each method takes an IP address and Precision case class.

Choose one of:

    org.bblaine.City
    org.bblaine.Country

    
###Get geo IP info in raw format
    g.getGeoRaw("64.233.160.0", org.bblaine.City)
    
returns a String
    
    res0: String = OK;;64.233.160.0;US;UNITED STATES;DISTRICT OF COLUMBIA;WASHINGTON;20001;38.9048;-77.0354;-05:00

###Get geo IP info in XML format
        
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

###Get geo IP info in JSON format    
    g.getGeoJSON("64.233.160.0", org.bblaine.City)
    
Return an Option[Any]
    
    res2: Option[Any] = Some(Map(statusCode -> OK, cityName -> , latitude -> , countryName -> , zipCode -> , longitude -> , countryCode -> , ipAddress -> , statusMessage -> , regionName -> , timeZone -> ))
