# AutoRent
## Car sharing application


AutoRent is web application created in spring boot,
which allows user to:

- Create rent offer and manage it
- Rent a car
- Get list of intresting objects near his location -- in progress --
- 🚗 Cars 



# Rest
Test application via postman
### Get 

To get all cars use
```sh
/cars
```

To get car by its ID use
```sh
/cars/{id}
```

To get all cars that aren't in offers use
```sh
/cars/available/{userID}
```

To get all offers use
```sh
/offers/all
```

To get all no rented offers use
```sh
/offers
```

To get all rented offers by specific user use
```sh
/offers/activeRented/{clientID}
```

To get all created offers by specific user use
```sh
/offers/active/{ownerID}
```

To get offer by its id use
```sh
/offers/{id}
```


### Post

To add car use body:
```sh
carName: name
carBrandName : name
carModelName : name
isRented : false
engine : engine
enginePower : enginePower
color : color
gearBox : gearBox
fuel : fuel
description : description
country : country
```

in
```sh
/cars
```

To add offer use body:
```sh
carID : carID
ownerID : onwerID
clientID : clientID
price : price
offerName : offerName
description : description
phone : phone
email : email
rented : false
```

in
```sh
/offers
```

### Put

To edit car provide changed body and use
```sh
/cars/{carID}
```

To change car rented status use
```sh
/cars/rent/{carID}
```

To edit offer use
```sh
/offers/{offerID}
```

To start rent offer(rent car) use
```sh
/offers/rent/{userID}/{offerID}
```

To end offer(return car) use
```sh
/offers/rent/{userID}/{offerID}
```

### Delete

To delete a car use
```sh
/cars/{carID}
```

To delete a offer use
```sh
/offers/{offerID}
```
