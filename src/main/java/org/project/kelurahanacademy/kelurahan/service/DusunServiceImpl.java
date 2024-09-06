package org.project.kelurahanacademy.kelurahan.service;

import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.RwEntity;
import org.project.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.project.kelurahanacademy.kelurahan.model.request.RWReq;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.project.kelurahanacademy.kelurahan.repository.DusunRepo;
import org.project.kelurahanacademy.kelurahan.repository.KelurahanRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DusunServiceImpl implements DusunService {
    private final DusunRepo dusunRepo;
    private final KelurahanRepo kelurahanRepo;

    public DusunServiceImpl(DusunRepo dusunRepo, KelurahanRepo kelurahanRepo) {
        this.dusunRepo = dusunRepo;
        this.kelurahanRepo = kelurahanRepo;
    }

    @Override
    public List<DusunRes> get() {
        List<DusunEntity> result = dusunRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream()
                .map(DusunRes::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DusunRes> getById(String id) {
        DusunEntity dusunEntity = dusunRepo.findById(id).orElse(null);
        if (dusunEntity == null) {
            return Optional.empty();
        }
        DusunRes dusunRes = new DusunRes(dusunEntity);
        return Optional.of(dusunRes);
    }

    @Override
    public Optional<DusunRes> save(DusunReq request) {
        DusunEntity entity = this.convertReqToEntity(request);

        BeanUtils.copyProperties(request, entity);
        entity.setId(UUID.randomUUID().toString());

        if (!request.getRw().isEmpty()) {
            for (RWReq rwReq : request.getRw()) {
                RwEntity rwReqEntity = new RwEntity();

                BeanUtils.copyProperties(rwReq, rwReqEntity);
                rwReqEntity.setId(UUID.randomUUID().toString());

                entity.addRW(rwReqEntity);
            }
        }
        try {
            dusunRepo.save(entity);
            log.info("Save Dusun Successfully");
            return Optional.of(new DusunRes(entity));
        } catch (Exception e) {
            log.error("Save Dusun Failed, error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DusunRes> update(DusunReq request, String id) {
        DusunEntity entity = dusunRepo.findById(id).orElse(null);
        if (entity == null) {
            return Optional.empty();
        }
        BeanUtils.copyProperties(request, entity);
        entity.setId(id);
        try {
            dusunRepo.save(entity);
            log.info("Update Dusun Successfully");
            return Optional.of(new DusunRes(entity));
        } catch (Exception e) {
            log.error("Update Dusun Failed, error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DusunRes> delete(String id) {
        DusunEntity dusunEntity = dusunRepo.findById(id).orElse(null);
        if (dusunEntity == null) {
            return Optional.empty();
        }
        try {
            dusunRepo.delete(dusunEntity);
            log.info("Delete Dusun Successfully");
            return Optional.of(convertResToEntity(dusunEntity));
        } catch (Exception e) {
            log.error("Delete Dusun Failed, error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    private DusunRes convertResToEntity(DusunEntity entity) {
        DusunRes dusunRes = new DusunRes();
        BeanUtils.copyProperties(entity, dusunRes);

        if (entity.getKelurahan() != null){
            dusunRes.setKelurahanId(entity.getKelurahan().getId());
            dusunRes.setKelurahanName(entity.getKelurahan().getNama());
        }
        return dusunRes;
    }

    private DusunEntity convertReqToEntity(DusunReq request) {
        KelurahanEntity entity = kelurahanRepo.findById(request.getKelurahanId()).orElse(null);
        if (entity == null) {
            return null;
        }

        DusunEntity result = new DusunEntity();
        BeanUtils.copyProperties(request, result);
        return result;
    }
}
