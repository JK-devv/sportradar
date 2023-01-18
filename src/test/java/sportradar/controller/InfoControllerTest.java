package sportradar.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sportradar.model.Competitor;
import sportradar.model.Event;
import sportradar.model.ResponseEventDto;
import sportradar.model.Venue;
import sportradar.model.mapper.SportRadarMapper;
import sportradar.service.InfoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@SpringBootTest(classes = {InfoController.class})
class InfoControllerTest {
    @MockBean
    private InfoService infoServiceImpl;
    @MockBean
    private SportRadarMapper sportRadarMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void getInfo_Ok() {
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
                .probabilityAwayTeamWinner(2.4)
                .probabilityDraw(1.0)
                .probabilityHomeTeamWinner(90.1)
                .sportName("testSportName")
                .startDate(OffsetDateTime.now())
                .venue(new Venue())
                .build();
        mockEvents.add(event);
        when(infoServiceImpl.getEvents()).thenReturn(mockEvents);
        when(sportRadarMapper.mapToResponse(mockEvents)).thenCallRealMethod();
        MvcResult mvcResult = mockMvc.perform(
                get("/info/winner").contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
        verify(infoServiceImpl, times(1)).getEvents();
        verify(sportRadarMapper, times(1)).mapToResponse(mockEvents);
    }

    @Test
    @SneakyThrows
    public void getInfo_WhenCountGreaterThenResponseSize_Ok() {
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
                .probabilityAwayTeamWinner(2.4)
                .probabilityDraw(1.0)
                .probabilityHomeTeamWinner(90.1)
                .sportName("testSportName")
                .startDate(OffsetDateTime.now())
                .venue(new Venue())
                .build();
        mockEvents.add(event);
        when(infoServiceImpl.getEvents()).thenReturn(mockEvents);
        when(sportRadarMapper.mapToResponse(mockEvents)).thenCallRealMethod();
        MvcResult mvcResult = mockMvc.perform(
                get("/info/winner?count=0").contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
        verify(infoServiceImpl, times(1)).getEvents();
        verify(sportRadarMapper, times(1)).mapToResponse(mockEvents);
    }


    @Test
    @SneakyThrows
    public void getTeams_Ok() {
        List<String> teamsNameList = List.of("AC Omonia Nicosia", "AS Monaco", "Benfica Lisbon", "Bodoe/Glimt", "CS Fola Esch", "Celtic Glasgow", "Connah`s Quay Nomads FC", "FC Alashkert Yerevan", "FC CFR 1907 Cluj", "FC Dinamo Tbilisi", "FC Kairat Almaty", "FC Midtjylland", "FC Prishtina", "FC Shakhtar Donetsk", "FC Shakhter Soligorsk", "FC Sheriff Tiraspol", "FK Borac Banja Luka", "FK Buducnost", "FK Crvena Zvezda Belgrade", "FK Spartak Moscow", "Ferencvarosi Budapest", "Flora Tallinn", "GNK Dinamo Zagreb", "Galatasaray Istanbul", "Glasgow Rangers", "HB Torshavn", "HJK Helsinki", "Hibernians FC Paola", "Inter Club de Escaldes", "KF Teuta", "KRC Genk", "Legia Warszawa", "Lincoln Red Imps", "Linfield FC", "Maccabi Haifa FC", "Malmo FF", "Mura Murska Sobota", "Neftchi Baku PFC", "Olympiacos Piraeus", "PFC Ludogorets 1945 Razgrad", "PSV Eindhoven", "Riga FC", "SK Rapid Wien", "SS Folgore Falciano Calcio", "Shamrock Rovers", "Shkendija Tetovo", "Slavia Prague", "Slovan Bratislava", "Sparta Prague", "Valur Reykjavik", "Vilnius FK Zalgiris", "Young Boys Bern");;
        when(infoServiceImpl.getTeams()).thenReturn(teamsNameList);
        MvcResult mvcResult = mockMvc.perform(
                get("/info/teams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
        verify(infoServiceImpl, times(1)).getTeams();
    }
}