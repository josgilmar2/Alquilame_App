package com.salesianostriana.dam.alquilame.dwelling.controller;

import com.salesianostriana.dam.alquilame.dwelling.dto.AllDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.service.DwellingService;
import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dwelling")
@RequiredArgsConstructor
public class DwellingController {

    private final DwellingService dwellingService;

    @GetMapping("/")
    public PageDto<AllDwellingResponse> getAllDwelling(@RequestParam (value = "search", defaultValue = "") String search,
                                                       @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(dwellingService.findAllDwellings(search, pageable));
    }

}
