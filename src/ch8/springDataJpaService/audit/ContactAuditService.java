package ch8.springDataJpaService.audit;

import ch7.contacts_schema.ContactAudit;

import java.util.List;

public interface ContactAuditService {
    List<ContactAudit> findAll();
    ContactAudit findById(Long id);
    ContactAudit save(ContactAudit contact);
}
