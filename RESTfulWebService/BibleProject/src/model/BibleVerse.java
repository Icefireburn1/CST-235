package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean
@SessionScoped
@XmlRootElement
public class BibleVerse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	String book;
	int chapter;
	int verse;
	String word;
	
	public BibleVerse(String book, int chapter, int verse, String text) {
		this.book = book;
		this.chapter = chapter;
		this.verse = verse;
		this.text = text;
	}
	
	public BibleVerse(String book, int chapter, int verse) {
		this.book = book;
		this.chapter = chapter;
		this.verse = verse;
		this.text = "";
	}
	
	public BibleVerse() {
		this.book = "Genesis";
		this.chapter = 1;
		this.verse = 1;
		this.text = "";
		this.word = "";
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getVerse() {
		return verse;
	}
	public void setVerse(int verse) {
		this.verse = verse;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
}
