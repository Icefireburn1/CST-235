package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import business.BibleServiceInterface;
import model.BibleVerse;

@ManagedBean
@SessionScoped
public class Controller {
	
	@Inject
	BibleServiceInterface service;
	
	public void onSubmit(BibleVerse bv) {
		String text = service.getVerse(bv);
		
		displayMessage(text);
	}
	
	public void onWordSubmit(BibleVerse bv) {
		String word = bv.getWord();

		BibleVerse temp = service.firstWordOccurence(word);
		int matches = service.wordNumberOccurences(word);
		
		displayMessage("Found with " + matches + " matches with the first occurence at " + temp.getBook() + " " + temp.getChapter() + ":" + temp.getVerse() + " - " + temp.getText());
	}
	
	private void displayMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Please select a path first", msg));
	}
}
