package hu.randras.gmap.client;

import hu.randras.gmap.client.services.GeocodingService;
import hu.randras.gmap.client.services.GeocodingServiceAsync;
import hu.randras.gmap.shared.model.Shop;
import hu.randras.gmap.shared.util.LatLng;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.accessibility.internal.resources.accessibility;


public class Google_map_sample implements EntryPoint, ClickHandler {
	
	private final GeocodingServiceAsync geocodingService = GWT.create(GeocodingService.class);
	
	private final TextBox nameField = new TextBox();
	private final TextBox zipCodeField = new TextBox();
	private final TextBox locationField = new TextBox();
	private final TextBox addressField = new TextBox();
	private final TextBox stateField = new TextBox();
	
	private final Button geocodingServiceButton = new Button("Geocoding");
	private final Button showOnMapButton = new Button("Show on map");
	
	@Override
	public void onModuleLoad() {

		
		// betöltjük az api-t
		Maps.loadMapsApi("", "2", true, new Runnable() {
			public void run() {
				buildUI();
			}
		});
		
	}
	
	/**
	 * UI felépítéséért felel.
	 */
	private void buildUI() {
		
		RootPanel.get("name-field").add(nameField);
		RootPanel.get("zip-code-field").add(zipCodeField);
		RootPanel.get("location-field").add(locationField);
		RootPanel.get("address-field").add(addressField);
		RootPanel.get("state-field").add(stateField);
		
		//Tesztelés miatt kitöltjük alapértelmezett adatokkal.
		zipCodeField.setValue("1094");
		locationField.setValue("Budapest");
		addressField.setValue("Liliom utca 7-9.");
		
		RootPanel.get("services").add(geocodingServiceButton);
		RootPanel.get("services").add(showOnMapButton);
		showOnMapButton.addClickHandler(this);
		geocodingServiceButton.addClickHandler(this);
		
	}

	/**
	 * A felületen található két gomb esemény kezelõje.
	 */
	@Override
	public void onClick(final ClickEvent event) {
		final boolean showOnMap = showOnMapButton.equals(event.getSource());
		geocodingService.getGeocodeByShop(createShop(),new AsyncCallback<LatLng>() {
			@Override
			public void onFailure(Throwable caught) {	
				DialogBox errorDialogBox = new DialogBox(true);
				errorDialogBox.add(new Label(caught.getMessage()));
				errorDialogBox.show();
			}

			@Override
			public void onSuccess(LatLng result) {
				if (result == null) {
					RootPanel.get("result").clear();
					RootPanel.get("result").add(new Label("Not valid shop!"));
				} else {
					if (showOnMap) {
						showOnMap(result);
					} else {
						showResult(result);
					}
				}
			}
		});
	}
	
	/**
	 * Egy popup ablakban megjelníti a térképet a paraméterként kapott koordinátával.
	 * @param latlng
	 */
	private void showOnMap(LatLng latlng) {
		DialogBox mapDialogBox = new DialogBox(true);
		mapDialogBox.center();
		mapDialogBox.setSize("600px", "600px");
		com.google.gwt.maps.client.geom.LatLng center = com.google.gwt.maps.client.geom.LatLng.newInstance(latlng.getLat(), latlng.getLng());
		MapWidget map = new MapWidget(center, 13);
		map.addControl(new LargeMapControl());
		map.addOverlay(new Marker(center));
		map.setSize("600px", "600px");
		mapDialogBox.add(map);
		mapDialogBox.show();
	}
	
	/**
	 * Megjeleníti a paraméterként kapott koordinátát a felületen.
	 * @param result
	 */
	private void showResult(LatLng result) {
		RootPanel.get("result").add(new Label(result.toString()));
	}
	
	/**
	 * A felületi text mezõk alapján létre hozza {@link Shop} entitást.
	 * @return
	 */
	private Shop createShop() {
		Shop shop = new Shop();
		shop.setAddress(addressField.getValue());
		shop.setLocation(locationField.getValue());
		shop.setName(nameField.getValue());
		shop.setState(stateField.getValue());
		shop.setZipCode(zipCodeField.getValue());
		
		return shop;
	}
}
