package br.com.newidea.matchproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author fabio
 * @since 22/10/17 00:03
 */

@Data
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applicantMetrics")
@Table(name = "applicant_profile_metrics_apm")
public class ApplicantProfileMetricsEntity {

    @Id
    @Column(name = "apm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apm_applicant_profile_id", referencedColumnName = "apt_id")
    private ApplicantProfileType profiles;

    @Column(name = "apm_name", length = 250)
    private String name;

    @Column(name = "apm_porcent")
    private BigDecimal porcent;
}
