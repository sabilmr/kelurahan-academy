package org.project.kelurahanacademy.kelurahan.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.project.kelurahanacademy.kelurahan.repository.KelurahanRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver;

import java.util.Random;
import java.util.UUID;

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

        KelurahanEntity kelurahan = new KelurahanEntity("","DESA SEBANI","PANDAAN");

        kelurahan.addDusun(new DusunEntity("","DUSUN JEDUNG",kelurahan));
        kelurahan.addDusun(new DusunEntity("","DUSUN KEMULAN",kelurahan));
        kelurahan.addDusun(new DusunEntity("","DUSUN CLUMPRIT",kelurahan));
        try {
            kelurahanRepo.save(kelurahan);
            log.info("Save Kelurahan successfully");
        } catch (Exception e) {
            log.error("Save Kelurahan failed, error {}", e.getMessage() );
        }
    }
}
