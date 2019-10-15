package at.fhv.itb17.s5.teamb.util

import java.util.*
import java.util.function.Consumer


class ArgumentParser {

    private var parsedArgs = HashSet<String>()

    /**
     * Accept consumer when argument is known.
     *
     * @param argKeyword keyword to search in arguments for.
     * @param onFound to accept on occurrence.
     */
    fun checkForKeyword(argKeyword: String, onFound: Consumer<String>) {
        if (parsedArgs.contains(argKeyword)) {
            onFound.accept(argKeyword)
        }
    }

    /**
     * Accept consumer when argument is known.
     *
     * @param argKeyword keyword to search in arguments for.
     * @param onFound to accept on occurrence.
     * @param orElse to execute if missing.
     */
    fun checkForKeyword(argKeyword: String, onFound: Consumer<String>, orElse: Runnable) {
        if (parsedArgs.contains(argKeyword)) {
            onFound.accept(argKeyword)
        } else {
            orElse.run()
        }
    }


    /**
     * Checks if argument is known to parser.
     * @param argKeyword keyword to search in arguments for.
     * @return true, if parser contains argument.
     */
    fun containsKeyword(argKeyword: String): Boolean {
        return parsedArgs.contains(argKeyword)
    }

    /**
     * Pareses all arguments and stores them.
     *
     * @param args to parse.
     */
    fun parseArgs(args: List<String>) {
        parsedArgs.addAll(args)
    }

}

