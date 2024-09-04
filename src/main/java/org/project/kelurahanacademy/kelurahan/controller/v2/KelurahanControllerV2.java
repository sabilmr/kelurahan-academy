package org.project.kelurahanacademy.kelurahan.controller.v2;

import org.project.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.project.kelurahanacademy.kelurahan.model.responsev2.Response;
import org.project.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v2/kelurahan")
public class KelurahanControllerV2 {
    private final KelurahanService kelurahanService;

    public KelurahanControllerV2(KelurahanService kelurahanService) {
        this.kelurahanService = kelurahanService;
    }

    @GetMapping
    public ResponseEntity<Response> get() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(kelurahanService.get())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable("id") String id) {
        Optional<KelurahanRes> response = kelurahanService.getById(id);
        return response.map(kelurahanRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(kelurahanRes)
                        .build()
        )).orElseGet(()->ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("DATA NOT FOUND")
                        .data(null)
                        .build()
        ));
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody KelurahanReq request) {
        Optional<KelurahanRes> result = kelurahanService.save(request);
        return result.map(kelurahanRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(kelurahanRes)
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
    public ResponseEntity<Response> update(@RequestBody KelurahanReq request,
                                           @PathVariable("id") String id) {
        Optional<KelurahanRes> result = kelurahanService.update(request, id);
        return result.map(kelurahanRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(kelurahanRes)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("UPDATE FAILED - DATA NOT FOUND")
                        .data(null)
                        .build()
        ));    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") String id) {
        Optional<KelurahanRes> result = kelurahanService.delete(id);
        return result.map(kelurahanRes -> ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message("SUCCESS")
                        .data(kelurahanRes)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message("DELETE FAILED")
                        .data(null)
                        .build()
        ));    }
}
