package sportradar.model.mapper;

import org.springframework.stereotype.Component;
import sportradar.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SportRadarMapper {
    public List<ResponseEventDto> mapToResponse(List<Event> events) {
        List<ResponseEventDto> responseEventDtos = new ArrayList<>();
        for (Event event : events) {
            ResponseEventDto responseEventDto = new ResponseEventDto();
            responseEventDto.setStartDate(event.getStartDate());
            responseEventDto.setCompetitionName(event.getCompetitionName());

            ResponseCompetitorDto responseCompetitorDtoAway = new ResponseCompetitorDto();
            ResponseCompetitorDto responseCompetitorDtoHome = new ResponseCompetitorDto();
            List<Competitor> competitors = event.getCompetitors();
            Competitor home = competitors.stream()
                    .filter(competitor -> competitor.getQualifier()
                            .equalsIgnoreCase("home"))
                    .findFirst().orElseThrow();
            Competitor away = competitors.stream()
                    .filter(competitor -> competitor.getQualifier()
                            .equalsIgnoreCase("away"))
                    .findFirst().orElseThrow();
            responseCompetitorDtoAway.setCountry(away.getCountry());
            responseCompetitorDtoAway.setName(away.getName());
            responseCompetitorDtoHome.setCountry(home.getCountry());
            responseCompetitorDtoHome.setName(home.getName());
            responseEventDto.setAwayTeam(responseCompetitorDtoAway);
            responseEventDto.setHomeTeam(responseCompetitorDtoHome);
            responseEventDto.setVenueName(event.getVenue() == null ? null :event.getVenue().getName());

            List<Double> probableResult = new ArrayList<>();
            probableResult.add(event.getProbabilityHomeTeamWinner());
            probableResult.add(event.getProbabilityDraw());
            probableResult.add(event.getProbabilityAwayTeamWinner());
            probableResult.sort(Double::compareTo);
            ResponseProbableResultDto responseProbableResultDto = new ResponseProbableResultDto();
            Double max = probableResult.get(probableResult.size() - 1);
            responseProbableResultDto.setResult(max);
            if (max.equals(event.getProbabilityAwayTeamWinner())) {
                responseProbableResultDto.setTeamName(away.getName());
            }
            if (max.equals(event.getProbabilityHomeTeamWinner())) {
                responseProbableResultDto.setTeamName(home.getName());
            } else {
                responseProbableResultDto.setTeamName("DRAW");
            }
            responseEventDto.setProbableResult(responseProbableResultDto);
            responseEventDtos.add(responseEventDto);
        }
        return responseEventDtos;
    }
}
