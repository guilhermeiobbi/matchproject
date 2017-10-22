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
 * @since 21/10/17 23:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantMetricsConsPrefRequestADTO {

    @JsonProperty("consumption_preference_category_id")
    @ApiModelProperty(notes = "consumption_preference_category_id", example = "abc", required = false, position = 0)
    private String consumptionPreferenceCategoryId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "name", example = "abc", required = false, position = 1)
    private String name;

    @JsonProperty("consumption_preferences")
    @ApiModelProperty(notes = "consumption_preferences", required = false, position = 2)
    private List<ApplicantMetricsConsPrefRequestBDTO> consumptionPreferences;
}
