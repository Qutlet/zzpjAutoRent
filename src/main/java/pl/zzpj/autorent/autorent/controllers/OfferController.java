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

    @CrossOrigin
    @GetMapping(path = "/offers/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllOffers() throws IOException, InterruptedException {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @CrossOrigin
    @GetMapping(path = "/api/places", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getPlaces() throws IOException, InterruptedException {
        return ResponseEntity.ok(offerService.getPlaces());
    }

    @CrossOrigin
    @GetMapping(path = "/offers", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRentedOffers() {
        final List<Offer> offers = offerService.getAllNoRentedOffers();
        return ResponseEntity.ok(offers);
    }

    @CrossOrigin
    @GetMapping(path = "/offers/activeRented/{clientID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllClientOffers(@PathVariable String clientID) {
        final List<Offer> offers = offerService.getAllClientOffers(clientID);
        return ResponseEntity.ok(offers);
    }

    @CrossOrigin
    @GetMapping(path = "/offers/active/{ownerID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllOwnerOffers(@PathVariable String ownerID) {
        final List<Offer> offers = offerService.getAllOwnerOffers(ownerID);
        return ResponseEntity.ok(offers);
    }

    @CrossOrigin
    @GetMapping(path = "/offers/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getOfferById(@PathVariable String id) throws IOException, InterruptedException {
        final Offer offer = offerService.getOffer(id);
        return ResponseEntity.ok(offer);
    }

    @CrossOrigin
    @PostMapping(path = "/offers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
        return ResponseEntity.ok(offer);
    }

    @CrossOrigin
    @PutMapping(path = "/offers/rent/{userid}/{id}")
    public ResponseEntity rentCar(@PathVariable String id, @PathVariable String userid) {
        offerService.updateOffer(id, userid);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping(path = "/offers/return/{userid}/{id}")
    public ResponseEntity returnCar(@PathVariable String id, @PathVariable String userid) {
        offerService.updateOffer(id, userid);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping(path = "/offers/{id}")
    public ResponseEntity deleteOffer(@PathVariable String id) {
        offerService.deleteOffer(id);
        return ResponseEntity.accepted().build();
    }

    @CrossOrigin
    @PutMapping(path = "/offers/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity editOffer(@PathVariable String id, @RequestBody Offer offer) {
        offerService.editOffer(id, offer);
        return ResponseEntity.ok().build();
    }


}
