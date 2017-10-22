package br.com.newidea.matchproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author fabio
 * @since 22/10/17 00:00
 */

@Data
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applicant")
@Table(name = "applicant_app")
public class ApplicantEntity {

    @Id
    @Column(name = "app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_name", length = 250)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicant", orphanRemoval = true)
    private List<ApplicantProfileType> profiles;
}
