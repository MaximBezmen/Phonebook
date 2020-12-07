package service;

import entity.Contact;
import service.dto.ContactDto;

public interface ContactService {

    String saveContact(ContactDto dto);

    String getContactById(Long contactId);

    String updateContact(ContactDto dto);

    void deleteContact(Long id);
}
