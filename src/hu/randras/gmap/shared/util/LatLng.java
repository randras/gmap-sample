package hu.randras.gmap.shared.util;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Egy koordináta párt reprezentáló osztály.
 * @author Romhanyi
 *
 */
public class LatLng implements IsSerializable, Serializable {

	private static final long serialVersionUID = 1L;

	private double lat;
	private double lng;
	
	public LatLng() {}
	
	public LatLng(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng the lan to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return lat+" ; "+lng;
	}
	
}
