package org.randomfacts;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.randomfacts.model.Fact;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class FactLoaderTest {
    @Inject
    @RestClient
    FactClient factClient;

    @InjectMocks
    FactRepository factRepository;

    @Inject
    FactLoader factLoader;

    @Test
    @Transactional
    public void testLoadFacts() {
        Fact mockFact = new Fact("The Eiffel Tower is repainted every 7 years.");
        List<Fact> mockFacts = List.of(mockFact);

        when(factClient.getFacts(anyString())).thenReturn(mockFacts);
        when(factRepository.existsByText(mockFact.getFact())).thenReturn(false);

        factLoader.loadFacts();

        verify(factClient, times(1)).getFacts(anyString());
        verify(factRepository, times(1)).persist(any(Fact.class));
    }
  }