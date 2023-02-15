package com.salesianostriana.dam.alquilame.province.controller;

import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.province.dto.ProvinceDtoConverter;
import com.salesianostriana.dam.alquilame.province.dto.ProvinceRequest;
import com.salesianostriana.dam.alquilame.province.dto.ProvinceResponse;
import com.salesianostriana.dam.alquilame.province.model.Province;
import com.salesianostriana.dam.alquilame.province.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/province")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;
    private final ProvinceDtoConverter provinceDtoConverter;

    @GetMapping("/")
    public PageDto<ProvinceResponse> getAllProvinces(@PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(provinceService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ProvinceResponse getOneProvince(@PathVariable Long id) {
        Province result = provinceService.findById(id);

        return provinceDtoConverter.provinceToProvinceResponse(result);
    }

    @PostMapping("/")
    public ResponseEntity<ProvinceResponse> createProvince(@Valid @RequestBody ProvinceRequest dto) {
        Province created = provinceService.create(dto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(createdURI)
                .body(provinceDtoConverter.provinceToProvinceResponse(created));
    }

    @PutMapping("/{id}")
    public ProvinceResponse editProvince(@PathVariable Long id, @Valid @RequestBody ProvinceRequest dto) {
        Province edited = provinceService.edit(id, dto);

        return provinceDtoConverter.provinceToProvinceResponse(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProvince(@PathVariable Long id) {
        provinceService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
