package type;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FamilyStatusType {
    @JsonProperty("SINGLE")
    SINGLE,
    @JsonProperty("MARRIED")
    MARRIED,
    @JsonProperty("DIVORCED")
    DIVORCED
}
