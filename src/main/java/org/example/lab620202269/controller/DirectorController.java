package org.example.lab620202269.controller;

import jakarta.validation.Valid;
import org.example.lab620202269.entity.Director;
import org.example.lab620202269.entity.Pelicula;
import org.example.lab620202269.repository.DirectorRepository;
import org.example.lab620202269.repository.PeliculaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RequestMapping("/director")
@Controller
public class DirectorController {
    final PeliculaRepository peliculaRepository;
    final DirectorRepository directorRepository;

    public DirectorController(PeliculaRepository peliculaRepository, DirectorRepository directorRepository) {
        this.peliculaRepository = peliculaRepository;
        this.directorRepository = directorRepository;
    }

    @GetMapping("/list")
    public String listarDirectores(Model model) {
        model.addAttribute("listaDirectores", directorRepository.findAll());
        return "director/list";
    }

    @GetMapping("/new")
    public String nuevoDirector(@ModelAttribute ("director") Director director, Model model) {
        model.addAttribute("director", new Director());
        return "director/newForm";
    }

    @GetMapping("/edit")
    public String editarDirector(@ModelAttribute ("director") Director director, Model model, @RequestParam("directorId") int id) {
        Optional<Director> optional = directorRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("director", optional.get());
            return "director/editForm";
        } else {
            return "redirect:/director/list";
        }
    }

    @PostMapping("/save")
    public String guardarDirector( @ModelAttribute("director") Director director,
                                   BindingResult bindingResult,
                                   RedirectAttributes attr) {


        if (director.getDirectorId() == null) {
            attr.addFlashAttribute("msg", "Director creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Director actualizado exitosamente");
        }

        directorRepository.save(director);
        return "redirect:/director/list";
    }

    @GetMapping("/delete")
    public String borrarDirector(@RequestParam("directorId") int id, RedirectAttributes attr) {
        Optional<Director> optional = directorRepository.findById(id);

        if (optional.isPresent()) {
            directorRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Director borrado exitosamente");
        }

        return "redirect:/director/list";
    }

}
