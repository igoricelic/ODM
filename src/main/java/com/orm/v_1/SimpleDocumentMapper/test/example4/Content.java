package com.orm.v_1.SimpleDocumentMapper.test.example4;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;

@Document(embedded = true)
public class Content {
	
	private String titile;
	
	private String originalTitle;
	
	private String intro;
	
	private String portraitImg;
	
	private String landscapeImg;
	
	private String runtime;
	
	private String year;
	
	private List<String> genres;

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPortraitImg() {
		return portraitImg;
	}

	public void setPortraitImg(String portraitImg) {
		this.portraitImg = portraitImg;
	}

	public String getLandscapeImg() {
		return landscapeImg;
	}

	public void setLandscapeImg(String landscapeImg) {
		this.landscapeImg = landscapeImg;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Content [titile=" + titile + ", originalTitle=" + originalTitle + ", intro=" + intro + ", portraitImg="
				+ portraitImg + ", landscapeImg=" + landscapeImg + ", runtime=" + runtime + ", year=" + year
				+ ", genres=" + genres + "]";
	}

}
