package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ObservationDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Observation;
import com.mitocode.service.IObservationService;


@RestController
@RequestMapping("/observations")
public class ObservationController {

    @Autowired
    private IObservationService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ObservationDTO>> list() throws Exception {
        List<ObservationDTO> list = service.list().stream().map(p -> mapper.map(p, ObservationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservationDTO> listForId(@PathVariable("id") Integer id) throws Exception {
        ObservationDTO dtoResponse;
        Observation obj = service.listForId(id); //Observation

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        } else {
            dtoResponse = mapper.map(obj, ObservationDTO.class); //ObservationDTO
        }

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ObservationDTO dtoRequest) throws Exception {
        Observation p = mapper.map(dtoRequest, Observation.class);
        Observation obj = service.register(p);
        ObservationDTO dtoResponse = mapper.map(obj, ObservationDTO.class);
        //localhost:8080/observations/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdObservation()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ObservationDTO> update(@RequestBody ObservationDTO dtoRequest) throws Exception {
        Observation pac = service.listForId(dtoRequest.getIdObservation());

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + dtoRequest.getIdObservation());
        }

        Observation p = mapper.map(dtoRequest, Observation.class);

        Observation obj = service.update(p);

        ObservationDTO dtoResponse = mapper.map(obj, ObservationDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Observation pac = service.listForId(id);

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ObservationDTO> listHateoasForId(@PathVariable("id") Integer id) throws Exception {
        Observation obj = service.listForId(id);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        ObservationDTO dto = mapper.map(obj, ObservationDTO.class);

        EntityModel<ObservationDTO> resource = EntityModel.of(dto);
        //localhost:8080/observations/1
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listForId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listHateoasForId(id));
        resource.add(link1.withRel("observation-resource 1"));
        resource.add(link2.withRel("observation-resource 2"));

        return resource;
    }
}
