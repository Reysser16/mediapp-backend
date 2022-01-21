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

import com.mitocode.dto.ConditionDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Condition;
import com.mitocode.service.IConditionService;


@RestController
@RequestMapping("/conditions")
public class ConditionController {

    @Autowired
    private IConditionService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ConditionDTO>> list() throws Exception {
        List<ConditionDTO> list = service.list().stream().map(p -> mapper.map(p, ConditionDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionDTO> listForId(@PathVariable("id") Integer id) throws Exception {
        ConditionDTO dtoResponse;
        Condition obj = service.listForId(id); //Condition

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        } else {
            dtoResponse = mapper.map(obj, ConditionDTO.class); //ConditionDTO
        }

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody ConditionDTO dtoRequest) throws Exception {
        Condition p = mapper.map(dtoRequest, Condition.class);
        Condition obj = service.register(p);
        ConditionDTO dtoResponse = mapper.map(obj, ConditionDTO.class);
        //localhost:8080/conditions/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCondition()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ConditionDTO> update(@RequestBody ConditionDTO dtoRequest) throws Exception {
        Condition pac = service.listForId(dtoRequest.getIdCondition());

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + dtoRequest.getIdCondition());
        }

        Condition p = mapper.map(dtoRequest, Condition.class);

        Condition obj = service.update(p);

        ConditionDTO dtoResponse = mapper.map(obj, ConditionDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Condition pac = service.listForId(id);

        if (pac == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ConditionDTO> listHateoasForId(@PathVariable("id") Integer id) throws Exception {
        Condition obj = service.listForId(id);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }

        ConditionDTO dto = mapper.map(obj, ConditionDTO.class);

        EntityModel<ConditionDTO> resource = EntityModel.of(dto);
        //localhost:8080/conditions/1
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listForId(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listHateoasForId(id));
        resource.add(link1.withRel("condition-resource 1"));
        resource.add(link2.withRel("condition-resource 2"));

        return resource;
    }
}
