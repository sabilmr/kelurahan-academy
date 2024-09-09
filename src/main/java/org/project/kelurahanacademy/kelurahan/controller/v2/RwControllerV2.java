package org.project.kelurahanacademy.kelurahan.controller.v2;

import org.project.kelurahanacademy.kelurahan.model.responsev2.Response;
import org.project.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/rw")
public class RwControllerV2 {
    private final RWService rwService;

    public RwControllerV2(RWService rwService) {
        this.rwService = rwService;
    }

    @GetMapping
    public ResponseEntity<Response> get() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(rwService.get())
                        .build()
        );
    }
}
