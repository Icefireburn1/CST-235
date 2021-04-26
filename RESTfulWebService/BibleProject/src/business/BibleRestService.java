package business;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.BibleVerse;

@RequestScoped
@Path("/bible")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BibleRestService {
	@Inject
	BibleServiceInterface service;
	
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BibleVerse> getOrdersAsJson() {
		return service.getVerses();
	}
	
	@GET
	@Path("/getverse")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrdersAsJson(@QueryParam("book") String book, @QueryParam("chapter") int chapter, @QueryParam("verse") int verse) {
		BibleVerse v = new BibleVerse(book, chapter, verse);
		String temp = service.getVerse(v);
		return temp;
	}
	
	@GET
	@Path("getxml")
	@Produces(MediaType.APPLICATION_XML)
	public BibleVerse[] getOrdersAsXml() {
		BibleVerse[] items = new BibleVerse[service.getVerses().size()];
		items = service.getVerses().toArray(items);
		return items;
	}
	
	@POST
	@Path("/newbook")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertNewPost(@QueryParam("book") int book) {
		service.insertBook(book);
		return "Item successfully inserted";
	}
}
