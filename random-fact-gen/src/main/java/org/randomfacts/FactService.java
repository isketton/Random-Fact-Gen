package org.randomfacts;


import org.randomfacts.model.Fact;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Path("/facts")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FactService {
  
     @Inject
    FactRepository factRepository;

    @Inject
    FactLoader factLoader;

    @Inject
    MyRedisCache cache;

    @GET
    public String getFacts(@QueryParam("limit") int limit) {
        try {
            List<Fact> facts = factLoader.loadFacts();
            if (!facts.isEmpty()) {
                return facts.get(0).getFact();
            }

            else if (cache.get("top") != null) {
                return cache.get("top").getFact();
            }
            

            List<Fact> dbFacts = factRepository.findAll().page(0, 1).list();
            if (!dbFacts.isEmpty() && dbFacts.get(0).getFact() != null) {
                cache.set("top", dbFacts.get(0));
                return dbFacts.get(0).getFact();
            }
            
           return facts.get(0).getFact();
        } catch (Exception e) {
            return "Fallback fact: The sky is blue."; // Hardcoded backup
        }
    }
}
