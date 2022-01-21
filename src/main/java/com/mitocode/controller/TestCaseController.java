package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
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

import com.mitocode.dto.TestCaseDTO;
import com.mitocode.dto.TestCaseObservationDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.TestCase;
import com.mitocode.model.Observation;
import com.mitocode.service.ITestCaseService;


@RestController
@RequestMapping("/testcases")
public class TestCaseController {

    @Autowired
    private ITestCaseService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TestCaseDTO>> list() throws Exception {
        List<TestCaseDTO> list = service.list().stream().map(p -> mapper.map(p, TestCaseDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCaseDTO> listForId(@PathVariable("id") Integer id) throws Exception {
        TestCaseDTO dtoResponse;
        TestCase obj = service.listForId(id); //Testcase

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        } else {
            dtoResponse = mapper.map(obj, TestCaseDTO.class); //TestCaseDTO
        }

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody TestCaseObservationDTO dtoRequest) throws Exception {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        TestCase testcase = mapper.map(dtoRequest, TestCase.class);
        List<Observation> examenes = mapper.map(dtoRequest.getLstObservation(), new TypeToken<List<Observation>>() {
        }.getType());

        TestCase obj = service.registerTransactional(testcase, examenes);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTestcase()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<TestCaseDTO> update(@RequestBody TestCaseDTO dtoRequest) throws Exception {
        TestCase pac = service.listForId(dtoRequest.getIdTestcase());

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND  " + dtoRequest.getIdTestcase());
        }

        TestCase p = mapper.map(dtoRequest, TestCase.class);

        TestCase obj = service.update(p);

        TestCaseDTO dtoResponse = mapper.map(obj, TestCaseDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        TestCase pac = service.listForId(id);

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND  " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<TestCaseDTO> listHateoasForId(@PathVariable("id") Integer id) throws Exception {
        TestCase obj = service.listForId(id);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        TestCaseDTO dto = mapper.map(obj, TestCaseDTO.class);

        EntityModel<TestCaseDTO> resource = EntityModel.of(dto);
        //localhost:8080/testcases/1
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listForId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listHateoasForId(id));
        resource.add(link1.withRel("testcase-resource 1"));
        resource.add(link2.withRel("testcase-resource 2"));

        return resource;
    }
}
