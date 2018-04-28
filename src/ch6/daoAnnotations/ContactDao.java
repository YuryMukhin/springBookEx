package ch6.daoAnnotations;

import ch6.entities.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    String findFirstNameById(Long id);
    List<Contact> findAllWithDetail(Long id);
    void insert(Contact contact);
    void insertWithDetail(Contact contact);
    void update(Contact contact);
}
