package com.harsha.newspaper;
import java.util.List;

public class NewsApiResponse {
    private int totalResults;
    private List<Article> articles;

    public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public static class Article {
        private String title;
        private String description;
        private String urlToImage;
        private String url;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUrlToImage() {
			return urlToImage;
		}
		public void setUrlToImage(String urlToImage) {
			this.urlToImage = urlToImage;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
    }
}
