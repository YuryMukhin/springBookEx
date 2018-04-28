package ch8.springDataJpaService.audit.hiber_envers;

import ch7.contacts_schema.ContactAudit;

import java.util.List;

public interface ContactAuditService {
    List<ContactAudit> findAll();
    ContactAudit findAuditByRevision(Long id, int rev);
    ContactAudit save(ContactAudit contact);
    ContactAudit findById(Long id);
}
