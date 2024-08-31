package org.project.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DusunRes {
    private String id;
    private String name;

    public DusunRes(DusunEntity entity){
        BeanUtils.copyProperties(entity, this);
    }
}
