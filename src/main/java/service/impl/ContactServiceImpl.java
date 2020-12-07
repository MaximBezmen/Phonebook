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
    public String saveContact(ContactDto contactDto) {

        return customJson.contactToJson(contactMapper.toDto(contactDao.read(contactDao.save(contactMapper.toEntity(contactDto)))));
    }

    @Override
    public String getContactById(Long contactId) {
        return customJson.contactToJson(contactMapper.toDto(contactDao.read(contactId)));
    }

    @Override
    public String updateContact(ContactDto dto) {
        return customJson.contactToJson(contactMapper.toDto(contactDao.update(contactDao.update(contactMapper.toEntity(dto)))));
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.delete(id);
    }
}
