package com.salesianostriana.dam.alquilame.province.controller;

import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.province.dto.ProvinceDtoConverter;
import com.salesianostriana.dam.alquilame.province.dto.ProvinceRequest;
import com.salesianostriana.dam.alquilame.province.dto.ProvinceResponse;
import com.salesianostriana.dam.alquilame.province.model.Province;
import com.salesianostriana.dam.alquilame.province.service.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtiene toda la lista de provincias paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de provincias paginada con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinceResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": 1,
                                                        "name": "A Coruña",
                                                        "dwelling": [
                                                            {
                                                                "id": 89,
                                                                "name": "Casa independiente en IÃ±as, cerca centro comercial",
                                                                "province": "A Coruña",
                                                                "image": "https://robohash.org/perferendisfugiatqui.png?size=50x50&set=set1",
                                                                "price": 700.35
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 2,
                                                        "name": "Alava",
                                                        "dwelling": [
                                                            {
                                                                "id": 91,
                                                                "name": "Chalet adosado en calle Gorbea, 1",
                                                                "province": "Alava",
                                                                "image": "https://robohash.org/aliasestvitae.png?size=50x50&set=set1",
                                                                "price": 1236.7
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 3,
                                                        "name": "Albacete",
                                                        "dwelling": [
                                                            {
                                                                "id": 90,
                                                                "name": "Piso en San Antonio Abad - PolÃ­gono San AntÃ³n",
                                                                "province": "Albacete",
                                                                "image": "https://robohash.org/earummagniea.png?size=50x50&set=set1",
                                                                "price": 650.86
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 4,
                                                        "name": "Alicante",
                                                        "dwelling": [
                                                            {
                                                                "id": 74,
                                                                "name": "Casa independiente en calle Galia",
                                                                "province": "Alicante",
                                                                "image": "https://robohash.org/molestiaeenimblanditiis.png?size=50x50&set=set1",
                                                                "price": 6000.12
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 5,
                                                        "name": "Almeria",
                                                        "dwelling": []
                                                    },
                                                    {
                                                        "id": 6,
                                                        "name": "Asturias",
                                                        "dwelling": [
                                                            {
                                                                "id": 68,
                                                                "name": "Casa independiente en calle Naon, 20 A",
                                                                "province": "Asturias",
                                                                "image": "https://robohash.org/velquaequia.png?size=50x50&set=set1",
                                                                "price": 650.24
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 7,
                                                        "name": "Avila",
                                                        "dwelling": [
                                                            {
                                                                "id": 66,
                                                                "name": "El Refugio, Casa en calle Albahaca NÂº36",
                                                                "province": "Avila",
                                                                "image": "https://robohash.org/etquamcorrupti.png?size=50x50&set=set1",
                                                                "price": 900.77
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 8,
                                                        "name": "Badajoz",
                                                        "dwelling": [
                                                            {
                                                                "id": 85,
                                                                "name": "Casa adosado en calle CASTILLO DE ALMORCHÃ“N",
                                                                "province": "Badajoz",
                                                                "image": "https://robohash.org/eosadipiscidistinctio.png?size=50x50&set=set1",
                                                                "price": 900.96
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 9,
                                                        "name": "Baleares",
                                                        "dwelling": []
                                                    },
                                                    {
                                                        "id": 10,
                                                        "name": "Barcelona",
                                                        "dwelling": [
                                                            {
                                                                "id": 58,
                                                                "name": "Piso barato en Barcelona",
                                                                "province": "Barcelona",
                                                                "image": "https://robohash.org/eoseaqueest.png?size=50x50&set=set1",
                                                                "price": 788.51
                                                            },
                                                            {
                                                                "id": 88,
                                                                "name": "Casa independiente en Passatge Oriol",
                                                                "province": "Barcelona",
                                                                "image": "https://robohash.org/exeosvoluptatem.png?size=50x50&set=set1",
                                                                "price": 1529.37
                                                            },
                                                            {
                                                                "id": 97,
                                                                "name": "Piso en VARSOVIA",
                                                                "province": "Barcelona",
                                                                "image": "https://robohash.org/voluptatibuserrorillo.png?size=50x50&set=set1",
                                                                "price": 886.91
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 11,
                                                        "name": "Bizkaia",
                                                        "dwelling": []
                                                    },
                                                    {
                                                        "id": 12,
                                                        "name": "Burgos",
                                                        "dwelling": []
                                                    },
                                                    {
                                                        "id": 13,
                                                        "name": "Caceres",
                                                        "dwelling": [
                                                            {
                                                                "id": 54,
                                                                "name": "Piso Acogedor",
                                                                "province": "Caceres",
                                                                "image": "https://robohash.org/veniamiustoqui.png?size=50x50&set=set1",
                                                                "price": 450.0
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 14,
                                                        "name": "Cadiz",
                                                        "dwelling": [
                                                            {
                                                                "id": 65,
                                                                "name": "Piso en Sagasta",
                                                                "province": "Cadiz",
                                                                "image": "https://robohash.org/quidemplaceatconsequatur.png?size=50x50&set=set1",
                                                                "price": 600.14
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 15,
                                                        "name": "Cantabria",
                                                        "dwelling": [
                                                            {
                                                                "id": 62,
                                                                "name": "Casa adosado en calle Vicente Trueba, 7",
                                                                "province": "Cantabria",
                                                                "image": "https://robohash.org/necessitatibusanimirerum.png?size=50x50&set=set1",
                                                                "price": 1200.44
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 16,
                                                        "name": "Castellon",
                                                        "dwelling": [
                                                            {
                                                                "id": 87,
                                                                "name": "Chalet independiente en calle de la Muntanyeta de Sant Josep, 26",
                                                                "province": "Castellon",
                                                                "image": "https://robohash.org/quisabvoluptatem.png?size=50x50&set=set1",
                                                                "price": 900.13
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 17,
                                                        "name": "Ceuta",
                                                        "dwelling": []
                                                    },
                                                    {
                                                        "id": 18,
                                                        "name": "Ciudad Real",
                                                        "dwelling": [
                                                            {
                                                                "id": 76,
                                                                "name": "Casa adosada en Pau 2 - 600",
                                                                "province": "Ciudad Real",
                                                                "image": "https://robohash.org/atoditest.png?size=50x50&set=set1",
                                                                "price": 525.7
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 19,
                                                        "name": "Cordoba",
                                                        "dwelling": [
                                                            {
                                                                "id": 71,
                                                                "name": "Chalet independiente en calle Junco, 1",
                                                                "province": "Cordoba",
                                                                "image": "https://robohash.org/commodiametnatus.png?size=50x50&set=set1",
                                                                "price": 1250.36
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 20,
                                                        "name": "Cuenca",
                                                        "dwelling": []
                                                    }
                                                ],
                                                "totalElements": 52,
                                                "totalPages": 3,
                                                "number": 0,
                                                "size": 20
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la lista de provincias",
                    content = @Content)
    })
    @GetMapping("/")
    public PageDto<ProvinceResponse> getAllProvinces(@PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(provinceService.findAll(pageable));
    }

    @Operation(summary = "Obtiene una provincia por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado con éxito la provincia por su identificador",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinceResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 3,
                                                "name": "Albacete",
                                                "dwellings": [
                                                    {
                                                        "id": 90,
                                                        "name": "Piso en San Antonio Abad - Polígono San Antón",
                                                        "province": "Albacete",
                                                        "image": "https://robohash.org/earummagniea.png?size=50x50&set=set1",
                                                        "price": 650.86
                                                    }
                                                ]
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la provincia indicada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ProvinceResponse getOneProvince(@Parameter(description = "Identificador de la provincia a buscar")
                                               @PathVariable Long id) {
        Province result = provinceService.findById(id);

        return provinceDtoConverter.provinceToProvinceResponse(result);
    }

    @Operation(summary = "Creación de una provincia")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProvinceRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                        "name": "New York"
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva provincia con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinceResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 102,
                                                "name": "New York",
                                                "dwellings": []
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha ocurrido algún error al crear la provincia",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
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

    @Operation(summary = "Edita una provincia por su identificador")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProvinceRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                        "name": "New York"
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente la provincia",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinceResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 3,
                                                "name": "New York",
                                                "dwelling": [
                                                    {
                                                        "id": 90,
                                                        "name": "Piso en San Antonio Abad - Polígono San Antón",
                                                        "province": null,
                                                        "image": "https://robohash.org/earummagniea.png?size=50x50&set=set1",
                                                        "price": 650.86
                                                    }
                                                ]
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha ocurrido algún error al editar la provincia",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la provincia a editar",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ProvinceResponse editProvince(@Parameter(description = "Identificador de la provincia a buscar")
                                             @PathVariable Long id, @Valid @RequestBody ProvinceRequest dto) {
        Province edited = provinceService.edit(id, dto);

        return provinceDtoConverter.provinceToProvinceResponse(edited);
    }

    @Operation(summary = "Elimina una provincia por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado correctamente la provincia seleccionada",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La provincia seleccionada no existe, por lo que no se puede borrar",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProvince(@PathVariable Long id) {
        provinceService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
