package com.harsha.Projectsuccess;
public class SearchingEntity {
	public String searchedField="id";
	public String searchedTerm;

	public String getSearchedTerm() {
		return searchedTerm;
	}

	public void setSearchedTerm(String searchedTerm) {
		this.searchedTerm = searchedTerm;
	}

	public String getSearchedField() {
		return searchedField;
	}

	public void setSearchedField(String searchedField) {
		this.searchedField = searchedField;
	}

	@Override
	public String toString() {
		return "SearchingEntity [searchedField=" + searchedField + ", searchedTerm=" + searchedTerm + "]";
	}
}
