package service.impl;

import database.dao.daoImpl.ContactDao;
import entity.Contact;
import service.ContactService;
import service.dto.ContactDto;
import service.json.CustomJson;
import service.mapper.ContactMapper;

public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;
    private final CustomJson customJson;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactDao contactDao, CustomJson customJson, ContactMapper contactMapper) {
        this.contactDao = contactDao;
        this.customJson = customJson;
        this.contactMapper = contactMapper;
    }

    @Override
    public String getContactById(Long id) {
        Contact contactEntity = contactDao.read(id);
        return customJson.contactToJson(contactMapper.toDto(contactEntity));
    }

    @Override
    public String saveContact(ContactDto contactDto) {

        return customJson.contactToJson(contactMapper.toDto(contactDao.read(contactDao.save())));
    }

    @Override
    public String readContact(Long contactId) {
        return customJson.contactToJson(contactMapper.toDto(contactDao.read(contactId)));
    }
}
