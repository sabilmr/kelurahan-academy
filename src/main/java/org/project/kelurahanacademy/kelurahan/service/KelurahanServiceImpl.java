package org.project.kelurahanacademy.kelurahan.service;

import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.project.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.project.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
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
public class KelurahanServiceImpl implements KelurahanService{
    private final KelurahanRepo repo;

    public KelurahanServiceImpl(KelurahanRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<KelurahanRes> get() {
        List<KelurahanEntity> result = repo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(KelurahanRes::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<KelurahanRes> getById(String id) {
        KelurahanEntity entity = repo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        KelurahanRes res = new KelurahanRes(entity);
        return Optional.of(res);
    }

    @Override
    public Optional<KelurahanRes> save(KelurahanReq request) {
        KelurahanEntity entity = new KelurahanEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setId(UUID.randomUUID().toString());

        if(!request.getDusun().isEmpty()) {
            for (DusunReq dusunReq : request.getDusun()) {
                DusunEntity entityDusun = new DusunEntity();

                BeanUtils.copyProperties(dusunReq, entityDusun);
                entityDusun.setId(UUID.randomUUID().toString());

                entity.addDusun(entityDusun);
            }
        }
        try {
            repo.save(entity);
            log.info("Save Kelurahan Success");
            return Optional.of(new KelurahanRes(entity));
        } catch (Exception e) {
            log.error("Save Kelurahan failed, error: {}", e.getMessage());
            return Optional.empty();

        }
    }

    @Override
    public Optional<KelurahanRes> update(KelurahanReq request, String id) {
        KelurahanEntity entity = repo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            repo.save(entity);
            log.info("Update Kelurahan Success");
            return Optional.of(new KelurahanRes(entity));
        } catch (Exception e) {
            log.error("Update Kelurahan failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelurahanRes> delete(String id) {
        KelurahanEntity entity = repo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        try {
            repo.delete(entity);
            log.info("Delete Kelurahan Success");
            return Optional.of(new KelurahanRes(entity));
        } catch (Exception e) {
            log.error("Delete Kelurahan failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private KelurahanEntity getByEntityId(DusunEntity dusunEntity) {
        KelurahanEntity result = this.repo.findById(dusunEntity.getKelurahanId()).orElse(null);
        if (result == null){
            return null;
        }
        return result;
    }
}
