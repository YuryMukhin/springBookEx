package ch7.dao;

import ch7.contacts_schema.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact c);
    void delete(Contact c);
}
