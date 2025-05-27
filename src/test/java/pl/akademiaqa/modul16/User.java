package pl.akademiaqa.modul16;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores any unknown properties in the JSON
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geo {
        private String lat;
        private String lng;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }

    public static User getRandomUser() {
        Faker faker = new Faker();
        User user = new User();

        user.setName(faker.name().fullName());
        user.setUsername(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setWebsite(faker.internet().url());

        User.Address address = new User.Address();
        address.setStreet(faker.address().streetName());
        address.setSuite(faker.address().buildingNumber());
        address.setCity(faker.address().city());
        address.setZipcode(faker.address().zipCode());

        User.Geo geo = new User.Geo();
        geo.setLat(faker.address().latitude());
        geo.setLng(faker.address().longitude());
        address.setGeo(geo);

        user.setAddress(address);

        User.Company company = new User.Company();
        company.setName(faker.company().name());
        company.setCatchPhrase(faker.company().catchPhrase());
        company.setBs(faker.company().bs());

        user.setCompany(company);
        return user;
    }
}