package com.dappermoose.stsimplefinance.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.FileCopyUtils;

/**
 * This is the class containing the main program for spring boot.
 */
@SpringBootApplication (exclude = {ThymeleafAutoConfiguration.class})
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

        ApplicationContext ctx = SpringApplication.run (Application.class, args);

        System.out.println ("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames ();
        Arrays.sort (beanNames);
        for (String beanName : beanNames)
        {
            System.out.println (beanName);
        }
    }

    /**
     * This sets up the embedded tomcat server.
     *
     * @return the customized embedded tomcat server
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer ()
    {
        TomcatEmbeddedServletContainerFactory tomcat;
        tomcat = new TomcatEmbeddedServletContainerFactory ()
        {
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
        };

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
}
