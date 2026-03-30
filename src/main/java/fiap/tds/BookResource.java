package fiap.tds;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/books")
public class BookResource {

    private final ArrayList<Book> collection = new ArrayList<Book>(){{
        add(new Book("I, Robot", "Isaac Asimov"));
        add(new Book("IT", "Stephen King"));
        add(new Book("Duna", "Frank Herbert"));
    }};

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksTest(){
        return collection;
    }

    @GET
    @Path("/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("index") int index){
        return collection.get(index);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book createBook(Book book){
        collection.add(book);
        return book;
    }

    @DELETE
    @Path("/{index}")
    public void deleteBook(@PathParam("index") int index){
        collection.remove(index);
    }
}
