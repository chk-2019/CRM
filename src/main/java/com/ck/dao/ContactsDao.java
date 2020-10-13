package com.ck.dao;

import com.ck.domain.Clue;
import com.ck.domain.Contacts;

public interface ContactsDao {
    int insertContacts(Clue clue,String customerId);
    Contacts selectContactsByCusIdAndName(String cusId,String name);
}
