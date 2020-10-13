package com.ck.service;

import com.ck.domain.Clue;
import com.ck.domain.Contacts;

public interface ContactsService {
    int insertContacts(Clue clue,String customerId);
    Contacts getConByCusAndName(String cusId,String name);
}
