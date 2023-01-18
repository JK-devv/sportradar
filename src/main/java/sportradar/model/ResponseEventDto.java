package sportradar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEventDto {
    private OffsetDateTime startDate;
    private String competitionName;
    private ResponseCompetitorDto awayTeam;
    private ResponseCompetitorDto homeTeam;
    private String venueName;
    private ResponseProbableResultDto probableResult;
}
