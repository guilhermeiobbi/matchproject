package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fabio
 * @since 21/10/17 20:08
 */
public class ApplicantMetricsPersonalityRequestDTO {

    @JsonProperty("trait_id")
    @ApiModelProperty(notes = "trait_id", example = "abc", required = true)
    private int traitId;

}
