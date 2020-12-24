package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.daoImpl.ContactDao;
import entity.Contact;
import exception.SQLExceptionDao;
import service.ContactService;
import service.dto.ContactDto;
import service.mapper.ContactMapper;


public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;
    private final ContactMapper contactMapper;
    private final ObjectMapper objectMapper;

    public ContactServiceImpl(ContactDao contactDao, ContactMapper contactMapper) {
        this.contactDao = contactDao;
        this.contactMapper = contactMapper;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ContactDto saveContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);
        try {
            contactEntity = contactDao.save(contactEntity);
        } catch (SQLExceptionDao e) {
            e.printStackTrace();
        }
        contactDto = contactMapper.toDto(contactEntity);
        return contactDto;
    }

    @Override
    public ContactDto getContactById(Long contactId) {
        Contact contactEntity = null;
        try {
            contactEntity = contactDao.read(contactId);
        } catch (SQLExceptionDao e) {
            e.printStackTrace();
        }
        ContactDto contactDto = contactMapper.toDto(contactEntity);
        return contactDto;
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);

        try {
            contactEntity = contactDao.update(contactEntity);
        }catch (SQLExceptionDao e){
            e.printStackTrace();
        }
        contactDto = contactMapper.toDto(contactEntity);
        return contactDto;
    }

    @Override
    public void deleteContact(Long id) {
        try {
            contactDao.delete(id);
        } catch (SQLExceptionDao e) {
        e.printStackTrace();
        }
    }
}
