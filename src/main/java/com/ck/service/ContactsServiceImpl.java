package com.ck.service;

import com.ck.dao.ContactsDao;
import com.ck.domain.Clue;
import com.ck.domain.Contacts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ContactsServiceImpl implements ContactsService {
    @Resource
    private ContactsDao contactsDao;
    @Override
    public int insertContacts(Clue clue,String customerId) {

        int i = contactsDao.insertContacts(clue,customerId);
        return i;
    }

    @Override
    public Contacts getConByCusAndName(String cusId, String name) {
        Contacts contacts = contactsDao.selectContactsByCusIdAndName(cusId, name);
        return contacts;
    }
}
