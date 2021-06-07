package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.OfferRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private CarRepository carRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer getOffer(String id) {
        return offerRepository.get(id).orElseThrow();
    }

    /**
     * this function is responsible for ending car rental
     */
    public void updateOffer(String id, String userid) {
        Optional<Offer> offerToUpdate = offerRepository.get(id);
        offerToUpdate.get().setClientID(userid);
        if (userid.equals("0")) {
            offerToUpdate.get().setRented(false);
        } else {
            offerToUpdate.get().setRented(true);
        }
        offerRepository.save(offerToUpdate.get());
    }

    // TODO: 03.05.2021 check offer creator
    public void editOffer(String id, Offer offer) {
        offerRepository.update(id, offer);
    }

    public void addOffer(Offer offer) {
        offer.setId(UUID.randomUUID().toString());
        offerRepository.save(offer);
    }

   // TODO: 03.05.2021 check offer creator
    public void deleteOffer(String id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getAllNoRentedOffers() {
        List<Offer> all = offerRepository.retrieveAll();
        all.removeIf(Offer::isRented);
        return all;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.retrieveAll();
    }


}
