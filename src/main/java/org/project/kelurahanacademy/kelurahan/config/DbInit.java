package org.project.kelurahanacademy.kelurahan.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.RtEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.RwEntity;
import org.project.kelurahanacademy.kelurahan.repository.KelurahanRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {
    private final KelurahanRepo kelurahanRepo;


    @Override
    public void run(String... args) throws Exception {
        initKelurahan();

    }

    private void initKelurahan() {
        if (kelurahanRepo.count() > 0) {
            return;
        }

        KelurahanEntity kelurahan = new KelurahanEntity("", "DESA SEBANI", "PANDAAN");

        DusunEntity jedung = new DusunEntity("", "DUSUN JEDUNG");
        DusunEntity ciparakan = new DusunEntity("", "DUSUN CIPARAKAN");
        DusunEntity tanggol = new DusunEntity("", "DUSUN TANGGOL");

        kelurahan.addDusun(jedung);
        kelurahan.addDusun(ciparakan);
        kelurahan.addDusun(tanggol);

        RwEntity rw01 = new RwEntity("","RW01","BAPAK SUMANTO");
        RwEntity rw04 = new RwEntity("","RW04","BAPAK DORI");
        RwEntity rw09 = new RwEntity("","RW01","BAPAK JONO");

        jedung.addRW(rw01);
        ciparakan.addRW(rw04);
        tanggol.addRW(rw09);

        RtEntity rt10 = new RtEntity("","RT10","BAPAK KASINO");
        RtEntity rt11 = new RtEntity("","RT11","BAPAK INDRO");
        RtEntity rt12 = new RtEntity("","RT12","BAPAK DONO");

        rw01.addRT(rt10);
        rw04.addRT(rt11);
        rw09.addRT(rt12);

        try {
            kelurahanRepo.save(kelurahan);
            log.info("Save Kelurahan successfully");
        } catch (Exception e) {
            log.error("Save Kelurahan failed, error {}", e.getMessage());
        }
    }
}
