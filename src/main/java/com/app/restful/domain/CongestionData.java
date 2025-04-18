package com.app.restful.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CongestionData {
    @JsonProperty("방향")
    private String direction;

    @JsonProperty("요일")
    private String day;

    @JsonProperty("호선")
    private String line;

    @JsonProperty("역번호")
    private String stationNumber;

    @JsonProperty("역명")
    private String stationName;

    @JsonProperty("7:00~ (%)")
    private String time0700;

    @JsonProperty("7:30~ (%)")
    private String time0730;

    @JsonProperty("8:00~ (%)")
    private String time0000;

    @JsonProperty("8:30~ (%)")
    private String time0830;

    @JsonProperty("9:00~ (%)")
    private String time0900;

}
