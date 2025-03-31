package org.randomfacts.model;
import java.util.List;
public class FactWrapper {
  private List<Fact> facts;

  // Getters and setters
  public List<Fact> getFacts() {
      return facts;
  }

  public void setFacts(List<Fact> facts) {
      this.facts = facts;
  }
}
