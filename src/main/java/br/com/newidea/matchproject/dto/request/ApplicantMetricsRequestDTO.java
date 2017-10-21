package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author fabio
 * @since 21/10/17 19:56
 */

@Data
@Builder
public class ApplicantMetricsRequestDTO {


    @JsonProperty("processed_language")
    @ApiModelProperty(notes = "processed_language", example = "", required = true)
    private String processedLanguage;

    @JsonProperty("word_count")
    @ApiModelProperty(notes = "word_count", example = "1", required = true)
    private int wordCount;

    @JsonProperty("word_count_message")
    @ApiModelProperty(notes = "wordCountMessage", example = "1", required = true)
    private int wordCountMessage;

    @JsonProperty("personality")
    @ApiModelProperty(notes = "personality", example = "1", required = true)
    private List<ApplicantMetricsPersonalityRequestDTO> personality;
}
