package com.dappermoose.stsimplefinance.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.FileCopyUtils;

/**
 * This is the class containing the main program for spring boot.
 */
@SpringBootApplication
@EnableRedisHttpSession
@ServletComponentScan
public class Application
{

    /**
     * This is the main program for spring boot.
     *
     * @param args the command line arguments
     *
     */
    public static void main (final String[] args)
    {
        TimeZone.setDefault (TimeZone.getTimeZone ("UTC"));

        System.setProperty ("spring.devtools.restart.enabled", "false");
        System.setProperty ("com.atomikos.icatch.registered", "true");
        ApplicationContext ctx = SpringApplication.run (Application.class, args);
        sendStartupEmail (ctx);
    }

    private static void sendStartupEmail (final ApplicationContext ctx)
    {
        MailSender mailer = ctx.getBean (MailSender.class);
        SimpleMailMessage msg = new SimpleMailMessage ();

        String envProp = System.getenv ("MAIL_TO");
        String recipient;
        recipient = envProp != null ? envProp :
                ctx.getMessage ("mail.to", new Object[] {}, Locale.getDefault ());

        msg.setFrom (ctx.getMessage ("mail.sender", new Object[] {}, Locale.getDefault ()));
        msg.setTo (recipient);
        msg.setSubject (ctx.getMessage ("mail.subject", new Object[] {}, Locale.getDefault ()));
        msg.setText (ctx.getMessage ("mail.upmsg", new Object[] {}, Locale.getDefault ()));
        mailer.send (msg);
    }

    /**
     * This sets up the embedded tomcat server.
     *
     * @return the customized embedded tomcat server
     */
    @Bean
    public TomcatServletWebServerFactory servletWebServer ()
    {
        TomcatServletWebServerFactory tomcat;
        tomcat = new TomcatServletWebServerFactoryImpl ();

        tomcat.addConnectorCustomizers ((TomcatConnectorCustomizer) (final Connector connector) ->
        {
            connector.setRedirectPort (8443);
        });
        tomcat.addAdditionalTomcatConnectors (createSSLConnector ());
        return tomcat;
    }

    private Connector createSSLConnector ()
    {
        Connector connector = new Connector ("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler ();
        try
        {
            File keystore = getKeyStoreFile ();
            connector.setScheme ("https");
            connector.setSecure (true);
            connector.setPort (8443);
            protocol.setSSLEnabled (true);
            protocol.setKeystoreFile (keystore.getAbsolutePath ());
            protocol.setKeystorePass ("changeit");
            protocol.setKeyAlias ("localhost");
            protocol.setCiphers ("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256,TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_256_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA256,SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
            return connector;
        }
        catch (IOException ex)
        {
            throw new IllegalStateException ("cant access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }

    private File getKeyStoreFile () throws IOException
    {
        ClassPathResource resource = new ClassPathResource ("ssl.jks");
        try
        {
            return resource.getFile ();
        }
        catch (Exception ex)
        {
            File temp = File.createTempFile ("keystore", ".tmp");
            FileCopyUtils.copy (resource.getInputStream (), new FileOutputStream (temp));
            return temp;
        }
    }

    private static class TomcatServletWebServerFactoryImpl extends TomcatServletWebServerFactory
    {

        TomcatServletWebServerFactoryImpl ()
        {
        }

        @Override
        protected void postProcessContext (final Context context)
        {
            SecurityConstraint securityConstraint = new SecurityConstraint ();
            securityConstraint.setUserConstraint ("CONFIDENTIAL");
            SecurityCollection collection = new SecurityCollection ();
            collection.addPattern ("/*");
            securityConstraint.addCollection (collection);
            context.addConstraint (securityConstraint);
        }
    }
}
