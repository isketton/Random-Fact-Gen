package org.randomfacts;
import org.randomfacts.model.Fact;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class FactClientTest {

  @Inject
  @RestClient
  FactClient factClient;

  @Test
  public void testGetFactsFromRealApi() {
      String apiKey = "BI/IeqmqyE71luW3Dk9zFA==lux8sH4djZvHsvTS";
      List<Fact> facts = factClient.getFacts(apiKey);

      assertNotNull(facts, "Fact list should not be null");
      assertFalse(facts.isEmpty(), "Fact list should not be empty");
      assertNotNull(facts.get(0).getFact(), "Fact should have a text field");
  }
}
