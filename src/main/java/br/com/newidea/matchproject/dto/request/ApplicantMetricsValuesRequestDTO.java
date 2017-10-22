package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fabio
 * @since 21/10/17 22:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantMetricsValuesRequestDTO {

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

    @JsonProperty("raw_score")
    @ApiModelProperty(notes = "raw_score", example = "10.00", dataType = "numeric", required = false, position = 4)
    private BigDecimal rawScore;

    @JsonProperty("significant")
    @ApiModelProperty(notes = "significant", example = "true", dataType = "boolean", required = false, position = 5)
    private boolean significant;

    @JsonProperty("children")
    @ApiModelProperty(notes = "children", required = false, position = 6)
    private List<ApplicantMetricsValuesRequestDTO> children;
}
