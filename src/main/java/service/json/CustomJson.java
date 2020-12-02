package service.json;

import service.dto.AddressDto;
import service.dto.ContactDto;

public class CustomJson {

    public String addressToJsonForContact(AddressDto dto) {

        return "Address[" +
                "id=" + dto.getId() +
                ", country='" + dto.getCountry() +
                ", city='" + dto.getCity() +
                ", street='" + dto.getStreet() +
                ", house=" + dto.getHouse().toString() +
                ", flat=" + dto.getFlat().toString() +
                "]";
    }

    public String addressDtoToJson(AddressDto dto) {

        return "Address{" +
                "id=" + dto.getId() +
                ", country='" + dto.getCountry() +
                ", city='" + dto.getCity() +
                ", street='" + dto.getStreet() +
                ", house=" + dto.getHouse().toString() +
                ", flat=" + dto.getFlat().toString() +
                "}";
    }
    public String contactToJson (ContactDto dto){
        return "Contact{" +
                "id=" + dto.getId() +
                ", firstName='" + dto.getFirstName() +
                ", lastName='" + dto.getLastName() +
                ", middleName='" +dto.getMiddleName()+
                ", birthday=" + dto.getBirthday().toString() +
                ", gender=" + dto.getGender().name() +
                ", citizenship='" + dto.getCitizenship() +
                ", familyStatus=" + dto.getFamilyStatus().name() +
                ", webSite='" + dto.getWebSite() +
                ", email='" + dto.getEmail() +
                ", currentPlaceOfWork='" + dto.getCurrentPlaceOfWork() +
                ", address=" + addressToJsonForContact(dto.getAddress()) +
                '}';
    }
}
