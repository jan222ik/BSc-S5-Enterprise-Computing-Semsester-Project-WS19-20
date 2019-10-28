package at.fhv.itb17.s5.teamb.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@SuppressWarnings("WeakerAccess")
public class ArgumentParserTest {

    private static final LinkedList<String> TEST_ARGS;
    private static final String NOT_IN_LIST_ARG;
    private static final LinkedList<String> TEST_ARGS_WITH_VALUES;

    static {
        NOT_IN_LIST_ARG = "a4";
        TEST_ARGS = new LinkedList<>(Arrays.asList("a0", "a1", "a2", "a3"));
        TEST_ARGS_WITH_VALUES = new LinkedList<>(Arrays.asList("a0", "a1=10", "a2=Hello World!"));
    }

    @BeforeAll
    public static void assertConstants() {
        assertThat(TEST_ARGS.contains(NOT_IN_LIST_ARG), is(false));
    }

    @Test
    @DisplayName("Contains - Arg exists")
    public void testContainsArgumentExisting() {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArgs(TEST_ARGS, '=');
        assertThat(argumentParser.containsKeyword(TEST_ARGS.get(0)), is(true));
    }

    @Test
    @DisplayName("Contains - Arg missing")
    public void testContainsArgumentMissing() {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArgs(TEST_ARGS, '=');
        assertThat(argumentParser.containsKeyword(NOT_IN_LIST_ARG), is(false));
    }

    @Test
    @DisplayName("CheckFor - Arg exists")
    public void testCheckArgumentExisting() {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArgs(TEST_ARGS, '=');
        AtomicBoolean executed = new AtomicBoolean(false);
        argumentParser.checkForKeyword(TEST_ARGS.get(1), s -> executed.set(true));
        assertThat(executed.get(), is(true));

        executed.set(false);
        argumentParser.checkForKeyword(TEST_ARGS.get(2), s -> executed.set(true), () -> fail("Must not be executed"));
        assertThat(executed.get(), is(true));
    }

    @Test
    @DisplayName("CheckFor - Arg missing")
    public void testCheckArgumentMissing() {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArgs(TEST_ARGS, '=');
        AtomicBoolean noExecOrorElseExecuted = new AtomicBoolean(true);
        argumentParser.checkForKeyword(NOT_IN_LIST_ARG, s -> noExecOrorElseExecuted.set(false));
        assertThat(noExecOrorElseExecuted.get(), is(true));

        noExecOrorElseExecuted.set(false);
        argumentParser.checkForKeyword(NOT_IN_LIST_ARG, s -> fail("Must not be executed"),
                () -> noExecOrorElseExecuted.set(true));
        assertThat(noExecOrorElseExecuted.get(), is(true));
    }

    @Test
    @DisplayName("GetArgValue - All")
    public void testArgsWithValues() {
        final String defaultS = "default";
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArgs(TEST_ARGS_WITH_VALUES, '=');
        assertThat(argumentParser.containsKeyword(TEST_ARGS_WITH_VALUES.get(0)), is(true));
        assertThat(argumentParser.getArgValue("a1", defaultS), equalTo("10"));
        assertThat(argumentParser.getArgValue("a2", defaultS), equalTo("Hello World!"));
        assertThat(argumentParser.getArgValue("a0", defaultS), equalTo(defaultS));
    }
}
