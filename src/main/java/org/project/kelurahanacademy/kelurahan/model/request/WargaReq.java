package org.project.kelurahanacademy.kelurahan.model.request;

import lombok.Data;

@Data
public class WargaReq {
    private String id;
    private Integer nik;
    private String namaWarga;
    private String jenisKelamin;
    private Integer age;
}
