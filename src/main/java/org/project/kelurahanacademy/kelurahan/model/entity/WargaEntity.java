package org.project.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_warga")
public class WargaEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nik")
    private Integer nik;

    @Column(name = "nama_warga")
    private String namaWarga;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "age")
    private Integer age;
}
