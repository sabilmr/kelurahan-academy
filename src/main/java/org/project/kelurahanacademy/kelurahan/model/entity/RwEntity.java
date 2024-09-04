package org.project.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_rw")
public class RwEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_rw")
    private String nameRW;

    @Column(name = "dusun_id", length = 36, insertable = false, updatable = false)
    private String dusunId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dusun_id")
    private DusunEntity dusun;
}
