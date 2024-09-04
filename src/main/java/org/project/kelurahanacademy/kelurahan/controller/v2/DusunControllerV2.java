package org.project.kelurahanacademy.kelurahan.controller.v2;

import org.project.kelurahanacademy.kelurahan.model.responsev2.Response;
import org.project.kelurahanacademy.kelurahan.service.DusunService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/dusun")
public class DusunControllerV2 {
    private final DusunService dusunService;

    public DusunControllerV2(DusunService dusunService) {
        this.dusunService = dusunService;
    }

    @GetMapping
    public ResponseEntity<Response> get() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(dusunService.get())
                        .build()
        );
    }
}
