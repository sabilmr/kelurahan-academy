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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_dusun")
public class DusunEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name")
    private String name;
    //@Column Annotation ini digunakan untuk menentukan detail kolom pada basis data yang sesuai dengan properti di entitas (kelas Java).
    //name = "kelurahan_id" Menentukan nama kolom di tabel basis data yang dihubungkan dengan atribut kelurahanId.
    //insertable = false: Menentukan bahwa nilai kolom ini tidak boleh diinsert (dimodifikasi) saat melakukan operasi INSERT di basis data.
    //updatable = false: Menentukan bahwa nilai kolom ini tidak boleh diperbarui saat melakukan operasi UPDATE di basis data
    @Column(name = "kelurahan_id", length = 36, insertable = false, updatable = false)
    private String kelurahanId;
    //@ManyToOne: Annotation ini menunjukkan bahwa ada hubungan banyak-ke-satu (many-to-one) antara entitas ini
    //(entitas yang mendefinisikan kode ini) dengan entitas KelurahanEntity.
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn: Annotation ini digunakan untuk menentukan kolom yang akan digunakan sebagai join key dalam hubungan antar entitas
    @JoinColumn(name = "kelurahan_id")
    private KelurahanEntity kelurahan;

    @OneToMany(mappedBy = "dusun", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RwEntity> rwEntities = new ArrayList<>();

    public DusunEntity(String id, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public void addRW(RwEntity rwEntity) {
        rwEntities.add(rwEntity);
        rwEntity.setDusun(this);
    }
}
