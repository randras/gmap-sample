package hu.randras.gmap.client.services;

import hu.randras.gmap.shared.model.Shop;
import hu.randras.gmap.shared.util.LatLng;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * 
 * @author Romhanyi
 *
 */
@RemoteServiceRelativePath("geocoding")
public interface GeocodingService extends RemoteService  {
	
	LatLng getGeocodeByShop(Shop shop) throws Exception ;
	
}
