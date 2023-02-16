package com.salesianostriana.dam.alquilame.dwelling.controller;

import com.salesianostriana.dam.alquilame.dwelling.dto.AllDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.dto.DwellingRequest;
import com.salesianostriana.dam.alquilame.dwelling.dto.OneDwellingResponse;
import com.salesianostriana.dam.alquilame.dwelling.model.Dwelling;
import com.salesianostriana.dam.alquilame.dwelling.service.DwellingService;
import com.salesianostriana.dam.alquilame.page.dto.PageDto;
import com.salesianostriana.dam.alquilame.user.model.User;
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

    @Operation(summary = "Obtiene todas las viviendas paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la lista de viviendas paginadas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": 1,
                                                        "name": "A CoruÃ±a",
                                                        "dwellings": [
                                                            {
                                                                "id": 89,
                                                                "name": "Casa independiente en IÃ±as, cerca centro comercial",
                                                                "province": null,
                                                                "image": "https://robohash.org/perferendisfugiatqui.png?size=50x50&set=set1",
                                                                "price": 700.35
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 2,
                                                        "name": "Alava",
                                                        "dwellings": [
                                                            {
                                                                "id": 91,
                                                                "name": "Chalet adosado en calle Gorbea, 1",
                                                                "province": null,
                                                                "image": "https://robohash.org/aliasestvitae.png?size=50x50&set=set1",
                                                                "price": 1236.7
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 3,
                                                        "name": "Albacete",
                                                        "dwellings": [
                                                            {
                                                                "id": 90,
                                                                "name": "Piso en San Antonio Abad - Poligono San Anton",
                                                                "province": null,
                                                                "image": "https://robohash.org/earummagniea.png?size=50x50&set=set1",
                                                                "price": 650.86
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 4,
                                                        "name": "Alicante",
                                                        "dwellings": [
                                                            {
                                                                "id": 74,
                                                                "name": "Casa independiente en calle Galia",
                                                                "province": null,
                                                                "image": "https://robohash.org/molestiaeenimblanditiis.png?size=50x50&set=set1",
                                                                "price": 6000.12
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 5,
                                                        "name": "Almeria",
                                                        "dwellings": []
                                                    },
                                                    {
                                                        "id": 6,
                                                        "name": "Asturias",
                                                        "dwellings": [
                                                            {
                                                                "id": 68,
                                                                "name": "Casa independiente en calle Naon, 20 A",
                                                                "province": null,
                                                                "image": "https://robohash.org/velquaequia.png?size=50x50&set=set1",
                                                                "price": 650.24
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 7,
                                                        "name": "Avila",
                                                        "dwellings": [
                                                            {
                                                                "id": 66,
                                                                "name": "El Refugio, Casa en calle Albahaca NÂº36",
                                                                "province": null,
                                                                "image": "https://robohash.org/etquamcorrupti.png?size=50x50&set=set1",
                                                                "price": 900.77
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 8,
                                                        "name": "Badajoz",
                                                        "dwellings": [
                                                            {
                                                                "id": 85,
                                                                "name": "Casa adosado en calle CASTILLO DE ALMORCHoN",
                                                                "province": null,
                                                                "image": "https://robohash.org/eosadipiscidistinctio.png?size=50x50&set=set1",
                                                                "price": 900.96
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 9,
                                                        "name": "Baleares",
                                                        "dwellings": []
                                                    },
                                                    {
                                                        "id": 10,
                                                        "name": "Barcelona",
                                                        "dwellings": [
                                                            {
                                                                "id": 58,
                                                                "name": "Piso barato en Barcelona",
                                                                "province": null,
                                                                "image": "https://robohash.org/eoseaqueest.png?size=50x50&set=set1",
                                                                "price": 788.51
                                                            },
                                                            {
                                                                "id": 88,
                                                                "name": "Casa independiente en Passatge Oriol",
                                                                "province": null,
                                                                "image": "https://robohash.org/exeosvoluptatem.png?size=50x50&set=set1",
                                                                "price": 1529.37
                                                            },
                                                            {
                                                                "id": 97,
                                                                "name": "Piso en VARSOVIA",
                                                                "province": null,
                                                                "image": "https://robohash.org/voluptatibuserrorillo.png?size=50x50&set=set1",
                                                                "price": 886.91
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 11,
                                                        "name": "Bizkaia",
                                                        "dwellings": []
                                                    },
                                                    {
                                                        "id": 12,
                                                        "name": "Burgos",
                                                        "dwellings": []
                                                    },
                                                    {
                                                        "id": 13,
                                                        "name": "Caceres",
                                                        "dwellings": [
                                                            {
                                                                "id": 54,
                                                                "name": "Piso Acogedor",
                                                                "province": null,
                                                                "image": "https://robohash.org/veniamiustoqui.png?size=50x50&set=set1",
                                                                "price": 450.0
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 14,
                                                        "name": "Cadiz",
                                                        "dwellings": [
                                                            {
                                                                "id": 65,
                                                                "name": "Piso en Sagasta",
                                                                "province": null,
                                                                "image": "https://robohash.org/quidemplaceatconsequatur.png?size=50x50&set=set1",
                                                                "price": 600.14
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 15,
                                                        "name": "Cantabria",
                                                        "dwellings": [
                                                            {
                                                                "id": 62,
                                                                "name": "Casa adosado en calle Vicente Trueba, 7",
                                                                "province": null,
                                                                "image": "https://robohash.org/necessitatibusanimirerum.png?size=50x50&set=set1",
                                                                "price": 1200.44
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 16,
                                                        "name": "Castellon",
                                                        "dwellings": [
                                                            {
                                                                "id": 87,
                                                                "name": "Chalet independiente en calle de la Muntanyeta de Sant Josep, 26",
                                                                "province": null,
                                                                "image": "https://robohash.org/quisabvoluptatem.png?size=50x50&set=set1",
                                                                "price": 900.13
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 17,
                                                        "name": "Ceuta",
                                                        "dwellings": []
                                                    },
                                                    {
                                                        "id": 18,
                                                        "name": "Ciudad Real",
                                                        "dwellings": [
                                                            {
                                                                "id": 76,
                                                                "name": "Casa adosada en Pau 2 - 600",
                                                                "province": null,
                                                                "image": "https://robohash.org/atoditest.png?size=50x50&set=set1",
                                                                "price": 525.7
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 19,
                                                        "name": "Cordoba",
                                                        "dwellings": [
                                                            {
                                                                "id": 71,
                                                                "name": "Chalet independiente en calle Junco, 1",
                                                                "province": null,
                                                                "image": "https://robohash.org/commodiametnatus.png?size=50x50&set=set1",
                                                                "price": 1250.36
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 20,
                                                        "name": "Cuenca",
                                                        "dwellings": []
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
                    description = "No se ha encontrado ninguna vivienda",
                    content = @Content)
    })
    @GetMapping("/")
    public PageDto<AllDwellingResponse> getAllDwelling(@RequestParam (value = "search", defaultValue = "") String search,
                                                       @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(dwellingService.findAllDwellings(search, pageable));
    }

    @Operation(summary = "Obtiene una provincia por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la vivienda por su identificador",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OneDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 53,
                                                "name": "Estancia Campos De Lino",
                                                "province": "Guadalajara",
                                                "image": "https://robohash.org/laboreullamaut.png?size=50x50&set=set1",
                                                "price": 803.38,
                                                "address": "Torrejon del Rey - Guadalajara",
                                                "description": "Chalet independiente en parcela de 600 metros. Divido en 2 plantas sin escaleras son independientes las 2, la parte de arriba con 3 dormitorios muy amplios con armarios empotrados y maleteros grandes, aire acondicionado con bomba cde calor y calefaccion por gasoil, ventanas de pvc Climalit 10 y mosquiteras, las puertas son de ancho para minusvalidos, para baÃ±o. Completo con ventana mosquitera y baÃ±era, cocina completa con mesa y banco 2 ventanas, vitroceramica de induccion, puerta corredera para no entorpecer en la cocina salon con 2 terrazas grande con chimenea. Una de ellas esta cerrada con cerramiento y sirve para habitacion o despacho o q se requiera a gusto de las necesidades de ca uno, tiene mucho sol y vistas al jardin, suelos rusticos. Terraza abierta mas diminuta para tender y da a la piscina y vistas, entrada con una cancela cerrada de aluminio y ventana oscilobatiente y puerta corredera antimosquitos y entrada a la casa con holl amplio q de frente el salon, derecha habitaciones e izda. Cocina. Portero automatico y puerta con motor para meter el coche si uno quiere hay suficiente para aparcar fuera, pero tiene su cochera por si quieres meter motos o bicis. Segunda vivienda las dos miden 150metros cada una. Entras y tienes un salon garaje muy grande con armario, para lavadora o secadora y preparada para poner pila si quieres, cuarto de calderas con deposito 750 litros y tiene espacio para trastos, 2 habitaciones una forrada toda de madera con armario empotrados 3 cuerpos mas maleteros amplios da a la piscina, otro salon muy grande con la cocina tipo isla y chimenea, tiene unas puertas correderas grandes para diferenciar los espacios, luego otra habitacion con puerta corredera aire acondicionado y bomba de calor y baÃ±o completo con ventana mosquitera y plato de ducha. Esta para entrar a vivir.",
                                                "type": "CHALET",
                                                "m2": 300.0,
                                                "numBedrooms": 5,
                                                "numBathrooms": 2,
                                                "hasElevator": false,
                                                "hasPool": true,
                                                "hasTerrace": true,
                                                "hasGarage": true,
                                                "owner": {
                                                    "username": "eantoniutti0",
                                                    "fullName": "Elicia Antoniutti",
                                                    "email": "eantoniutti0@furl.net",
                                                    "phoneNumber": "80132928",
                                                    "numPublications": 5
                                                }
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
                    description = "No se ha encontrado la vivienda seleccionada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public OneDwellingResponse getDwellingDetails(@Parameter(description = "Identificador de la vivienda a buscar")
                                                      @PathVariable Long id) {
        Dwelling result = dwellingService.findOneDwelling(id);

        return OneDwellingResponse.of(result);
    }

    @Operation(summary = "Obtener la lista de viviendas pagiandas de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado correctamente la lista de viviendas pagianadas del usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": 53,
                                                        "name": "Estancia Campos De Lino",
                                                        "province": "Guadalajara",
                                                        "image": "https://robohash.org/laboreullamaut.png?size=50x50&set=set1",
                                                        "price": 803.38
                                                    },
                                                    {
                                                        "id": 54,
                                                        "name": "Piso Acogedor",
                                                        "province": "Caceres",
                                                        "image": "https://robohash.org/veniamiustoqui.png?size=50x50&set=set1",
                                                        "price": 450.0
                                                    },
                                                    {
                                                        "id": 55,
                                                        "name": "Casa Los Rodriguez",
                                                        "province": "Murcia",
                                                        "image": "https://robohash.org/voluptatemenimnihil.png?size=50x50&set=set1",
                                                        "price": 1500.94
                                                    },
                                                    {
                                                        "id": 75,
                                                        "name": "Piso en paseo heriz",
                                                        "province": "Guipuzkoa",
                                                        "image": "https://robohash.org/animiquasperferendis.png?size=50x50&set=set1",
                                                        "price": 921.43
                                                    },
                                                    {
                                                        "id": 89,
                                                        "name": "Casa independiente en IÃ±as, cerca centro comercial",
                                                        "province": "A CoruÃ±a",
                                                        "image": "https://robohash.org/perferendisfugiatqui.png?size=50x50&set=set1",
                                                        "price": 700.35
                                                    }
                                                ],
                                                "totalElements": 5,
                                                "totalPages": 1,
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
                    description = "No se ha encontrado la lista de viviendas del usuario",
                    content = @Content)
    })
    @GetMapping("/user")
    public PageDto<AllDwellingResponse> getUserDwellings(@AuthenticationPrincipal User user,
                                                         @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(dwellingService.findUserDwellings(user, pageable));
    }

    @Operation(summary = "Creación de una vivienda")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DwellingRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                        "name": "Piso Cuqui",
                                        "address": "C/ Sierpes Nº2, Sevilla",
                                        "description": "Piso muy chulo que está muy chulo y sigue siendo chulo. Tiene habitaciones chulas y eso :)",
                                        "image": "",
                                        "type": "CASA",
                                        "price": 563,
                                        "m2": 34,
                                        "numBedrooms": 3,
                                        "numBathrooms": 1,
                                        "hasElevator": true,
                                        "hasPool": false,
                                        "hasTerrace": true,
                                        "hasGarage": false,
                                        "provinceId": 43
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente la vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OneDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 102,
                                                "name": "Piso Cuqui",
                                                "province": "Sevilla",
                                                "image": "",
                                                "price": 563.0,
                                                "address": "C/ Sierpes Nº2, Sevilla",
                                                "description": "Piso muy chulo que está muy chulo y sigue siendo chulo. Tiene habitaciones chulas y eso :)",
                                                "type": "CASA",
                                                "m2": 34.0,
                                                "numBedrooms": 3,
                                                "numBathrooms": 1,
                                                "hasElevator": true,
                                                "hasPool": false,
                                                "hasTerrace": true,
                                                "hasGarage": false,
                                                "owner": {
                                                    "username": "eantoniutti0",
                                                    "fullName": "Elicia Antoniutti",
                                                    "email": "eantoniutti0@furl.net",
                                                    "phoneNumber": "80132928",
                                                    "numPublications": 6
                                                }
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha ocurrido algún error al crear la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
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

    @Operation(summary = "Edición de una vivienda por su identificador")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DwellingRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                        "name": "Piso Cuqui",
                                        "address": "C/ Sierpes Nº2, Sevilla",
                                        "description": "Piso muy chulo que está muy chulo y sigue siendo chulo. Tiene habitaciones chulas y eso :)",
                                        "image": "",
                                        "type": "CASA",
                                        "price": 563,
                                        "m2": 34,
                                        "numBedrooms": 3,
                                        "numBathrooms": 1,
                                        "hasElevator": true,
                                        "hasPool": false,
                                        "hasTerrace": true,
                                        "hasGarage": false,
                                        "provinceId": 43
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente la vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OneDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 53,
                                                "name": "Piso Cuqui",
                                                "province": "Sevilla",
                                                "image": "",
                                                "price": 563.0,
                                                "address": "C/ Sierpes Nº2, Sevilla",
                                                "description": "Piso muy chulo que está muy chulo y sigue siendo chulo. Tiene habitaciones chulas y eso :)",
                                                "type": "CASA",
                                                "m2": 34.0,
                                                "numBedrooms": 3,
                                                "numBathrooms": 1,
                                                "hasElevator": true,
                                                "hasPool": false,
                                                "hasTerrace": true,
                                                "hasGarage": false,
                                                "owner": {
                                                    "username": "eantoniutti0",
                                                    "fullName": "Elicia Antoniutti",
                                                    "email": "eantoniutti0@furl.net",
                                                    "phoneNumber": "80132928",
                                                    "numPublications": 6
                                                }
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al editar la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda seleccionada",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public OneDwellingResponse editDwelling(@Parameter(description = "Identificador de la vivienda a editar")
                                                @PathVariable Long id, @Valid @RequestBody DwellingRequest dto,
                                                @AuthenticationPrincipal User user) {
        Dwelling edited = dwellingService.editDwelling(id, dto, user);

        return OneDwellingResponse.of(edited);
    }

    @Operation(summary = "Elimina una vivienda por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente la vivienda seleccionada",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "La vivienda que se intenta eliminar no pertenece al usuario",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDwelling(@Parameter(description = "Identificador de la vivienda a eliminar")
                                                @PathVariable Long id, @AuthenticationPrincipal User user) {
        dwellingService.deleteOneDwelling(id, user);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Se añade una vivienda a la lista de favoritos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se añade correctamente la vivienda a la lista de favoritos del usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OneDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 76,
                                                "name": "Casa adosada en Pau 2 - 600",
                                                "province": "Ciudad Real",
                                                "image": "https://robohash.org/atoditest.png?size=50x50&set=set1",
                                                "price": 525.7,
                                                "address": "Puertollano, Ciudad Real",
                                                "description": "Zona con preciosas vistas al parque de los patos. Unifamiliar con dos plantas, mas magnifica planta sotano con chimenea y muebles rusticos de diseÃ±o. Calefaccion individual y aire acondicionado en dormitorio principal. Cochera espaciosa.",
                                                "type": "CASA",
                                                "m2": 183.0,
                                                "numBedrooms": 3,
                                                "numBathrooms": 2,
                                                "hasElevator": false,
                                                "hasPool": false,
                                                "hasTerrace": true,
                                                "hasGarage": true,
                                                "owner": {
                                                    "username": "blahivea",
                                                    "fullName": "Beth Lahive",
                                                    "email": "blahivea@goodreads.com",
                                                    "phoneNumber": "77961241",
                                                    "numPublications": 8
                                                }
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar añadir la vivienda a la lista de favoritos",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @PostMapping("/{id}/favourite")
    public ResponseEntity<OneDwellingResponse> markAsFavourite(@Parameter(description = "Identificador de la vivienda a añadir a favoritos")
                                                                   @PathVariable Long id, @AuthenticationPrincipal User user) {
        Dwelling newFavourite = dwellingService.doFavourite(id, user);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFavourite.getId()).toUri();

        return ResponseEntity.created(createdURI)
                .body(OneDwellingResponse.of(newFavourite));
    }

    @Operation(summary = "Elimina una vivienda de la lista de favoritos del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente la vivienda de la lista de favoritos del usuario",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido un error al intentar eliminar la vivienda de la lista de favoritos del usuario",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @DeleteMapping("/{id}/favourite")
    public ResponseEntity<?> deleteFavourite(@Parameter(description = "Identificador de la vivienda a eliminar de favoritos")
                                                 @PathVariable Long id, @AuthenticationPrincipal User user) {
        dwellingService.deleteFavourite(id, user);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene todas las viviendas paginadas de una provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la lista de viviendas paginadas de una provincia",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllDwellingResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": 90,
                                                        "name": "Piso en San Antonio Abad - Poligono San Anton",
                                                        "province": "Albacete",
                                                        "image": "https://robohash.org/earummagniea.png?size=50x50&set=set1",
                                                        "price": 650.86
                                                    }
                                                ],
                                                "totalElements": 1,
                                                "totalPages": 1,
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
                    description = "No se ha encontrado la provincia indicada",
                    content = @Content)
    })
    @GetMapping("/province/{id}")
    public PageDto<AllDwellingResponse> getDwellingsByProvince(@PathVariable Long id,
                                                               @PageableDefault(size = 20) Pageable pageable) {
        return new PageDto<>(dwellingService.findByProvinceId(id, pageable));
    }

}
