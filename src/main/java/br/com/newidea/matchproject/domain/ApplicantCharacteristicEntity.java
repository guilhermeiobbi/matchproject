package br.com.newidea.matchproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author fabio
 * @since 22/10/17 03:22
 */

@Data
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applicantCharacteristic")
@Table(name = "applicant_characteristic_acc")
public class ApplicantCharacteristicEntity {

    @Id
    @Column(name = "acc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_applicant_profile_id", referencedColumnName = "apt_id")
    private ApplicantProfileTypeEntity profile;

    @Column(name = "acc_name", length = 250)
    private String name;

    @Column(name = "acc_percent")
    private BigDecimal percent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "characteristic", orphanRemoval = true)
    private List<ApplicantCharacteristicMetricsEntity> metrics;
}
