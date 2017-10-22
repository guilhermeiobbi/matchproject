package br.com.newidea.matchproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fabio
 * @since 22/10/17 00:17
 */

@Data
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applicantProfile")
@Table(name = "applicant_profile_apt")
public class ApplicantProfileType {

    @Id
    @Column(name = "apt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apt_applicant_id", referencedColumnName = "app_id")
    private ApplicantEntity applicant;

    @Column(name = "apt_name", length = 250)
    private String name;

    @Column(name = "apt_porcent")
    private BigDecimal porcent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profiles", orphanRemoval = true)
    private List<ApplicantProfileMetricsEntity> metrics;
}
