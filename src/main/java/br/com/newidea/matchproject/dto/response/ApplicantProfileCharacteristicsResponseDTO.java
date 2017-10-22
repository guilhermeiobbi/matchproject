package br.com.newidea.matchproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fabio
 * @since 22/10/17 07:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantProfileCharacteristicsResponseDTO {

    private String name;
    private BigDecimal percent;
    private List<ApplicantCharacteristicMetricsResponseDTO> metrics;
}
