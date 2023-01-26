package com.afrah;

public class Results {
	    private SubMain subMain;
	    private String listName;
	    private String listNameEncoded;
	    private String publishedDateDescription;
	    private String nextPublishedDate;
	    private String displayName;
	    private long normalListEndsAt;
	    private String updated;
	    private String copyright;

	    private Book[] books;
	    
	    public String getListName() {
			return listName;
		}
		public void setListName(String listName) {
			this.listName = listName;
		}
		public String getListNameEncoded() {
			return listNameEncoded;
		}
		public void setListNameEncoded(String listNameEncoded) {
			this.listNameEncoded = listNameEncoded;
		}
		public String getPublishedDateDescription() {
			return publishedDateDescription;
		}
		public void setPublishedDateDescription(String publishedDateDescription) {
			this.publishedDateDescription = publishedDateDescription;
		}
		public String getNextPublishedDate() {
			return nextPublishedDate;
		}
		public void setNextPublishedDate(String nextPublishedDate) {
			this.nextPublishedDate = nextPublishedDate;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		public long getNormalListEndsAt() {
			return normalListEndsAt;
		}
		public void setNormalListEndsAt(long normalListEndsAt) {
			this.normalListEndsAt = normalListEndsAt;
		}
		public String getUpdated() {
			return updated;
		}
		public void setUpdated(String updated) {
			this.updated = updated;
		}
		public Book[] getBooks() {
			return books;
		}
		public void setBooks(Book[] books) {
			this.books = books;
		}
		public Object[] getCorrections() {
			return corrections;
		}
		public void setCorrections(Object[] corrections) {
			this.corrections = corrections;
		}
		public String getCopyright() {
			return copyright;
		}
		public void setCopyright(String copyright) {
			this.copyright = copyright;
		}
		public SubMain getSubMain() {
			return subMain;
		}
		public void setSubMain(SubMain subMain) {
			this.subMain = subMain;
		}
		private Object[] corrections;

}
