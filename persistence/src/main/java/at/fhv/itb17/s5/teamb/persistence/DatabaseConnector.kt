package at.fhv.itb17.s5.teamb.persistence

import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

/**
 * Initializes Database Connection.
 */
object DatabaseConnector {
    private val ourSessionFactory: SessionFactory

    /**
     * Returns Hibernate Session.
     *
     * @return Session
     * @throws HibernateException if Session cannot be opened.
     */
    val session: Session
        @Throws(HibernateException::class)
        get() = ourSessionFactory.openSession()

    init {
        try {
            ourSessionFactory = Configuration().configure("hibernate.cfg.xml").buildSessionFactory()
        } catch (ex: Throwable) {
            throw ExceptionInInitializerError(ex)
        }

    }

    fun hasConnection(): Boolean {
        val session = session
        val sessionConnected = session.isConnected
        session.close()
        return sessionConnected
    }

    fun shutdown() {
        ourSessionFactory.close()
    }

    fun init() {

    }
}

