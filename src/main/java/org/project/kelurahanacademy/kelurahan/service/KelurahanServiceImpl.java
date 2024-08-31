package org.project.kelurahanacademy.kelurahan.service;

import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.project.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.project.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.project.kelurahanacademy.kelurahan.repository.KelurahanRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

        if (!request.getDusun().isEmpty()){
            for (DusunReq dusun : request.getDusun()){
                DusunEntity dusunEntity = new DusunEntity();

                BeanUtils.copyProperties(dusun, dusunEntity);
                entity.addDusun(dusunEntity);
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
        return Optional.empty();
    }

    @Override
    public Optional<KelurahanRes> delete(String id) {
        return Optional.empty();
    }
}
