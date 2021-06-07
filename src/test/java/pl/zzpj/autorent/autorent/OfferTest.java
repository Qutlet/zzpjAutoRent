package pl.zzpj.autorent.autorent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.OfferRepository;
import pl.zzpj.autorent.autorent.services.CarService;
import pl.zzpj.autorent.autorent.services.OfferService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {AutorentApplication.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OfferTest {

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private MockMvc mvc;

        @Autowired
        private OfferRepository offerRepository;

        @Autowired
        private OfferService offerService;

        Car  car = new Car("test","test","test");
        Offer offer;

        @Before
        public void setUp() {
            offer = new Offer(car.getId(),"testOffer");
            offerService.addOffer(offer);
        }

        @Test
        void contextLoads() {
//        assertThat(carController).isNotNull();
        }

        @Test
        public void addOfferTest() {
            assertThat(offerRepository.get(offer.getId()).get().equals(offer));
        }

        @Test
        public void updateCarTest() {
            offerService.updateOffer(offer.getId(),"0");
            assertThat(offerRepository.get(offer.getId()).get().isRented()).isFalse();
            offerService.updateOffer(offer.getId(),"5");
            assertThat(offerRepository.get(offer.getId()).get().isRented()).isTrue();
        }

        @Test
        public void editOfferTest() {
            Offer offer2 = new Offer(car.getId(),"NewOffer");
            offerService.editOffer(offer.getId(),offer2);
            assertThat(offerRepository.get(car.getId()).get().equals(offer2));
        }

        @Test
        public void deleteOfferTest() {
            offerService.deleteOffer(offer.getId());
            assertThat(offerRepository.get(offer.getId()).get() == null);
        }

        @Test
        public void getAllOffersTest() {
            assertThat(offerService.getAllOffers().size() != 0);
        }

        @Test
        public void getAllNoRentedCarsTest() {
            int size = offerService.getAllNoRentedOffers().size();
            offerService.updateOffer(offer.getId(),"10");
            assertThat(offerService.getAllNoRentedOffers().size()).isEqualTo(size-1);
        }
}
