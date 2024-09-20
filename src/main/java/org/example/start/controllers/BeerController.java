package org.example.start.controllers;

import jakarta.validation.Valid;

import org.example.start.exceptions.DuplicateEntityException;
import org.example.start.exceptions.EntityNotFoundException;
import org.example.start.exceptions.UnauthorizedOperationException;
import org.example.start.model.Beer;
import org.example.start.model.BeerDTO;
import org.example.start.model.BeerDTOout;
import org.example.start.model.User;
import org.example.start.service.BeerService;
import org.example.start.service.mappers.BeerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beers")
public class BeerController {
    private final BeerService beerService;
    private final AuthenticationHelper authenticationHelper;
    private final BeerMapper beerMapper;

    @Autowired
    public BeerController(BeerService beerService, AuthenticationHelper authenticationHelper, BeerMapper beerMapper) {
        this.beerService = beerService;

        this.authenticationHelper = authenticationHelper;
        this.beerMapper = beerMapper;
    }

    @GetMapping
    public List<Beer> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String styleName,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder
    ) {


        return beerService.getAll(name,styleName,sortBy,sortOrder);
    }

    @GetMapping("/{id}")
    public Beer getBeerById(@PathVariable int id) {

        try {
            return beerService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
    @GetMapping("/normal/{id}")
    public BeerDTOout getBeerForNormalUser(@PathVariable int id) {

        try {
            Beer beer = beerService.getById(id);
            return beerMapper.objectToDto(beer);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @PostMapping
    public Beer create(@RequestHeader HttpHeaders headers, @Valid @RequestBody BeerDTO beerDTO) {
        try {
            Beer beer=beerMapper.dtoToObject(beerDTO);
            User user=authenticationHelper.tryGetUser(headers);
            beerService.create(beer,user);
            return beer;
        } catch (DuplicateEntityException e) {
            // Handle the exception
           throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }


    }


    @PutMapping ("/{id}")
    public Beer update(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody Beer beer) {
        try {
            User user=authenticationHelper.tryGetUser(headers);
            beerService.update(beer,user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (UnauthorizedOperationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        return beer;
    }

@DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user=authenticationHelper.tryGetUser(headers);
            beerService.delete(id,user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorizedOperationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
}



}
