package hu.randras.gmap.server;

import flexjson.JSONDeserializer;
import hu.randras.gmap.client.services.GeocodingService;
import hu.randras.gmap.shared.model.Shop;
import hu.randras.gmap.shared.util.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * A geocodolással kapcsolatos szolgáltatások gyüjteményének implementációja.
 * @author Romhanyi
 *
 */
public class GeocodingServiceImpl extends RemoteServiceServlet implements GeocodingService {

	private static final long serialVersionUID = 1L;

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	/**
	 * A teljes cím alapján meghívja a geocode api-t.
	 * @param fullAddress
	 * @return geocode api válasza JSON formátumban.
	 * @throws IOException
	 */
	private String getJSONByGoogle(String fullAddress) throws IOException {
		URL url = new URL(URL + "?address="+ URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=true");
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		String result = "";
		while((line = br.readLine()) != null) {
			result += line;
		}
		br.close();
		return result;

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public LatLng getGeocodeByShop(Shop shop) throws Exception {
		LatLng latlng = null;
		String address = shop.getZipCode()+" "+shop.getLocation()+" "+shop.getAddress()+" "+shop.getState();
	
		Map result = new JSONDeserializer<Map>().deserialize(getJSONByGoogle(address));
		
		if ("OK".equals(result.get("status").toString())) {	
			// FIXME
			// ez igy nem szép, csináltam generáltam a json object-nek egy megfelelõ
			// POJO-t de valamiért a flexJson nem tudta bele parsolni (némi utána járást igényel)
			double lat = Double.parseDouble( ((Map) ((Map) ((Map)((List) result.get("results") ).get(0) ) .get("geometry")).get("location")).get("lat").toString() );
			double lng = Double.parseDouble( ((Map) ((Map) ((Map)((List) result.get("results") ).get(0) ) .get("geometry")).get("location")).get("lng").toString() );
		
			latlng = new LatLng(lat, lng);
		}
	
		return latlng;
	}

}
