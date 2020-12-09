package net.hack.services;

import net.hack.doa.SessionDoa;
import net.hack.doa.SessionDoaImpl;
import net.hack.model.Session;

import java.util.List;

public class SessionService {

    private static final SessionService instance = new SessionService();
    private  final SessionDoa sessionDoa = new SessionDoaImpl();

    public static SessionService getInstance(){
        return instance;
    }

    public boolean insertSessions(Session session) {
        return sessionDoa.insertSessions(session);
    }

    public List<Session> selectAllSessions() {
        return sessionDoa.selectAllSessions();
    }

    public Session selectSession(int id) {
        return sessionDoa.selectSession(id);
    }

    public boolean deleteSession(int id) {
        return sessionDoa.deleteSession(id);
    }
}
