package ch7.contacts_schema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contact")
@NamedQueries({
        @NamedQuery(
                name="Contact.findAll",
                query = "select c from Contact c"),
        @NamedQuery(
                name="Contact.countAll",
                query = "select count(c) from Contact c"),
        @NamedQuery(
                name="Contact.findById",
                query = "select distinct c from Contact c " +
                        "left join fetch c.contactTelDetails t " +
                        "left join fetch c.hobbies h " +
                        "where c.id = :id"),
        @NamedQuery(
                name="Contact.findAllWithDetail",
                query = "select distinct c from Contact c " +
                        "left join fetch c.contactTelDetails t " +
                        "left join fetch c.hobbies h")
})
@SqlResultSetMapping(
        name="contactResult",
        entities = @EntityResult(entityClass = Contact.class)
)
public class Contact implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Set<ContactTelDetail> contactTelDetails = new HashSet<>();
    private Set<Hobby> hobbies = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<ContactTelDetail> getContactTelDetails() {
        return contactTelDetails;
    }

    public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public void addContactTelDetail(ContactTelDetail ctd) {
        ctd.setContact(this);
        getContactTelDetails().add(ctd);
    }

    public void removeContactTelDetail(ContactTelDetail ctd) {
        getContactTelDetails().remove(ctd);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
