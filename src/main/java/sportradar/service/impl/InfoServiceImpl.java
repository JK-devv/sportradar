package sportradar.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sportradar.model.Competitor;
import sportradar.model.Event;
import sportradar.service.InfoService;
import sportradar.service.ObjectParserService;
import sportradar.service.ObjectReaderService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

    private final List<Event> events;
    private final List<String> teamsName;
    private final ObjectReaderService objectReaderServiceImpl;
    private final ObjectParserService objectParserServiceImpl;


    @Override
    public List<Event> getEvents() {
        if (events.isEmpty()) {
            String readString = objectReaderServiceImpl.read();
            events.addAll(objectParserServiceImpl.parseToEvent(readString));
        }
        return events;
    }

    @Override
    public List<String> getTeams() {
        if (teamsName.isEmpty()) {
            teamsName.addAll(getEvents().stream()
                    .map(Event::getCompetitors)
                    .flatMap(Collection::stream)
                    .map(Competitor::getName)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList()));
        }
        return teamsName;
    }
}
