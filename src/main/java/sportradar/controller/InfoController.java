package sportradar.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportradar.model.ResponseEventDto;
import sportradar.model.mapper.SportRadarMapper;
import sportradar.service.InfoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoServiceImpl;
    private final SportRadarMapper sportRadarMapper;

    @GetMapping("/winner")
    @ApiOperation(value = "Get info about winner with additional information")
    public ResponseEntity<List<ResponseEventDto>> getInfo(@RequestParam(defaultValue = "10")
                                                          @ApiParam(value = " default value is 10") Integer count) {
        List<ResponseEventDto> responseEventDtos = sportRadarMapper.mapToResponse(infoServiceImpl.getEvents());
        if (count > responseEventDtos.size()) {
            return ResponseEntity.ok(responseEventDtos);
        }
        return ResponseEntity.ok(responseEventDtos.subList(0, count));
    }


    @GetMapping("/teams")
    @ApiOperation(value = "get all teams by alphabet")
    public ResponseEntity<List<String>> getTeams() {
        return ResponseEntity.ok(infoServiceImpl.getTeams());
    }
}

