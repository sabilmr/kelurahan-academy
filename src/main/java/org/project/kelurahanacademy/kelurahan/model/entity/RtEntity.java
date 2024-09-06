package org.project.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_rt")
public class RtEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_rt")
    private String nameRT;

    @Column(name = "rw_id", length = 36, insertable = false, updatable = false)
    private String rwId;

    @ToString.Exclude
    @JoinColumn(name = "rw_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private RwEntity rw;

    public RtEntity(String id, String name, String nameRT) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.nameRT = nameRT;
    }
}
