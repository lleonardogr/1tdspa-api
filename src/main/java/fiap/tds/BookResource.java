package fiap.tds;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response getBookById(@PathParam("index") int index){
        return index > collection.size() - 1 ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.ok().entity(collection.get(index)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book){
        if(book.getTitle() == null || book.getAuthor() == null)
            return Response.status(Response.Status.BAD_REQUEST).entity(book).build();
        collection.add(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @DELETE
    @Path("/{index}")
    public void deleteBook(@PathParam("index") int index){
        collection.remove(index);
    }
}
