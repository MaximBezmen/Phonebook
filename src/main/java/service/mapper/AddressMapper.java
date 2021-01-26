package service.mapper;


import entity.Address;
import service.dto.AddressDto;

public class AddressMapper {

    public AddressDto toDto(Address entity) {

        if (entity == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setId(entity.getId());
        addressDto.setCountry(entity.getCountry());
        addressDto.setCity(entity.getCity());
        addressDto.setStreet(entity.getStreet());
        addressDto.setHouse(entity.getHouse());
        addressDto.setFlat(entity.getFlat());

        return addressDto;
    }

    public Address toEntity(AddressDto dto) {

        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setId(dto.getId());
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setHouse(dto.getHouse());
        address.setFlat(dto.getFlat());
        return address;
    }
}
