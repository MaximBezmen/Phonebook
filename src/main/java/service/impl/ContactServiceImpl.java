package service.impl;

import database.dao.DAO;
import database.dao.daoImpl.ContactDao;
import entity.Contact;
import exception.SQLExceptionDao;
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
    public ContactDto saveContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);
        try {
            contactEntity = contactDao.save(contactEntity, contactDao.connect());
        } catch (SQLExceptionDao e) {
            e.printStackTrace();
        }
        return contactMapper.toDto(contactEntity);
    }

    @Override
    public ContactDto getContactById(Long contactId) {
        Contact contactEntity = null;
        try {
            contactEntity = contactDao.read(contactId, contactDao.connect());
        } catch (SQLExceptionDao e) {
            e.printStackTrace();
        }

        return contactMapper.toDto(contactEntity);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);

        try {
            contactEntity = contactDao.update(contactEntity, contactDao.connect());
        } catch (SQLExceptionDao e) {
            e.printStackTrace();
        }
       ;
        return contactMapper.toDto(contactEntity);
    }

    @Override
    public void deleteContact(Long id) {
        try {
            contactDao.delete(id, contactDao.connect());
        } catch (SQLExceptionDao e) {
            e.printStackTrace();
        }
    }
}
