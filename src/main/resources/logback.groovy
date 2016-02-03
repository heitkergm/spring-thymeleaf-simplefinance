//
// Built on Tue Nov 18 01:09:09 CET 2014 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.DEBUG

jmxConfigurator()
statusListener(OnConsoleStatusListener)
appender("STDOUT", ConsoleAppender) {
  encoder(LayoutWrappingEncoder) {
    layout(PatternLayout) {
      pattern = "%d{yyyy-MM-dd HH:mm:ss.SSSZ} [%thread] %-5level %logger{36} - %msg%n"
    }
  }
}
root(INFO, ["STDOUT"])