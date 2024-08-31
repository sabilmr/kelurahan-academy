package org.project.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanReq {
    private String id;
    private String nama;
    private String alamat;
    private List<DusunReq> dusun = new ArrayList<>();

    public KelurahanReq(KelurahanEntity kelurahanEntity) {
        this.id = kelurahanEntity.getId();
        this.nama = kelurahanEntity.getNama();
        this.alamat = kelurahanEntity.getAlamat();

        if (!kelurahanEntity.getDaftarDusun().isEmpty()) {
            this.dusun = kelurahanEntity.getDaftarDusun().stream()
                    .map(DusunReq::new)
                    .collect(Collectors.toList());

        }
    }
}
