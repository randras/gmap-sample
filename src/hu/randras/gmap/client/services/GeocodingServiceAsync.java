package hu.randras.gmap.client.services;

import hu.randras.gmap.shared.model.Shop;
import hu.randras.gmap.shared.util.LatLng;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GeocodingServiceAsync {

	void getGeocodeByShop(Shop shop, AsyncCallback<LatLng> callback);

}
