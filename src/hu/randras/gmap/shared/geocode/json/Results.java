
package hu.randras.gmap.shared.geocode.json;

import java.util.List;

public class Results{
   	private List<AddressComponents> address_components;
   	private String formatted_address;
   	private Geometry geometry;
   	private List<String> types;

 	public List<AddressComponents> getAddress_components(){
		return this.address_components;
	}
	public void setAddress_components(List<AddressComponents> address_components){
		this.address_components = address_components;
	}
 	public String getFormatted_address(){
		return this.formatted_address;
	}
	public void setFormatted_address(String formatted_address){
		this.formatted_address = formatted_address;
	}
 	public Geometry getGeometry(){
		return this.geometry;
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
 	public List<String> getTypes(){
		return this.types;
	}
	public void setTypes(List<String> types){
		this.types = types;
	}
}
