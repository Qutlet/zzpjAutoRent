package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer getOffer(long id) {
        return offerRepository.get(String.valueOf(id)).orElseThrow();
    }

    /**
     * this function is responsible for ending car rental
     */
    public void updateOffer(long id, long userid) {
        Optional<Offer> offerToUpdate = offerRepository.get(String.valueOf(id));
        //offerToUpdate.setClientID(userid);
        offerToUpdate.get().setClientID(userid);
        if (userid == 0) {
            offerToUpdate.get().setRented(false);
        } else {
            offerToUpdate.get().setRented(true);
        }
        offerRepository.save(offerToUpdate.get());
        //return updatedOffer;
    }

    // TODO: 03.05.2021 check offer creator
    // TODO: 03.05.2021 check if offer is live
    public void editOffer(Offer offer) {
        offerRepository.save(offer);
        //return editedOffer;
    }

    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    //
//
//    // TODO: 03.05.2021 check offer creator
    public void deleteOffer(long id) {
//        Offer offerToDelete = offerRepository.getOne(id);
//        if (offerToDelete.isRented()) {
//            return 1; //nie usunieto
//        } else {
//            offerRepository.deleteById(id);
//        }
//        return 0;//usunieto
    }

    //
    public List<Offer> getAllNoRentedOffers() {
        List<Offer> all = offerRepository.retrieveAll();
        all.removeIf(Offer::isRented);
        return all;
    }
//
//    // TODO: 03.05.2021 add method to get all offers

}
