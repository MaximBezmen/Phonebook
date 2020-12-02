package service.impl;

import database.dao.daoImpl.ContactDao;
import entity.Contact;
import service.ContactService;
import service.dto.ContactDto;
import service.json.CustomJson;
import service.mapper.ContactMapper;

public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public String getContactById(Long id) {
        Contact contactEntity = contactDao.read(id);
        return new CustomJson().contactToJson(new ContactMapper().toDto(contactEntity));
    }
}
