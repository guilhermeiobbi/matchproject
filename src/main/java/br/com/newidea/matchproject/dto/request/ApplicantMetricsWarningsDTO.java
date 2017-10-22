package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fabio
 * @since 21/10/17 23:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantMetricsWarningsDTO {

    @JsonProperty("warning_id")
    @ApiModelProperty(notes = "warning_id", example = "abc", required = false, position = 0)
    private String warningId;

    @JsonProperty("message")
    @ApiModelProperty(notes = "message", example = "abc", required = false, position = 1)
    private String message;
}
