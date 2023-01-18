package sportradar.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sportradar.model.Event;
import sportradar.service.ObjectParserService;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ObjectParserServiceImpl implements ObjectParserService {

    private final ObjectMapper objectMapper;

    @Override
    public List<Event> parseToEvent(String input) {
        try {
            return objectMapper.readValue(input, new TypeReference<List<Event>>() {
            });
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
