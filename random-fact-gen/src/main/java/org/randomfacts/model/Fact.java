package org.randomfacts.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Fact extends PanacheEntity {
    @JsonProperty("fact")
    public String fact;

    public Fact() {}

    @JsonCreator
    public Fact(@JsonProperty("fact") String fact) {
      this.fact = fact;
    }

    @JsonProperty("fact")
    public String getFact() {
      return fact;
    }
    
    public void setFact(String fact) {
      this.fact = fact;
  }
}
