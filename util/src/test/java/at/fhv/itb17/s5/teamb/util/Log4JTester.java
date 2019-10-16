package at.fhv.itb17.s5.teamb.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("WeakerAccess")
public class Log4JTester {

    private static final Logger logger = LogManager.getLogger(Log4JTester.class);

    @Test
    public void testLogger() {
        logger.trace("Entering application.");
        doSth();
        logger.trace("Exiting application.");
        logger.info("Info Log");
        assertThat(true, Matchers.is(true));
    }

    public void doSth() {
    }
}
