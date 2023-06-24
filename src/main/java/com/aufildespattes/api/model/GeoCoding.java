package com.aufildespattes.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoCoding {
	@JsonProperty(value="results")
	GeoCodingResult[] geoCodingResults;
}