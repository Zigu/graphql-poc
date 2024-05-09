package de.pincservices.graphql.model;

public record Option(String id, String name, double callPrice, double putPrice) implements Instrument {
}
