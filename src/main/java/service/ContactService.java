package service;

import entity.Contact;
import service.dto.ContactDto;

public interface ContactService {

    ContactDto saveContact(ContactDto dto);

    ContactDto getContactById(Long contactId);

    ContactDto updateContact(ContactDto dto);

    void deleteContact(Long id);
}
