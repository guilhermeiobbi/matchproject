package br.com.newidea.matchproject.translator;

import br.com.newidea.matchproject.domain.ApplicantCharacteristicEntity;
import br.com.newidea.matchproject.domain.ApplicantCharacteristicMetricsEntity;
import br.com.newidea.matchproject.domain.ApplicantEntity;
import br.com.newidea.matchproject.domain.ApplicantProfileTypeEntity;
import br.com.newidea.matchproject.dto.response.ApplicantCharacteristicMetricsResponseDTO;
import br.com.newidea.matchproject.dto.response.ApplicantProfileCharacteristicsResponseDTO;
import br.com.newidea.matchproject.dto.response.ApplicantProfileTypeResponseDTO;
import br.com.newidea.matchproject.dto.response.ApplicantRankingResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fabio
 * @since 21/10/17 19:53
 */

@Component
public class ApplicantTranslator {

    public List<ApplicantRankingResponseDTO> toRankingDTO(List<ApplicantEntity> entityList) {

        List<ApplicantRankingResponseDTO> dtoList = new ArrayList<ApplicantRankingResponseDTO>();


        entityList.forEach(applicantEntity ->

                dtoList.add(

                        ApplicantRankingResponseDTO.builder()
                                .applicandId(applicantEntity.getId())
                                .applicantName(applicantEntity.getName())
                                .percent(new BigDecimal(applicantEntity.getPercent().toString()))
                                .profiles(toProfileTypeDTO(applicantEntity.getProfiles()))
                                .build()
                )
        );

        return dtoList;
    }

    private List<ApplicantProfileTypeResponseDTO> toProfileTypeDTO(List<ApplicantProfileTypeEntity> profiles) {

        List<ApplicantProfileTypeResponseDTO> dtoList = new ArrayList<ApplicantProfileTypeResponseDTO>();

        profiles.forEach(applicantProfileTypeEntity ->
                dtoList.add(
                        ApplicantProfileTypeResponseDTO.builder()
                                .name(applicantProfileTypeEntity.getName())
                                .percent(new BigDecimal(applicantProfileTypeEntity.getPercent().toString()))
                                .characteristics(toProfileCharacteristicsDTO(applicantProfileTypeEntity.getCharacteristics()))
                                .build()
                )
        );

        return dtoList;
    }

    private List<ApplicantProfileCharacteristicsResponseDTO> toProfileCharacteristicsDTO(List<ApplicantCharacteristicEntity> characteristics) {

        List<ApplicantProfileCharacteristicsResponseDTO> dtoList = new ArrayList<ApplicantProfileCharacteristicsResponseDTO>();

        characteristics.forEach(applicantCharacteristicEntity ->
                dtoList.add(
                        ApplicantProfileCharacteristicsResponseDTO.builder()
                                .name(applicantCharacteristicEntity.getName())
                                .percent(new BigDecimal(applicantCharacteristicEntity.getPercent().toString()))
                                .metrics(toCharacteristicsMetricsDTO(applicantCharacteristicEntity.getMetrics()))
                                .build()
                )
        );

        return dtoList;
    }

    private List<ApplicantCharacteristicMetricsResponseDTO> toCharacteristicsMetricsDTO(List<ApplicantCharacteristicMetricsEntity> metrics) {

        List<ApplicantCharacteristicMetricsResponseDTO> dtoList = new ArrayList<ApplicantCharacteristicMetricsResponseDTO>();

        metrics.forEach(characteristicMetricsEntity ->
                dtoList.add(
                        ApplicantCharacteristicMetricsResponseDTO.builder()
                                .name(characteristicMetricsEntity.getName())
                                .percent(new BigDecimal(characteristicMetricsEntity.getPercent().toString()))
                                .build()
                )
        );

        return dtoList;
    }
}
