package org.project.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.project.kelurahanacademy.kelurahan.model.entity.RwEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@Data
@AllArgsConstructor
public class RWRes {
    private String id;
    private String name;
    private String nameRW;

    public RWRes(RwEntity rwEntity) {
        BeanUtils.copyProperties(rwEntity, this);
    }

}
