package de.pincservices.graphql.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import de.pincservices.graphql.api.dto.OptionDTO;
import de.pincservices.graphql.api.dto.ShareDTO;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

// Attention: It seems that the StepVerifier closes the Flux at verify() call and this results in
// problems executing multiple test methods.
// Therefore, mark the test to reset the ApplicationContext after each method.
@SpringBootTest
@AutoConfigureGraphQlTester
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InstrumentNotificationControllerIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void notification_share() {
        // Although the subscription is configured to return an InstrumentDTO the flux has to be configured
        // to handle ShareDTOs in this test.
        final Flux<ShareDTO> flux = this.graphQlTester.documentName("subscription")
                .executeSubscription()
                .toFlux("notifyNewInstrumentDTO", ShareDTO.class);

        this.graphQlTester
                .documentName("createShare")
                .execute();

        StepVerifier.create(flux)
                .assertNext(instrumentDTO ->
                        assertThat(instrumentDTO).usingRecursiveComparison()
                                .comparingOnlyFields("name", "price")
                                .isEqualTo(new ShareDTO("", "new Share", 1.1))
                )
                .thenCancel()
                .verify();
    }

    @Test
    void notification_option() {
        // Although the subscription is configured to return an InstrumentDTO the flux has to be configured
        // to handle OptionDTOs in this test.
        final Flux<OptionDTO> flux = this.graphQlTester.documentName("subscription")
                .executeSubscription()
                .toFlux("notifyNewInstrumentDTO", OptionDTO.class);

        this.graphQlTester
                .documentName("createOption")
                .execute();

        StepVerifier.create(flux)
                .assertNext(optionDTO ->
                        assertThat(optionDTO).usingRecursiveComparison()
                                .comparingOnlyFields("name", "callPrice", "putPrice")
                                .isEqualTo(new OptionDTO("", "new Option", 1.1, 2.2))
                )
                .thenCancel()
                .verify();
    }

}
