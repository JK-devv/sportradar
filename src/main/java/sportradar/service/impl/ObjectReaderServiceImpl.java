package sportradar.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sportradar.service.ObjectReaderService;

import java.io.*;

@Service
@Slf4j
public class ObjectReaderServiceImpl implements ObjectReaderService {
    private static final String PATH = "src/main/resources/input.json";

    @Override
    public String read() {
        StringBuilder resultStringBuilder = new StringBuilder();
        File file = new File(PATH);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line.trim());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return resultStringBuilder.toString();
    }
}
