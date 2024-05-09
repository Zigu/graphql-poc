package de.pincservices.graphql.api;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import de.pincservices.graphql.api.dto.InstrumentDTO;
import de.pincservices.graphql.model.Instrument;
import de.pincservices.graphql.service.InstrumentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class InstrumentController {

    private final InstrumentService instrumentService;
    private final DTOMapper dtoMapper;

    @QueryMapping
    public InstrumentDTO instrumentById(@Argument String id) {
        Instrument instrument = instrumentService.get(id);
        return dtoMapper.map(instrument);
    }
}
