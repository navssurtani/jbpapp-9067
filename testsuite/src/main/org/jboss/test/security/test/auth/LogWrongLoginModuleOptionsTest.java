package org.jboss.test.security.test.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.spi.LoginModule;

import junit.framework.TestCase;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.jboss.security.ClientLoginModule;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.BaseCertLoginModule;
import org.jboss.security.auth.spi.DatabaseServerLoginModule;
import org.jboss.security.auth.spi.IdentityLoginModule;
import org.jboss.security.auth.spi.LdapExtLoginModule;
import org.jboss.security.auth.spi.LdapLoginModule;
import org.jboss.security.auth.spi.RunAsLoginModule;
import org.jboss.security.auth.spi.UsersRolesLoginModule;

/**
 * A LogWrongLoginModuleOptionsTest for JBPAPP-7794.
 * 
 * @author <a href="olukas@redhat.com">Ondrej Lukas</a>
 */
public class LogWrongLoginModuleOptionsTest extends TestCase
{

   private LogWarnAppender appender = new LogWarnAppender();

   Logger logger;

   /**
    * Set up method
    */
   @Override
   public void setUp()
   {
      logger = Logger.getRootLogger();
      logger.addAppender(appender);
   }

   /**
    * Tear down method
    */
   @Override
   public void tearDown()
   {
      logger.removeAppender(appender);
   }

   /**
    * Test that LdapLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testLdapLoginModuleNotWrongWarnLogging()
   {
      LdapLoginModule lm = new LdapLoginModule();
      String[] strOptions =
      {"java.naming.factory.initial", "java.naming.provider.url", "java.naming.security.authentication",
            "java.naming.security.protocol", "java.naming.security.principal", "java.naming.security.credentials",
            "principalDNPrefix", "principalDNSuffix", "useObjectCredential", "rolesCtxDN",
            "userRolesCtxDNAttributeName", "roleAttributeID", "roleAttributeIsDN", "roleNameAttributeID",
            "uidAttributeID", "matchOnUserDN", "unauthenticatedIdentity", "allowEmptyPasswords", "password-stacking",
            "hashAlgorithm", "hashEncoding", "hashCharset", "hashUserPassword", "hashStorePassword",
            "unauthenticatedIdentity"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that LdapExtLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testLdapExtLoginModuleNotWrongWarnLogging()
   {
      LdapExtLoginModule lm = new LdapExtLoginModule();
      String[] strOptions =
      {"baseCtxDN", "bindDN", "bindCredential", "jaasSecurityDomain", "baseFilter", "rolesCtxDN", "roleFilter",
            "roleAttributeIsDN", "roleAttributeID", "roleNameAttributeID", "roleRecursion", "searchTimeLimit",
            "searchScope", "allowEmptyPasswords", "defaultRole", "parseRoleNameFromDN", "parseUsername",
            "usernameBeginString", "usernameEndString", "password-stacking", "java.naming.factory.initial",
            "java.naming.security.protocol", "java.naming.provider.url", "java.naming.security.authentication",
            "java.naming.referral", "hashAlgorithm", "hashEncoding", "hashCharset", "hashUserPassword",
            "hashStorePassword", "unauthenticatedIdentity"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that UsersRolesLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testUsersRolesLoginModuleNotWrongWarnLogging()
   {
      UsersRolesLoginModule lm = new UsersRolesLoginModule();
      String[] strOptions =
      {"usersProperties", "rolesProperties", "hashAlgorithm", "hashEncoding", "hashCharset", "hashUserPassword",
            "hashStorePassword", "unauthenticatedIdentity"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that DatabaseServerLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testDatabaseServerLoginModuleNotWrongWarnLogging()
   {
      DatabaseServerLoginModule lm = new DatabaseServerLoginModule();
      String[] strOptions =
      {"dsJndiName", "principalsQuery", "rolesQuery", "ignorePasswordCase", "password-stacking", "hashAlgorithm",
            "hashEncoding", "hashCharset", "hashUserPassword", "hashStorePassword", "unauthenticatedIdentity"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that BaseCertLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testBaseCertLoginModuleNotWrongWarnLogging()
   {
      BaseCertLoginModule lm = new BaseCertLoginModule();
      String[] strOptions =
      {"securityDomain", "password-stacking"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that IdentityLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testIdentityLoginModuleNotWrongWarnLogging()
   {
      IdentityLoginModule lm = new IdentityLoginModule();
      String[] strOptions =
      {"principal", "roles", "password-stacking"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that RunAsLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testRunAsLoginModuleNotWrongWarnLogging()
   {
      RunAsLoginModule lm = new RunAsLoginModule();
      String[] strOptions =
      {"roleName"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that ClientLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testClientLoginModuleNotWrongWarnLogging()
   {
      ClientLoginModule lm = new ClientLoginModule();
      String[] strOptions =
      {"multi-threaded", "password-stacking", "restore-login-identity"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that RoleMappingLoginModule logs invalid property name as WARN and doesn't log correct property name as invalid
    */
   public void testRoleMappingLoginModuleNotWrongWarnLogging()
   {
      RoleMappingLoginModule lm = new RoleMappingLoginModule();
      String[] strOptions =
      {"rolesProperties", "replaceRole"};
      createOptions(lm, strOptions);
   }

   /**
    * Test that Login Modules log invalid property name as WARN and don't log correct property name as invalid
    */
   private void createOptions(LoginModule lm, String[] options)
   {
      Map<String, String> mapOptions = new HashMap<String, String>();
      for (int i = 0; i < options.length; i++)
      {
         mapOptions.put(options[i], "xxx");
      }
      mapOptions.put("principalClass", SimplePrincipal.class.getName());
      mapOptions.put("wrongPropertyName", "xxx");
      lm.initialize(new Subject(), new MyCallbackHandler(), new HashMap<String, String>(), mapOptions);
      boolean wrongProperty = false;
      List<String> list = new ArrayList<String>();
      Iterator it = appender.getLogs().iterator();
      while (it.hasNext())
      {
         LoggingEvent e = (LoggingEvent) it.next();
         for (int i = 0; i < options.length; i++)
         {
            if (e.getMessage().toString().contains(options[i]))
            {
               list.add(options[i]);
            }
         }
         wrongProperty = wrongProperty || e.getMessage().toString().contains("wrongPropertyName");
      }

      // *** READ THIS ***
      // This part have to removed when JBPAPP-9730 and JBPAPP-9731 will be resolved.
      String TemporaryWrongReport = "";
      if ((lm.getClass().equals(LdapLoginModule.class) && list.contains("useObjectCredential"))
            || (lm.getClass().equals(LdapExtLoginModule.class) && ((list.contains("java.naming.referral")) || list
                  .contains("roleAttributeIsDN"))))
      {
         TemporaryWrongReport = " , see JBPAPP-9730 for more information! https://issues.jboss.org/browse/JBPAPP-9730";
      }
      if (lm.getClass().equals(ClientLoginModule.class) || lm.getClass().equals(RoleMappingLoginModule.class))
      {
         TemporaryWrongReport = ", see JBPAPP-9731 for more information! https://issues.jboss.org/browse/JBPAPP-9731";
      }
      // *** READ THIS ***
      // End of part to remove (plus remove TemporaryWrongReport in next two lines.

      assertTrue("Correct options were reported as invalid - " + list + TemporaryWrongReport, list.isEmpty());
      assertTrue("Wrong property name was not reported" + TemporaryWrongReport, wrongProperty);

   }

   /**
    * A LogWarnAppender.
    * 
    * @author <a href="olukas@redhat.com">Ondrej Lukas</a>
    */
   private class LogWarnAppender extends AppenderSkeleton
   {

      private List<LoggingEvent> logs = new ArrayList<LoggingEvent>();

      @Override
      public void close()
      {
      }

      @Override
      public boolean requiresLayout()
      {
         return false;
      }

      @Override
      protected void append(LoggingEvent event)
      {
         if (event.getLevel().equals(Level.WARN))
         {
            logs.add(event);
         }
      }

      public List<LoggingEvent> getLogs()
      {
         return logs;
      }

   }

   private class MyCallbackHandler implements CallbackHandler
   {

      @Override
      public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
      {
      }

   }
}
