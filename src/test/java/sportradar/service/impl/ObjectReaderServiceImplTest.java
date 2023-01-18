package sportradar.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {ObjectReaderServiceImpl.class})
class ObjectReaderServiceImplTest {
    @Autowired
    private ObjectReaderServiceImpl objectReaderService;

    @Test
    public void read_Ok() {
        String read = objectReaderService.read();
        Assertions.assertNotNull(read);
        Assertions.assertTrue(read.length() > 1);
    }

}