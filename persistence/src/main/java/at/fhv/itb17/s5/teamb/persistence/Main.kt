package at.fhv.itb17.s5.teamb.persistence

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class Main {

    private val logger : Logger = LogManager.getLogger(Main::class.java)
    fun main(args: List<String>) {
        logger.info("Hello World! (Passed List: $args)")
    }
}
