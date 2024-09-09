package org.project.kelurahanacademy.kelurahan.controller;

import lombok.RequiredArgsConstructor;
import org.project.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.project.kelurahanacademy.kelurahan.model.response.RWRes;
import org.project.kelurahanacademy.kelurahan.service.DusunService;
import org.project.kelurahanacademy.kelurahan.service.KelurahanService;
import org.project.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kelurahan")
public class KelurahanController {
    private final KelurahanService kelurahanService;
    private final DusunService dusunService;
    private final RWService rwService;

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

    @GetMapping("/detail/{kelurahanId}/dusun/{dusunId}")
    public ModelAndView dusunDetail(@PathVariable("kelurahanId") String kelurahanId,
                                    @PathVariable("dusunId") String dusunId) {
        ModelAndView view = new ModelAndView("pages/kelurahan/detail/detail-dusun");

        Optional<KelurahanRes> result = kelurahanService.getById(kelurahanId);
        if (result.isPresent()) {
            Optional<DusunRes> dusun = dusunService.getById(dusunId);
            view.addObject("kelurahan", result.get());
            view.addObject("dusun", dusun);
            return view;
        } else {
            return new ModelAndView("redirect:/kelurahan");
        }
    }


    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/kelurahan/add");
        view.addObject("kelurahan", new KelurahanRes());
        return view;
    }

    @GetMapping("/add-dusun/{index}")
    public ModelAndView addDusun(@PathVariable("index") int index){
        ModelAndView view = new ModelAndView("pages/kelurahan/add-dusun");
        view.addObject("index", index);

        List<RWRes> result = rwService.get();
        view.addObject("rw", result);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kelurahan") KelurahanReq request) {
        kelurahanService.save(request);
        return new ModelAndView("redirect:/kelurahan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/kelurahan/edit");
        Optional<KelurahanRes> result = kelurahanService.getById(id);
        if (result.isPresent()) {
            view.addObject("kelurahan", result.get());
            return view;
        }
        return new ModelAndView("redirect:/kelurahan");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kelurahan") KelurahanReq request) {
        kelurahanService.update(request, request.getId());
        return new ModelAndView("redirect:/kelurahan");
    }

}
