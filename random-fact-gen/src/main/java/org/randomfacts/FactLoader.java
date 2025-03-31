package org.randomfacts;
import org.randomfacts.model.Fact;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;



@ApplicationScoped
public class FactLoader {
    @Inject
    FactRepository factRepository;

    @Inject
    @RestClient
    FactClient factClient;

    @Inject
    MyRedisCache cache;
    
    @Transactional
    public List<Fact> loadFacts() {
        List<Fact> facts;

            // Try fetching facts from API
            facts = factClient.getFacts("BI/IeqmqyE71luW3Dk9zFA==lux8sH4djZvHsvTS");
            if (facts.isEmpty()) {
                throw new RuntimeException("API returned empty list");
            }

            // Cache the facts in Redis
            
            for (Fact fact : facts) {
                cache.set("top", fact);
            }

            // Store in the database if not already present
            for (Fact fact : facts) {
                if (!factRepository.existsByText(fact.getFact())) {
                    factRepository.persist(fact);
                }
            }
            return facts;

    }
}
