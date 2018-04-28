package ch8.springDataJpaService.contact;

import ch7.contacts_schema.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstAndLastName(String firstName, String lastName);
}
