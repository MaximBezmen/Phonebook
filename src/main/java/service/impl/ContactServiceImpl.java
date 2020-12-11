package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.daoImpl.ContactDao;
import entity.Contact;
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
    public String saveContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);
        contactEntity = contactDao.save(contactEntity);
        String jsonContact = null;
        try {
            jsonContact = objectMapper.writeValueAsString(contactEntity);
        } catch (JsonProcessingException e) {
            System.err.print("Method saveContact: " + e.getMessage());
        }
        return jsonContact;
    }

    @Override
    public String getContactById(Long contactId) {
        Contact contactEntity = contactDao.read(contactId);
        String jsonContact = null;
        try {
            jsonContact = objectMapper.writeValueAsString(contactEntity);
        } catch (JsonProcessingException e) {
            System.err.print("Method saveContact: " + e.getMessage());
        }
        return jsonContact;
    }

    @Override
    public String updateContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);
        contactEntity = contactDao.update(contactEntity);
        String jsonContact = null;
        try {
            jsonContact = objectMapper.writeValueAsString(contactEntity);
        } catch (JsonProcessingException e) {
            System.err.print("Method saveContact: " + e.getMessage());
        }
        return jsonContact;
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.delete(id);
    }
}
