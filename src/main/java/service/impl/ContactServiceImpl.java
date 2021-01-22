package service.impl;

import database.dao.daoImpl.ContactDao;
import entity.Contact;
import exception.SQLExceptionDao;
import service.ContactService;
import service.dto.ContactDto;
import service.mapper.ContactMapper;

import java.sql.Connection;
import java.sql.SQLException;


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
            Connection connection = contactDao.connect();
            contactEntity = contactDao.save(contactEntity, connection);
            connection.close();
        } catch (SQLExceptionDao | SQLException e) {
            e.printStackTrace();
        }
        return contactMapper.toDto(contactEntity);
    }

    @Override
    public ContactDto getContactById(Long contactId) {
        Contact contactEntity = null;
        try {
            Connection connection = contactDao.connect();
            contactEntity = contactDao.read(contactId, connection);
            connection.close();
        } catch (SQLExceptionDao | SQLException e) {
            e.printStackTrace();
        }

        return contactMapper.toDto(contactEntity);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        Contact contactEntity = contactMapper.toEntity(contactDto);

        try {
            Connection connection = contactDao.connect();
            contactEntity = contactDao.update(contactEntity, connection);
            connection.close();
        } catch (SQLExceptionDao | SQLException e) {
            e.printStackTrace();
        }
        return contactMapper.toDto(contactEntity);
    }

    @Override
    public void deleteContact(Long id) {
        try {
            Connection connection = contactDao.connect();
            contactDao.delete(id, connection);
            connection.close();
        } catch (SQLExceptionDao | SQLException e) {
            e.printStackTrace();
        }
    }
}
