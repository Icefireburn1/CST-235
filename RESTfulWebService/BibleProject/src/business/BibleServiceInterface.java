package business;

import java.util.List;

import javax.ejb.Local;

import model.BibleVerse;

@Local
public interface BibleServiceInterface {
	public BibleVerse firstWordOccurence(String word);
	public int wordNumberOccurences(String word);
	public String getVerse(BibleVerse bv);
	public List<BibleVerse> getVerses();
}
