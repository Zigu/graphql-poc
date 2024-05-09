package de.pincservices.graphql.api;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import de.pincservices.graphql.api.dto.InstrumentDTO;
import de.pincservices.graphql.service.InstrumentService;
import reactor.core.publisher.Flux;

@Controller
public class InstrumentNotificationController {

    private final Flux<InstrumentDTO> instrumentDTOFlux;

    public InstrumentNotificationController(InstrumentService instrumentService, DTOMapper dtoMapper) {
        instrumentDTOFlux = instrumentService.getNotificationFlux()
                .map(dtoMapper::map);
    }

    @SubscriptionMapping("notifyNewInstrumentDTO")
    public Flux<InstrumentDTO> notifyNewInstrument() {
        return instrumentDTOFlux;
    }

}
