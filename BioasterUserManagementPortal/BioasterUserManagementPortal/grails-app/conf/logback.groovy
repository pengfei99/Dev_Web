import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.util.FileSize

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def HOME_DIR = "/var/log/bump"

appender("ROLLING", RollingFileAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${HOME_DIR}/logs/myApp-%d{yyyy-MM-dd_HH-mm}.log"
        maxHistory = 30
        totalSizeCap = FileSize.valueOf("2GB")
    }
}

//def targetDir = BuildSettings.TARGET_DIR
def targetDir = "/var/log/bump/accessLog"
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                    '%clr(%5p) ' + // Log level
                    '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                    '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                    '%m%n%wex' // Message
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
    root(INFO, ['STDOUT', 'FULL_STACKTRACE'])
}
else {
    root(ERROR, ['ROLLING'])
}
