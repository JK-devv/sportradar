package sportradar.model.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sportradar.model.Competitor;
import sportradar.model.Event;
import sportradar.model.ResponseEventDto;
import sportradar.model.Venue;
import sportradar.service.impl.InfoServiceImpl;

import javax.swing.text.html.parser.Entity;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {SportRadarMapper.class})
class SportRadarMapperTest {
    @Autowired
    private SportRadarMapper sportRadarMapper;

    @Test
    public void mapToResponse_Ok() {
        List<Event> mockEvents = new ArrayList<>();
        Competitor competitorHome = Competitor.builder()
                .name("homeTeam").qualifier("home").build();
        Competitor competitorAway = Competitor.builder().
                name("awayTeam").qualifier("away").build();
        Event event = Event.builder()
                .competitionName("testName")
                .sportEventId("1234")
                .seasonName("testSesson")
                .competitors(List.of(competitorHome, competitorAway))
                .competitionId("567")
                .probabilityAwayTeamWinner(4.0)
                .probabilityDraw(90.0)
                .probabilityHomeTeamWinner(4.0)
                .sportName("testSportName")
                .startDate(OffsetDateTime.now())
                .venue(new Venue())
                .build();
        mockEvents.add(event);

        List<ResponseEventDto> responseEventDtos = sportRadarMapper.mapToResponse(mockEvents);
        Assertions.assertNotNull(responseEventDtos);
        Assertions.assertEquals(mockEvents.size(), responseEventDtos.size());
        Assertions.assertEquals("testName", responseEventDtos.get(0).getCompetitionName());
        Assertions.assertEquals("awayTeam", responseEventDtos.get(0).getAwayTeam().getName());
        Assertions.assertEquals("homeTeam", responseEventDtos.get(0).getHomeTeam().getName());
    }
}
