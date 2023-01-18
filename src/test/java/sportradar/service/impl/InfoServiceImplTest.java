package sportradar.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sportradar.model.Competitor;
import sportradar.model.Event;
import sportradar.service.ObjectParserService;
import sportradar.service.ObjectReaderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(classes = {InfoServiceImpl.class})
class InfoServiceImplTest {
    @MockBean
    private ObjectReaderServiceImpl objectReaderServiceImpl;
    @MockBean
    private ObjectParserServiceImpl objectParserServiceImpl;
    @Autowired
    private InfoServiceImpl infoServiceImpl;

    @Test
    public void getEvents_And_getTeams_Ok() {
        Competitor competitorHome = Competitor.builder()
                .name("homeTeam").qualifier("home").build();
        Competitor competitorAway = Competitor.builder().
                name("awayTeam").qualifier("away").build();
        List<Event> expected = new ArrayList<>();
        Event event = Event.builder()
                .competitionName("UEFA Champions League")
                .competitors(List.of(competitorHome, competitorAway))
                .build();
        expected.add(event);

        when(objectReaderServiceImpl.read()).thenReturn("");
        when(objectParserServiceImpl.parseToEvent(any())).thenReturn(expected);
        List<Event> actual = infoServiceImpl.getEvents();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(event.getCompetitionName(), actual.get(0).getCompetitionName());
        List<String> teams = infoServiceImpl.getTeams();
        Assertions.assertEquals(2,teams.size());
        verify(objectReaderServiceImpl, times(1)).read();
        verify(objectParserServiceImpl, times(1)).parseToEvent(any());
    }
}