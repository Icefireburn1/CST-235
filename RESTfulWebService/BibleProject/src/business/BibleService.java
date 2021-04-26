package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.BibleVerse;

@Stateless
@Local(BibleServiceInterface.class)
@LocalBean
public class BibleService implements BibleServiceInterface {

	private enum Books {
		Genesis(1),
		Exodus(2),
		Leviticus(3),
		Numbers(4),
		Deuteronomy(5),
		Joshua(6),
		Judges(7),
		Ruth(8),
		Samuel1(9),
		Samuel2(10),
		Kings1(11),
		Kings2(12),
		Chronicles1(13),
		Chronicles2(14),
		Ezra(15),
		Nehemiah(16),
		Esther(17),
		Job(18),
		Psalm(19),
		Proverbs(20),
		Ecclesiastes(21),
		SongofSongs(22),
		Isaiah(23),
		Jeremiah(24),
		Lamentations(25),
		Ezekiel(26),
		Daniel(27),
		Hosea(28),
		Joel(29),
		Amos(30),
		Obadiah(31),
		Jonah(32),
		Micah(33),
		Nahum(34),
		Habakkuk(35),
		Zephaniah(36),
		Haggai(37),
		Zechaniah(38),
		Malachi(39),
		Matthew(40),
		Mark(41),
		Luke(42),
		John(43),
		Acts(44),
		Romans(45),
		Corinthians1(46),
		Corinthians2(47),
		Galatians(48),
		Ephesians(49),
		Philippians(50),
		Colossians(51),
		Thessalonians1(52),
		Thessalonians2(53),
		Timothy1(54),
		Timothy2(55),
		Titus(56),
		Philemon(57),
		Hebrews(58),
		James(59),
		Peter1(60),
		Peter2(61),
		John1(62),
		John2(63),
		John3(64),
		Jude(65),
		Revelation(66);
		
		private final int value;
		private static HashMap<Object, Object> map = new HashMap<>();
		private Books(int value) {
			this.value = value;
		}
		
		static {
			for (Books b : Books.values()) {
				map.put(b.value, b);
			}
		}
		
		public static Books valueOf(int books) {
			return (Books) map.get(books);
		}
		
		public int getValue() {
			return value;
		}
	}
	
	@Override
	public String getVerse(BibleVerse bv) {
		
		 // Use our DriverManager to connect to our DB
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/bible_db?useSSL=false", "root", "root");

            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT t FROM t_asv WHERE b = ? AND c = ? AND v = ?")) {
            preparedStatement.setInt(1, Books.valueOf(bv.getBook()).getValue());
            preparedStatement.setInt(2, bv.getChapter());
            preparedStatement.setInt(3, bv.getVerse());

            System.out.println("Within getVerse: " + preparedStatement);
            // Execute the query or update query
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
            	String text = result.getString("t");
            	System.out.println(text);
            	return text;
            }
            

        } catch (SQLException e) {
            // process sql exception
            System.out.println(e);
        }
		return null;
	}

	@Override
	public BibleVerse firstWordOccurence(String word) {
		
		String sql = "SELECT * FROM t_asv WHERE t LIKE '%" + word + "%' LIMIT 1";
		
		// Use our DriverManager to connect to our DB
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/bible_db?useSSL=false", "root", "root");

            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println("Within firstWordOccurence: " + preparedStatement);
            // Execute the query or update query
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
            	int book = result.getInt("b");
            	int chapter = result.getInt("c");
            	int verse = result.getInt("v");
            	String text = result.getString("t");
            	BibleVerse bv = new BibleVerse(Books.valueOf(book).toString(), chapter, verse, text);
            	return bv;
            }
            

        } catch (SQLException e) {
            // process sql exception
            System.out.println(e);
        }
		return null;
	}

	@Override
	public int wordNumberOccurences(String word) {
		String sql = "SELECT * FROM t_asv WHERE t LIKE '%" + word + "%'";
		
		// Use our DriverManager to connect to our DB
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/bible_db?useSSL=false", "root", "root");

            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println("Within wordNumberOccurences: " + preparedStatement);
            // Execute the query or update query
            ResultSet result = preparedStatement.executeQuery();
            int count = 0;
            while (result.next()) {
            	count++;
            }
            return count;

        } catch (SQLException e) {
            // process sql exception
            System.out.println(e);
        }
		return -1;
	}

	@Override
	public List<BibleVerse> getVerses() {	
		List<BibleVerse> list = new ArrayList<BibleVerse>();
		
		// Use our DriverManager to connect to our DB
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/bible_db?useSSL=false", "root", "root");

            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_asv")) {

            System.out.println("Within getVerses: " + preparedStatement);
            // Execute the query or update query
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
            	int book = result.getInt("b");
            	int chapter = result.getInt("c");
            	int verse = result.getInt("v");
            	String text = result.getString("t");
            	BibleVerse temp = new BibleVerse(Books.valueOf(book).toString(), chapter, verse, text);
            	list.add(temp);
            }
            return list;
            
        } catch (SQLException e) {
            // process sql exception
            System.out.println(e);
        }
		return null;
	}
	
	@Override
	public void insertBook(int book) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String dbURL = "jdbc:mysql://localhost:3306/bible_db?useSSL=false";
		String username = "root";
		String pword = "root";
		
        try {conn = DriverManager.getConnection(dbURL, username, pword);
            stmt = conn.prepareStatement("INSERT INTO t_asv" +
                    "  (id, b, c, v, t) VALUES " +
                    " (?, ?, ?, ?, ?);");
            stmt.setInt(1, 100);
            stmt.setInt(2, book);
            stmt.setInt(3, 100);
            stmt.setInt(4, 100);
            stmt.setString(5, "Test");

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
	}

}
