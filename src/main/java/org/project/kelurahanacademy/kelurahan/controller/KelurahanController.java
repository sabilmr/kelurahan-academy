package org.project.kelurahanacademy.kelurahan.controller;

import org.project.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.project.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/kelurahan")
public class KelurahanController {
    private final KelurahanService kelurahanService;

    public KelurahanController(KelurahanService kelurahanService) {
        this.kelurahanService = kelurahanService;
    }

    @GetMapping
    public ModelAndView kelurahan() {
       ModelAndView view = new ModelAndView("pages/kelurahan/index");

       List<KelurahanRes> result = kelurahanService.get();
       view.addObject("kelurahan", result);
       return view;

    }

    @GetMapping("/detail/{id}")
    public ModelAndView kelurahanDetail(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/kelurahan/detail/detail");

        Optional<KelurahanRes> result = kelurahanService.getById(id);
        if (result.isPresent()) {
            view.addObject("kelurahan", result.get());
            return view;
        }
        return new ModelAndView("redirect:/kelurahan");
    }


    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/kelurahan/add");
        view.addObject("kelurahan", new KelurahanRes());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kelurahan") KelurahanReq request) {
        ModelAndView view = new ModelAndView("pages/kelurahan/add");
        view.addObject("kelurahan", request);

        kelurahanService.save(request);
        return new ModelAndView("redirect:/kelurahan");
    }


}
