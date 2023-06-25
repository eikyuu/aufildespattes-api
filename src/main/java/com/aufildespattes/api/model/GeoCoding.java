package com.aufildespattes.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoCoding {
	@JsonProperty(value="results")
	GeoCodingResult[] geoCodingResults;

	public GeoCodingResult[] getGeoCodingResults() {
		return geoCodingResults;
	}

	public void setGeoCodingResults(GeoCodingResult[] geoCodingResults) {
		this.geoCodingResults = geoCodingResults;
	}
}