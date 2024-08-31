package org.project.kelurahanacademy.kelurahan.repository;

import org.project.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelurahanRepo extends JpaRepository<KelurahanEntity, String> {
}
