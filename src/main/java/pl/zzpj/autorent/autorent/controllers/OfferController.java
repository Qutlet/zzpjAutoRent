package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.services.OfferService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OfferController {

    @Autowired
    private OfferService offerService;

    /**
     * Gets all offers
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @CrossOrigin
    @GetMapping(path = "/offers/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllOffers() throws IOException, InterruptedException {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    /**
     * External api gets interesing places to visit
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @CrossOrigin
    @GetMapping(path = "/api/places", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getPlaces() throws IOException, InterruptedException {
        return ResponseEntity.ok(offerService.getPlaces());
    }

    /**
     * External api, returs conversion rate of PLN and currency given as a param
     * @param currId
     * @return
     * @throws IOException
     */
    @CrossOrigin
    @GetMapping(path = "/api/conversionRate/{currId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getConversionRate(@PathVariable String currId) throws IOException {
        return ResponseEntity.ok(offerService.getConversionRate(currId));
    }

    /**
     * Gets all unrented offers
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/offers", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRentedOffers() {
        final List<Offer> offers = offerService.getAllNoRentedOffers();
        return ResponseEntity.ok(offers);
    }

    /**
     * Gets all offers that user are currently renting
     * @param clientID
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/offers/activeRented/{clientID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllClientOffers(@PathVariable String clientID) {
        final List<Offer> offers = offerService.getAllClientOffers(clientID);
        return ResponseEntity.ok(offers);
    }

    /**
     * Gets all ofers that user is owner of
     * @param ownerID
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/offers/active/{ownerID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllOwnerOffers(@PathVariable String ownerID) {
        final List<Offer> offers = offerService.getAllOwnerOffers(ownerID);
        return ResponseEntity.ok(offers);
    }

    /**
     * Gets specific offer by its id
     * @param id
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @CrossOrigin
    @GetMapping(path = "/offers/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getOfferById(@PathVariable String id) throws IOException, InterruptedException {
        final Offer offer = offerService.getOffer(id);
        return ResponseEntity.ok(offer);
    }

    /**
     * Adds offer to database
     * @param offer
     * @return
     */
    @CrossOrigin
    @PostMapping(path = "/offers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
        return ResponseEntity.ok(offer);
    }

    /**
     * Make user, client of specific offer and also changes it to a rented offer
     * @param id
     * @param userid
     * @return
     */
    @CrossOrigin
    @PutMapping(path = "/offers/rent/{userid}/{id}")
    public ResponseEntity rentCar(@PathVariable String id, @PathVariable String userid) {
        offerService.updateOffer(id, userid);
        return ResponseEntity.ok().build();
    }

    /**
     * Changes client of specific offer and also changes it to a unrented offer
     * @param id
     * @param userid
     * @return
     */
    @CrossOrigin
    @PutMapping(path = "/offers/return/{userid}/{id}")
    public ResponseEntity returnCar(@PathVariable String id, @PathVariable String userid) {
        offerService.updateOffer(id, userid);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes offer
     * @param id
     * @return
     */
    @CrossOrigin
    @DeleteMapping(path = "/offers/{id}")
    public ResponseEntity deleteOffer(@PathVariable String id) {
        offerService.deleteOffer(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Allows to change existing offer values
     * @param id
     * @param offer
     * @return
     */
    @CrossOrigin
    @PutMapping(path = "/offers/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity editOffer(@PathVariable String id, @RequestBody Offer offer) {
        offerService.editOffer(id, offer);
        return ResponseEntity.ok().build();
    }


}
