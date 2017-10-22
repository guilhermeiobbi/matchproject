package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fabio
 * @since 21/10/17 19:56
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantMetricsRequestDTO {

    @JsonProperty("processed_language")
    @ApiModelProperty(notes = "processed_language", required = false, position = 0)
    private String processedLanguage;

    @JsonProperty("word_count")
    @ApiModelProperty(notes = "word_count", example = "1", required = false, position = 1)
    private int wordCount;

    @JsonProperty("word_count_message")
    @ApiModelProperty(notes = "wordCountMessage", example = "abc", required = false, position = 2)
    private String wordCountMessage;

    @JsonProperty("personality")
    @ApiModelProperty(notes = "personality", required = false, position = 3)
    private List<ApplicantMetricsPersonalityRequestDTO> personality;

    @JsonProperty("values")
    @ApiModelProperty(notes = "values", required = false, position = 4)
    private List<ApplicantMetricsValuesRequestDTO> values;

    @JsonProperty("needs")
    @ApiModelProperty(notes = "needs", required = false, position = 5)
    private List<ApplicantMetricsNeedsRequestDTO> needs;

    @JsonProperty("behavior")
    @ApiModelProperty(notes = "behavior", required = false, position = 6)
    private List<ApplicantMetricsBehaviorRequestDTO> behavior;

    @JsonProperty("consumption_preferences")
    @ApiModelProperty(notes = "consumption_preferences", required = false, position = 7)
    private List<ApplicantMetricsConsPrefRequestADTO> consumptionPreferences;

    @JsonProperty("warnings")
    @ApiModelProperty(notes = "warnings", required = false, position = 8)
    private List<ApplicantMetricsWarningsDTO> warnings;
}
