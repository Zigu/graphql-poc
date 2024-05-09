package de.pincservices.graphql.api.dto;

public record OptionDTO(String id, String name, double callPrice, double putPrice) implements InstrumentDTO {
}
