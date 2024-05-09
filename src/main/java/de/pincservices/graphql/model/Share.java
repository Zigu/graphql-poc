package de.pincservices.graphql.model;

public record Share(String id, String name, double price) implements Instrument {
}
