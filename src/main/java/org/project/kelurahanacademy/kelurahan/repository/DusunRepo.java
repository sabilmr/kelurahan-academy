package org.project.kelurahanacademy.kelurahan.repository;

import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DusunRepo extends JpaRepository<DusunEntity, String> {
}
