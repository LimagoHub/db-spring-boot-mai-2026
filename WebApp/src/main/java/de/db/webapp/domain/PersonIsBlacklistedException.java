package de.db.webapp.domain;

public class PersonIsBlacklistedException extends RuntimeException {

    public PersonIsBlacklistedException() {
    }

    public PersonIsBlacklistedException(final String message) {
        super(message);
    }

    public PersonIsBlacklistedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersonIsBlacklistedException(final Throwable cause) {
        super(cause);
    }

    public PersonIsBlacklistedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
