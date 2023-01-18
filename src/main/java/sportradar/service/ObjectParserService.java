package sportradar.service;

import sportradar.model.Event;
import sportradar.model.ResponseEventDto;

import java.util.List;

public interface ObjectParserService {

    List<Event> parseToEvent(String input);
}
