package org.project.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dusun_id")
    private DusunEntity dusun;

    @OneToMany(mappedBy = "rw", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RtEntity> rtEntities = new ArrayList<>();

    public RwEntity(String id, String name, String nameRW) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.nameRW = nameRW;
    }

    public void addRT(RtEntity rtEntity) {
        rtEntities.add(rtEntity);
        rtEntity.setRw(this);
    }
}
