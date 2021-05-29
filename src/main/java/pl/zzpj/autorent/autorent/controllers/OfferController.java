package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.services.OfferService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OfferController {


    @Autowired
    private OfferService offerService;
//
//    // TODO: 03.05.2021 optional: add get path for getting all offer; to discuss with UserController group
//
@GetMapping(path = "/offers", produces = APPLICATION_JSON_VALUE)
public ResponseEntity getAllNoRentedOffers() {
    final List<Offer> offers = offerService.getAllNoRentedOffers();
    return ResponseEntity.ok(offers);
}

    @GetMapping(path = "/offers/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getOfferById(@PathVariable String id) {
        final Offer offer = offerService.getOffer(id);
        return ResponseEntity.ok(offer);
    }

    @PostMapping(path = "/offers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
        return ResponseEntity.ok(offer);
    }

    @PutMapping(path = "/offers/rent/{userid}/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity rentCar(@PathVariable String id, @PathVariable String userid) {
        offerService.updateOffer(id, userid);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("offers/rent/" + updatedOffer.getId()));
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/offers/return/{userid}/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity returnCar(@PathVariable String id, @PathVariable String userid) {
        offerService.updateOffer(id, userid);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("offers/return/" + updatedOffer.getId()));
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/offers/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteOffer(@PathVariable long id) {
        offerService.deleteOffer(id);
//        if (response == 1) {
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/offers/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity editOffer(@PathVariable long id, @RequestBody Offer offer) {
        offerService.editOffer(offer);
        return ResponseEntity.ok().build();
    }
}
