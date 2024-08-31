package org.project.kelurahanacademy.kelurahan.controller;

import org.project.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kelurahan")
public class KelurahanController {
    private final KelurahanService kelurahanService;

    public KelurahanController(KelurahanService kelurahanService) {
        this.kelurahanService = kelurahanService;
    }

    @GetMapping
    public String kelurahan() {
        return "pages/kelurahan/index";
    }
}
