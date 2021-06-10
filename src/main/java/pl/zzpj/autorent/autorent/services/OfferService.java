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

    public Offer getOffer(String id) throws IOException, InterruptedException {


        return offerRepository.get(id).orElseThrow();
    }

    public String getPlaces() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://opentripmap-places-v1.p.rapidapi.com/en/places/radius?lat=51.855279&lon=19.39332&radius=50000&kinds=tourist_object"))
                .header("x-rapidapi-key", "2e775c2a61mshe8a9d515320ba79p148d59jsn25cb96372d73")
                .header("x-rapidapi-host", "opentripmap-places-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        return response.body();
    }


    /**
     * this function is responsible for ending car rental
     */
    public void updateOffer(String id, String userid) {
        Optional<Offer> offerToUpdate = offerRepository.get(id);
        //offerToUpdate.setClientID(userid);
        offerToUpdate.get().setClientID(userid);
        if (userid.equals("0")) {
            offerToUpdate.get().setRented(false);
        } else {
            offerToUpdate.get().setRented(true);
        }
        offerRepository.save(offerToUpdate.get());
        //return updatedOffer;
    }

    // TODO: 03.05.2021 check offer creator
    // TODO: 03.05.2021 check if offer is live
    public void editOffer(String id, Offer offer) {
        offerRepository.update(id, offer);
    }

    public void addOffer(Offer offer) {
        offer.setId(UUID.randomUUID().toString());
        offerRepository.save(offer);

    }

    //
//
//    // TODO: 03.05.2021 check offer creator

    public void deleteOffer(String id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getAllNoRentedOffers() {
        List<Offer> all = offerRepository.retrieveAll();
        all.removeIf(Offer::isRented);
        return all;
    }

    public List<Offer> getAllClientOffers(String clientID) {
        List<Offer> all = offerRepository.retrieveAll();
        all.removeIf(offer -> !offer.getClientID().equals(clientID));
        return all;
    }

    public List<Offer> getAllOwnerOffers(String ownerID) {
        List<Offer> all = offerRepository.retrieveAll();
        all.removeIf(offer -> !offer.getOwnerID().equals(ownerID));
        return all;
    }
    public List<Offer> getAllOffers() {

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from=EUR&to=PLN&amount=4"))
//                .header("x-rapidapi-key", "2e775c2a61mshe8a9d515320ba79p148d59jsn25cb96372d73")
//                .header("x-rapidapi-host", "currency-converter5.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://ip-geolocation-ipwhois-io.p.rapidapi.com/json/?ip=46.148.79.143"))
//                .header("x-rapidapi-key", "2e775c2a61mshe8a9d515320ba79p148d59jsn25cb96372d73")
//                .header("x-rapidapi-host", "ip-geolocation-ipwhois-io.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        //

        return offerRepository.retrieveAll();
    }

}
