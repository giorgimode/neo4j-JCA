package demo.org.connectorz.files.store;

import demo.org.connectorz.files.Neo4JConnectionFactory;
import demo.org.connectorz.files.Neo4jConnection;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import java.util.logging.Logger;

/**
 * Neo4JConnectionFactoryImpl
 *
 * @version $Revision: $
 */
public class Neo4JConnectionFactoryImpl implements Neo4JConnectionFactory
{
   /** The serial version UID */
   private static final long serialVersionUID = 1L;

   /** The logger */
   private static Logger log = Logger.getLogger("Neo4JConnectionFactoryImpl");

   /** Reference */
   private Reference reference;

   /** ManagedConnectionFactory */
   private Neo4jManagedConnectionFactory mcf;

   /** ConnectionManager */
   private ConnectionManager connectionManager;

    /**
    * Default constructor
    * @param mcf ManagedConnectionFactory
    * @param cxManager ConnectionManager
    */
   public Neo4JConnectionFactoryImpl(Neo4jManagedConnectionFactory mcf, ConnectionManager cxManager)
   {
      this.mcf = mcf;
      this.connectionManager = cxManager;
   }

   /** 
    * Get connection from factory
    *
    * @return Neo4JConnection instance
    * @exception ResourceException Thrown if a connection can't be obtained
    */
   public Neo4jConnection getConnection() throws ResourceException
   {
      log.info("getConnection()");
      return (Neo4jConnection)connectionManager.allocateConnection(mcf, getConnectionRequestInfo());
   }

   /**
    * Get the Reference instance.
    *
    * @return Reference instance
    * @exception NamingException Thrown if a reference can't be obtained
    */
   public Reference getReference() throws NamingException
   {
      log.info("getReference()");
      return reference;
   }

   /**
    * Set the Reference instance.
    *
    * @param reference A Reference instance
    */
   public void setReference(Reference reference)
   {
      log.info("setReference()");
      this.reference = reference;
   }

   private ConnectionRequestInfo getConnectionRequestInfo() {
      return new ConnectionRequestInfo() {

         @Override
         public boolean equals(Object obj) {
            return true;
         }

         @Override
         public int hashCode() {
            return 1;
         }
      };
   }


}
