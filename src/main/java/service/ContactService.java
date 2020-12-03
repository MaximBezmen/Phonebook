package service;

import entity.Contact;
import service.dto.ContactDto;

public interface ContactService {
    String getContactById(Long id);

    String saveContact(ContactDto e);

    String readContact(Long contactId);
}
