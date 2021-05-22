package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.OfferRepository;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
//
//    public OfferEntity getOffer(long id) {
//        return offerRepository.findById(id).orElseThrow();
//    }
//
//    /**
//     * this function is responsible for ending car rental
//     */
//    public OfferEntity updateOffer(long id, long userid) {
//        OfferEntity offerToUpdate = offerRepository.getOne(id);
//        offerToUpdate.setClientID(userid);
//        if (userid == 0) {
//            offerToUpdate.setRented(false);
//        } else {
//            offerToUpdate.setRented(true);
//        }
//        OfferEntity updatedOffer = offerRepository.save(offerToUpdate);
//        return updatedOffer;
//    }
//
//    // TODO: 03.05.2021 check offer creator
//    // TODO: 03.05.2021 check if offer is live
//    public OfferEntity editOffer(OfferEntity offer) {
//        OfferEntity editedOffer = offerRepository.save(offer);
//        return editedOffer;
//    }
//
    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }
//
//
//    // TODO: 03.05.2021 check offer creator
//    public int deleteOffer(long id) {
//        OfferEntity offerToDelete = offerRepository.getOne(id);
//        if (offerToDelete.isRented()) {
//            return 1; //nie usunieto
//        } else {
//            offerRepository.deleteById(id);
//        }
//        return 0;//usunieto
//    }
//
//    public List<OfferEntity> getAllNoRentedOffers() {
//        List<OfferEntity> all = offerRepository.findAll();
//        all.removeIf(OfferEntity::isRented);
//        return all;
//    }
//
//    // TODO: 03.05.2021 add method to get all offers

}
