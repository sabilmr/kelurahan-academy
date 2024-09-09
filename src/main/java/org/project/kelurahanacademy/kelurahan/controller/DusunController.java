package org.project.kelurahanacademy.kelurahan.controller;

import lombok.RequiredArgsConstructor;
import org.project.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.project.kelurahanacademy.kelurahan.service.DusunService;
import org.project.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dusun")
public class DusunController {
    private final DusunService dusunService;
    private final KelurahanService kelurahanService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("pages/dusun/index");

        List<DusunRes> dusunRes = dusunService.get();
        modelAndView.addObject("dusun", dusunRes);
        return modelAndView;
    }

    public void addObject(ModelAndView view) {

        List<KelurahanRes> kelurahanRes = kelurahanService.get();
        view.addObject("kelurahan", kelurahanRes);
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("pages/dusun/add");

        modelAndView.addObject("dusun", new DusunReq());
        addObject(modelAndView);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("dusun") DusunReq dusunReq) {
        dusunService.save(dusunReq);
        return new ModelAndView("redirect:/dusun");
    }
}
