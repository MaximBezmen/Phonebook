package type;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SexType {
    @JsonProperty("MALE")
    MALE,
    @JsonProperty("FEMALE")
    FEMALE
}
