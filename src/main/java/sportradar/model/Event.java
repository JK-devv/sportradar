package sportradar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Event {
    @JsonProperty("sport_event_id")
    private String sportEventId;
    @JsonProperty("start_date")
    private OffsetDateTime startDate;
    @JsonProperty("sport_name")
    private String sportName;
    @JsonProperty("competition_name")
    private String competitionName;
    @JsonProperty("competition_id")
    private String competitionId;
    @JsonProperty("season_name")
    private String seasonName;
    private List<Competitor> competitors;
    private Venue venue;
    @JsonProperty("probability_home_team_winner")
    private Double probabilityHomeTeamWinner;
    @JsonProperty("probability_draw")
    private Double probabilityDraw;
    @JsonProperty("probability_away_team_winner")
    private Double probabilityAwayTeamWinner;
}
