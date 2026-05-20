package de.db.webapp.domain;

import de.db.webapp.domain.model.Person;

public interface BlacklistService {

    boolean isBlacklisted(Person possibleBlacklistedPerson);
}
