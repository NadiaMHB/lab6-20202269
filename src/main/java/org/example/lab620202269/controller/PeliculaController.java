package org.example.lab620202269.controller;

import jakarta.validation.Valid;
import org.example.lab620202269.entity.Pelicula;
import org.example.lab620202269.repository.DirectorRepository;
import org.example.lab620202269.repository.PeliculaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RequestMapping("/pelicula")
@Controller
public class PeliculaController {
    final PeliculaRepository peliculaRepository;
    final DirectorRepository directorRepository;

    public PeliculaController(PeliculaRepository peliculaRepository, DirectorRepository directorRepository) {
        this.peliculaRepository = peliculaRepository;
        this.directorRepository = directorRepository;
    }

    @GetMapping("/list")
    public String listarPeliculas(Model model) {
        model.addAttribute("listaPeliculas", peliculaRepository.findAll());
        return "pelicula/list";
    }

    @GetMapping("/new")
    public String nuevaPelicula(@ModelAttribute ("pelicula") Pelicula pelicula, Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "pelicula/newForm";
    }

    @GetMapping("/edit")
    public String editarPelicula(@ModelAttribute ("pelicula") Pelicula pelicula, Model model, @RequestParam("peliculaId") int id) {
        Optional<Pelicula> optional = peliculaRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("pelicula", optional.get());
            return "pelicula/editForm";
        } else {
            return "redirect:/pelicula/list";
        }
    }

    @PostMapping("/save")
    public String guardarPelicula( @ModelAttribute("pelicula") Pelicula pelicula,
                                  BindingResult bindingResult,
                                  RedirectAttributes attr) {


        if (pelicula.getPeliculaId() == null) {
            attr.addFlashAttribute("msg", "Pelicula creada exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Pelicula actualizada exitosamente");
        }

        peliculaRepository.save(pelicula);
        return "redirect:/pelicula/list";
    }

    @GetMapping("/delete")
    public String borrarPelicula(@RequestParam("peliculaId") int id, RedirectAttributes attr) {
        Optional<Pelicula> optional = peliculaRepository.findById(id);

        if (optional.isPresent()) {
            peliculaRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Pelicula borrada exitosamente");
        }

        return "redirect:/pelicula/list";
    }

}
