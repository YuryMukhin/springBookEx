package ch6.dao;

import ch6.entities.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainContactDao implements ContactDao {

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {}
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts?useSSL=false", "root", "root");
    }

    private void closeConnection(Connection connection){
        if (connection == null){
            return;
        }
        try{
            connection.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement("select * from contact");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getLong(1));
                contact.setFirstName(rs.getString(2));
                contact.setLastName(rs.getString("LAST_NAМE"));
                contact.setBirthDate(rs.getDate(4));

                result.add(contact);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(Long contactId) {

    }
}
