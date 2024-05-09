package de.pincservices.graphql.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import de.pincservices.graphql.api.dto.OptionDTO;
import de.pincservices.graphql.api.dto.ShareDTO;

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

    @Test
    void createShare() {
        ShareDTO shareDTO = this.graphQlTester
                .documentName("createShare")
                .execute()
                .path("addShare")
                .entity(ShareDTO.class)
                .get();

        assertThat(shareDTO).usingRecursiveComparison().comparingOnlyFields("name", "price")
                .isEqualTo(new ShareDTO("", "new Share", 1.1));
    }

    @Test
    void createOption() {
        OptionDTO optionDTO = this.graphQlTester
                .documentName("createOption")
                .execute()
                .path("addOption")
                .entity(OptionDTO.class)
                .get();

        assertThat(optionDTO).usingRecursiveComparison().comparingOnlyFields("name", "callPrice", "putPrice")
                .isEqualTo(new OptionDTO("", "new Option", 1.1, 2.2));
    }

}
