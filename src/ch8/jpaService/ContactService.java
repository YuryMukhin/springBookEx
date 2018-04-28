package ch8.jpaService;


import ch7.contacts_schema.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact c);
    void delete(Contact c);
    List<Contact> findAllBynativeQuery();
    List<Contact> findByCriteriaQuery(String firstName, String lastName);
}
