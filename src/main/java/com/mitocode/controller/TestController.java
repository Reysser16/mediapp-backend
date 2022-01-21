package com.mitocode.controller;

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

import com.mitocode.dto.TestDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Test;
import com.mitocode.service.ITestService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private ITestService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TestDTO>> list() throws Exception {
        List<TestDTO> list = service.list().stream().map(p -> mapper.map(p, TestDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> listForId(@PathVariable("id") Integer id) throws Exception {
        TestDTO dtoResponse;
        Test obj = service.listForId(id); //Test

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        } else {
            dtoResponse = mapper.map(obj, TestDTO.class); //TestDTO
        }

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody TestDTO dtoRequest) throws Exception {
        Test p = mapper.map(dtoRequest, Test.class);
        Test obj = service.register(p);
        TestDTO dtoResponse = mapper.map(obj, TestDTO.class);
        //localhost:8080/test/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTest()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<TestDTO> update(@RequestBody TestDTO dtoRequest) throws Exception {
        Test pac = service.listForId(dtoRequest.getIdTest());

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + dtoRequest.getIdTest());
        }

        Test p = mapper.map(dtoRequest, Test.class);

        Test obj = service.update(p);

        TestDTO dtoResponse = mapper.map(obj, TestDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Test pac = service.listForId(id);

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<TestDTO> listHateoasForId(@PathVariable("id") Integer id) throws Exception {
        Test obj = service.listForId(id);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        TestDTO dto = mapper.map(obj, TestDTO.class);

        EntityModel<TestDTO> resource = EntityModel.of(dto);
        //localhost:8080/test/1
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listForId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listHateoasForId(id));
        resource.add(link1.withRel("test-resource 1"));
        resource.add(link2.withRel("test-resource 2"));

        return resource;
    }
}
