package com.salesianostriana.dam.alquilame.dwelling.controller;

import com.salesianostriana.dam.alquilame.dwelling.dto.AllDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.dto.DwellingRequest;
import com.salesianostriana.dam.alquilame.dwelling.dto.OneDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import com.salesianostriana.dam.alquilame.dwelling.service.DwellingService;
import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @GetMapping("/{id}")
    public OneDwellingResponse getDwellingDetails(@PathVariable Long id) {
        Dwelling result = dwellingService.findOneDwelling(id);

        return OneDwellingResponse.of(result);
    }

    @GetMapping("/user")
    public PageDto<AllDwellingResponse> getUserDwellings(@AuthenticationPrincipal User user,
                                                         @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(dwellingService.findUserDwellings(user, pageable));
    }

    @PostMapping("/")
    public ResponseEntity<OneDwellingResponse> createDwelling(@Valid @RequestBody DwellingRequest dto, @AuthenticationPrincipal User user) {
        Dwelling created = dwellingService.createDwelling(dto, user);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(createdURI)
                .body(OneDwellingResponse.of(created));
    }

    @PutMapping("/{id}")
    public OneDwellingResponse editDwelling(@PathVariable Long id, @Valid @RequestBody DwellingRequest dto, @AuthenticationPrincipal User user) {
        Dwelling edited = dwellingService.editDwelling(id, dto, user);

        return OneDwellingResponse.of(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDwelling(@PathVariable Long id, @AuthenticationPrincipal User user) {
        dwellingService.deleteOneDwelling(id, user);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/favourite")
    public ResponseEntity<OneDwellingResponse> markAsFavourite(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Dwelling newFavourite = dwellingService.doFavourite(id, user);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFavourite.getId()).toUri();

        return ResponseEntity.created(createdURI)
                .body(OneDwellingResponse.of(newFavourite));
    }

    @DeleteMapping("/{id}/favourite")
    public ResponseEntity<?> deleteFavourite(@PathVariable Long id, @AuthenticationPrincipal User user) {
        dwellingService.deleteFavourite(id, user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/province/{id}")
    public PageDto<AllDwellingResponse> getDwellingsByProvince(@PathVariable Long id,
                                                               @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(dwellingService.findByProvinceId(id, pageable));
    }

}
