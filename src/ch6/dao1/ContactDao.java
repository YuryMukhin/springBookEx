package ch6.dao1;

import ch6.entities.Contact;

import java.util.List;

public interface ContactDao {
    String findLastNameById(Long id);
    List<Contact> findAll();
    List<Contact> findAllWithDetail(Long id);
}
