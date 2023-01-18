package sportradar.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sportradar.model.Event;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(classes = {ObjectParserServiceImpl.class})
class ObjectParserServiceImplTest {

    @MockBean
    private ObjectMapper objectMapper;
    @Autowired
    private ObjectParserServiceImpl objectParserService;

    @Test
    @SneakyThrows
    public void parseToEvent_Ok() {
        String value = "test";
        List<Event> events = new ArrayList<>();
        Event event = Event.builder().build();
        event.setCompetitionName("UEFA Champions League");
        events.add(event);
        when(objectMapper.readValue(eq(value), any(TypeReference.class))).thenReturn(events);

        List<Event> responseEv = objectParserService.parseToEvent(value);
        Assertions.assertEquals("UEFA Champions League", responseEv.get(0).getCompetitionName());
        verify(objectMapper, times(1)).readValue(eq(value), any(TypeReference.class));
    }

    @Test
    @SneakyThrows
    public void parseToEvent_WithNullInputValue_NotOk() {
        String value = null;
        when(objectMapper.readValue(eq(value), any(TypeReference.class))).thenReturn(null);

        Assertions.assertNull(objectParserService.parseToEvent(value));
        verify(objectMapper, times(1)).readValue(eq(value), any(TypeReference.class));
    }
}