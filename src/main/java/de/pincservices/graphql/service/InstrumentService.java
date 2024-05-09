package de.pincservices.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.pincservices.graphql.model.Instrument;
import de.pincservices.graphql.model.Option;
import de.pincservices.graphql.model.Share;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
public class InstrumentService {

    private final List<Instrument> instruments = new ArrayList<>(List.of(
            new Option("option-1", "Option 1", 2.8, 1.2),
            new Option("option-2", "Option 2", 1.3, 78.2),
            new Share("share-1", "Share 1", 436),
            new Share("share-2", "Share 2", 41))
    );

    private final Sinks.Many<Instrument> sinks = Sinks.many().multicast().onBackpressureBuffer();

    public Instrument get(String id) {
        return instruments.stream()
                .filter(instrument -> instrument.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Option createOption(String name, double callPrice, double putPrice) {
        String id = UUID.randomUUID().toString();
        Option option = new Option("option-" + id, name, callPrice, putPrice);
        instruments.add(option);
        Sinks.EmitResult emitResult = sinks.tryEmitNext(option);

        if (emitResult.isFailure()) {
            log.warn("Could not send option {}. Reason: {}", option, emitResult);
        }
        return option;
    }

    public Share createShare(String name, double price) {
        String id = UUID.randomUUID().toString();
        Share share = new Share("share-" + id, name, price);
        instruments.add(share);
        Sinks.EmitResult emitResult = sinks.tryEmitNext(share);

        if (emitResult.isFailure()) {
            log.warn("Could not send share {}. Reason: {}", share, emitResult);
        }
        return share;
    }

    public Flux<Instrument> getNotificationFlux() {
        return sinks.asFlux();
    }

}
