
package hu.randras.gmap.shared.geocode.json;

import java.util.List;

public class GeocodingResult{
   	private List<Results> results;
   	private String status;

 	public List<Results> getResults(){
		return this.results;
	}
	public void setResults(List<Results> results){
		this.results = results;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
