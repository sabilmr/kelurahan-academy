package org.project.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanRes {
    private String id;
    private String nama;
    private String alamat;
    private List<DusunRes> dusun = new ArrayList<>();

    public KelurahanRes(KelurahanEntity kelurahanEntity) {
        this.id = kelurahanEntity.getId();
        this.nama = kelurahanEntity.getNama();
        this.alamat = kelurahanEntity.getAlamat();

        if (!kelurahanEntity.getDaftarDusun().isEmpty()) {
            this.dusun = kelurahanEntity.getDaftarDusun().stream()
                    .map(DusunRes::new)
                    .collect(Collectors.toList());

        }
    }
}
