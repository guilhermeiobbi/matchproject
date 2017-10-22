package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author fabio
 * @since 21/10/17 23:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantMetricsBehaviorRequestDTO {

    @JsonProperty("trait_id")
    @ApiModelProperty(notes = "trait_id", example = "abc", required = false, position = 0)
    private String traitId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "name", example = "abc", required = false, position = 1)
    private String name;

    @JsonProperty("category")
    @ApiModelProperty(notes = "category", example = "abc", required = false, position = 2)
    private String category;

    @JsonProperty("percentile")
    @ApiModelProperty(notes = "percentile", example = "10.00", dataType = "numeric", required = false, position = 3)
    private BigDecimal percentile;
}
