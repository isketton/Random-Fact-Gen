package org.randomfacts;


import org.randomfacts.model.Fact;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FactRepository implements PanacheRepository<Fact> {
    public boolean existsByText(String fact) {
        return count("fact", fact) > 0;
    }
}
