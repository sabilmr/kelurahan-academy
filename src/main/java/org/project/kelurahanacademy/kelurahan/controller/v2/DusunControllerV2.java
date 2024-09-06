package org.project.kelurahanacademy.kelurahan.controller.v2;

import org.project.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.project.kelurahanacademy.kelurahan.model.responsev2.Response;
import org.project.kelurahanacademy.kelurahan.service.DusunService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable("id") String id) {
        Optional<DusunRes> response = dusunService.getById(id);
        return response.map(dusunRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(dusunRes)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("NOT FOUND")
                        .data(null)
                        .build()

        ));
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody DusunReq request) {
        Optional<DusunRes> response = dusunService.save(request);
        return response.map(dusunRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(dusunRes)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("BAD REQUEST")
                        .data(null)
                        .build()
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@RequestBody DusunReq request,
                                           @PathVariable("id") String id) {
        Optional<DusunRes> response = dusunService.update(request, id);
        return response.map(dusunRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("SUCCESS")
                        .data(dusunRes)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("UPDATE FAILED")
                        .data(null)
                        .build()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") String id) {
        Optional<DusunRes> response = dusunService.delete(id);
        return response.map(dusunRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("SUCCESS")
                        .data(dusunRes)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("DELETE FAILED")
                        .data(null)
                        .build()
        ));
    }
}
