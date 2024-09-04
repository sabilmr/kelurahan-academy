package org.project.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_kelurahan")
public class KelurahanEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "alamat")
    private String alamat;

    //@OneToMany: Annotation ini menunjukkan bahwa ada hubungan satu-ke-banyak (one-to-many) antara entitas ini dan entitas DusunEntity.
    //mappedBy = "kelurahan": Properti ini mengindikasikan bahwa hubungan ini dipetakan oleh atribut kelurahan di dalam kelas DusunEntity.
    //cascade = CascadeType.ALL: Menentukan bahwa semua operasi yang dilakukan pada entitas ini
    // (seperti persist, merge, remove, dll.) juga akan dilakukan pada semua entitas DusunEntity yang terkait.
    //orphanRemoval = true: Menyatakan bahwa jika sebuah entitas DusunEntity dihapus dari daftar daftarDusun
    //dengan cara menghapusnya dari list), entitas tersebut akan secara otomatis dihapus dari basis data.
    @OneToMany(mappedBy = "kelurahan", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DusunEntity> daftarDusun = new ArrayList<>();


    public KelurahanEntity(String id, String nama, String alamat) {
        this.id = UUID.randomUUID().toString();
        this.nama = nama;
        this.alamat = alamat;
    }

    public void addDusun(DusunEntity dusun) {
        this.daftarDusun.add(dusun);
        dusun.setKelurahan(this);
    }
}
