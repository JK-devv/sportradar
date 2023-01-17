package sportradar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competitor {
    private String id;
    private String name;
    private String country;
    @JsonProperty("country_code")
    private String countryCode;
    private String abbreviation;
    private String qualifier;
    private String gender;
}
