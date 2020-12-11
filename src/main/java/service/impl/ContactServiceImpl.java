package service.impl;

import database.dao.daoImpl.ContactDao;
import service.ContactService;
import service.dto.ContactDto;
import service.mapper.ContactMapper;

public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactDao contactDao, ContactMapper contactMapper) {
        this.contactDao = contactDao;
        this.contactMapper = contactMapper;
    }

    @Override
    public String saveContact(ContactDto contactDto) {

        return null;
    }

    @Override
    public String getContactById(Long contactId) {
        return null;
    }

    @Override
    public String updateContact(ContactDto dto) {
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.delete(id);
    }
}
