package org.randomfacts;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.randomfacts.model.Fact;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
@RegisterRestClient(baseUri = "https://api.api-ninjas.com/v1/facts")
public interface FactClient {
  
 @GET
 @Produces(MediaType.APPLICATION_JSON)
  List<Fact> getFacts(@HeaderParam("X-Api-Key") String apiKey);
  
}

