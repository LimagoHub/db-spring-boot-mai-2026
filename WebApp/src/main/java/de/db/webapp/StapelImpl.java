package de.db.webapp;

import de.db.webapp.persistence.repository.PersonenRepository;

public class StapelImpl implements Stapel {

    private PersonenRepository personenRepository;

    public StapelImpl(final PersonenRepository personenRepository) {
        this.personenRepository = personenRepository;
    }

    @Override
    public void push(final Object obj) {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
