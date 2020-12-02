package service.mapper;

import entity.Contact;
import service.dto.ContactDto;

public class ContactMapper {

   public ContactDto toDto(Contact entity) {
        ContactDto contactDto = null;
        if (entity != null) {
            contactDto = new ContactDto();
            contactDto.setId(entity.getId());
            contactDto.setFirstName(entity.getFirstName());
            contactDto.setLastName(entity.getLastName());
            contactDto.setMiddleName(entity.getMiddleName());
            contactDto.setBirthday(entity.getBirthday());
            contactDto.setGender(entity.getGender());
            contactDto.setCitizenship(entity.getCitizenship());
            contactDto.setFamilyStatus(entity.getFamilyStatus());
            contactDto.setWebSite(entity.getWebSite());
            contactDto.setEmail(entity.getEmail());
            contactDto.setCurrentPlaceOfWork(entity.getCurrentPlaceOfWork());
            contactDto.setAddress(new AddressMapper().toDto(entity.getAddress()));
        }
        return contactDto;
    }

}

