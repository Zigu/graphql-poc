package de.pincservices.graphql.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import de.pincservices.graphql.model.Instrument;
import de.pincservices.graphql.model.Option;
import de.pincservices.graphql.model.Share;

@Service
public class InstrumentService {

    private final List<Instrument> instruments = Arrays.asList(
            new Option("option-1", "Option 1", 2.8, 1.2),
            new Option("option-2", "Option 2", 1.3, 78.2),
            new Share("share-1", "Share 1", 436),
            new Share("share-2", "Share 2", 41)
    );


    public Instrument get(String id) {
        return instruments.stream()
                .filter(instrument -> instrument.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
