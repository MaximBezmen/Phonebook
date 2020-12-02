package service.mapper;


import entity.Address;
import service.dto.AddressDto;

public class AddressMapper {

    public AddressDto toDto(Address entity) {
        AddressDto addressDto = null;;
        if (entity != null) {
            addressDto = new AddressDto();
            addressDto.setId(entity.getId());
            addressDto.setCountry(entity.getCountry());
            addressDto.setCity(entity.getCity());
            addressDto.setStreet(entity.getStreet());
            addressDto.setHouse(entity.getHouse());
            addressDto.setFlat(entity.getFlat());
        }

        return addressDto;
    }

}
