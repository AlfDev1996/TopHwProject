package it.unisa.tophw.server.model.connection;

import junit.extensions.PrivilegedAccessor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverManagerConnectionPoolTest {

    public DriverManagerConnectionPoolTest() {
        super();
    }


    @Test
    public void TestCreateConnection() throws SQLException {
        Connection c = DriverManagerConnectionPool.getConnection();
        c.close();
        assertTrue(c.isClosed());
    }

    @Test
    public void testReleaseConnessione() throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
       // Connection connection=(Connection) PrivilegedAccessor.invokeMethod(DriverManagerConnectionPool.class, "createDBConnection()");
       // DriverManagerConnectionPool.releaseConnection((com.mysql.jdbc.Connection) connection);
        assertEquals(1,((List<Connection>)(PrivilegedAccessor.getValue(DriverManagerConnectionPool.class, "freeDbConnections"))).size(),"la connessione creata non e' valida");
    }

    @Test
    public void testReleaseConnessioneNull() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, NoSuchFieldException {
        PrivilegedAccessor.setValue(DriverManagerConnectionPool.class, "freeDbConnections", new LinkedList<Connection>());
        DriverManagerConnectionPool.releaseConnection(null);
        assertEquals(0,((List<Connection>)(PrivilegedAccessor.getValue(DriverManagerConnectionPool.class, "freeDbConnections"))).size(),"Ha accettato una connessione null");
    }

}