package com.velvet.vo;

import java.util.List;



public class PartsSearchBean{
	
private String fromDate;
private String toDate;

private Parts parts;

private List<PartsSearchResultsBean> partsSearchResults;


public List<PartsSearchResultsBean> getPartsSearchResults() {
	return partsSearchResults;
}

public void setPartsSearchResults(
		List<PartsSearchResultsBean> partsSearchResults) {
	this.partsSearchResults = partsSearchResults;
}

public String getFromDate() {
	return fromDate;
}

public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}

public String getToDate() {
	return toDate;
}

public void setToDate(String toDate) {
	this.toDate = toDate;
}

public Parts getParts() {
	return parts;
}

public void setParts(Parts parts) {
	this.parts = parts;
}
	



}
