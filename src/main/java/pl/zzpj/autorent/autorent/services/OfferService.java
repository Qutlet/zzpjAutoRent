package pl.zzpj.autorent.autorent.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.OfferRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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


    public String getConversionRate(String currency) throws IOException{
        String url_str = "https://v6.exchangerate-api.com/v6/80d42cc4b1182ff527f16534/pair/PLN/" + currency;
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        return jsonobj.get("result").getAsString();
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
        return offerRepository.retrieveAll();
    }


}
