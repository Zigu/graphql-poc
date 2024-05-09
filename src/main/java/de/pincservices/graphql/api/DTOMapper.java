package de.pincservices.graphql.api;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

import de.pincservices.graphql.api.dto.InstrumentDTO;
import de.pincservices.graphql.api.dto.OptionDTO;
import de.pincservices.graphql.api.dto.ShareDTO;
import de.pincservices.graphql.model.Instrument;
import de.pincservices.graphql.model.Option;
import de.pincservices.graphql.model.Share;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface DTOMapper {

    @SubclassMapping(source = Share.class, target = ShareDTO.class)
    @SubclassMapping(source = Option.class, target = OptionDTO.class)
    InstrumentDTO map(Instrument instrument);
}
