package service;

import entity.Contact;

public interface ContactService {
    String getContactById(Long id);

    String saveContact(Contact e);


}
