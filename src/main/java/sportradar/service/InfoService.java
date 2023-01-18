package sportradar.service;

import sportradar.model.Event;

import java.util.List;

public interface InfoService {

    List<Event> getEvents();

    List<String> getTeams();
}
