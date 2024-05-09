package de.pincservices.graphql.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
public class InstrumentControllerIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getOption() {
        this.graphQlTester
                .documentName("getInstrument")
                .variable("id", "option-1")
                .execute()
                .path("instrumentById")
                .matchesJson("""
                    {
                        "id": "option-1",
                        "name": "Option 1",
                        "callPrice": 2.8,
                        "putPrice": 1.2
                    }
                """);
    }

    @Test
    void getShare() {
        this.graphQlTester
                .documentName("getInstrument")
                .variable("id", "share-2")
                .execute()
                .path("instrumentById")
                .matchesJson("""
                    {
                        "id": "share-2",
                        "name": "Share 2",
                        "price": 41.0
                    }
                """);
    }
}
