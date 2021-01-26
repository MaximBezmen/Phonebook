package service.mapper;

import entity.Contact;
import service.dto.ContactDto;

public class ContactMapper {
    private final AddressMapper addressMapper;
    public final PhoneNumberMapper phoneNumberMapper;

    public ContactMapper() {
        this.phoneNumberMapper = new PhoneNumberMapper();
        this.addressMapper = new AddressMapper();
    }

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
            contactDto.setAddress(addressMapper.toDto(entity.getAddress()));
            contactDto.setPhoneNumbers(phoneNumberMapper.toDtos(entity.getPhoneNumbers()));
        }
        return contactDto;
    }

    public Contact toEntity(ContactDto dto) {
        if (dto == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(dto.getId());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setMiddleName(dto.getMiddleName());
        contact.setBirthday(dto.getBirthday());
        contact.setGender(dto.getGender());
        contact.setCitizenship(dto.getCitizenship());
        contact.setFamilyStatus(dto.getFamilyStatus());
        contact.setWebSite(dto.getWebSite());
        contact.setEmail(dto.getEmail());
        contact.setCurrentPlaceOfWork(dto.getCurrentPlaceOfWork());
        contact.setAddress(addressMapper.toEntity(dto.getAddress()));
        contact.setPhoneNumbers(phoneNumberMapper.toEntities(dto.getPhoneNumbers()));
        return contact;
    }
}

