package ch8.springDataJpaService.audit;

import ch7.contacts_schema.ContactAudit;
import org.springframework.data.repository.CrudRepository;

public interface ContactAuditRepository extends CrudRepository<ContactAudit, Long> {
}
