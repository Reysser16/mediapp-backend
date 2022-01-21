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

import com.mitocode.dto.TesterDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Tester;
import com.mitocode.service.ITesterService;


@RestController
@RequestMapping("/testers")
public class TesterController {

    @Autowired
    private ITesterService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TesterDTO>> list() throws Exception {
        List<TesterDTO> list = service.list().stream().map(p -> mapper.map(p, TesterDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TesterDTO> listForId(@PathVariable("id") Integer id) throws Exception {
        TesterDTO dtoResponse;
        Tester obj = service.listForId(id); //Tester

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        } else {
            dtoResponse = mapper.map(obj, TesterDTO.class); //TesterDTO
        }

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody TesterDTO dtoRequest) throws Exception {
        Tester p = mapper.map(dtoRequest, Tester.class);
        Tester obj = service.register(p);
        TesterDTO dtoResponse = mapper.map(obj, TesterDTO.class);
        //localhost:8080/testers/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTester()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<TesterDTO> update(@RequestBody TesterDTO dtoRequest) throws Exception {
        Tester pac = service.listForId(dtoRequest.getIdTester());

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + dtoRequest.getIdTester());
        }

        Tester p = mapper.map(dtoRequest, Tester.class);

        Tester obj = service.update(p);

        TesterDTO dtoResponse = mapper.map(obj, TesterDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Tester pac = service.listForId(id);

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<TesterDTO> listHateoasForId(@PathVariable("id") Integer id) throws Exception {
        Tester obj = service.listForId(id);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        TesterDTO dto = mapper.map(obj, TesterDTO.class);

        EntityModel<TesterDTO> resource = EntityModel.of(dto);
        //localhost:8080/testers/1
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listForId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listHateoasForId(id));
        resource.add(link1.withRel("tester-resource 1"));
        resource.add(link2.withRel("tester-resource 2"));

        return resource;
    }
}
