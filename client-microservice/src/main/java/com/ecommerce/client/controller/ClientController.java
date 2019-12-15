package com.ecommerce.client.controller;

import com.ecommerce.client.requestDTO.ClientRequestDTO;
import com.ecommerce.client.requestDTO.RegisterRequestDTO;
import com.ecommerce.client.responseDTO.ResponseDTO;
import com.ecommerce.client.service.ClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ecommerce.client.constants.WebResourceKeyConstants.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = BASE_API)
@Api(value = "This is client controller")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = SAVE)
    @ApiOperation(value = "Save new client")
    @ResponseBody
    public ResponseEntity<?> saveClient(@RequestBody RegisterRequestDTO requestDTO) {
        clientService.saveClient(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = SEARCH)
    @ApiOperation(value = "Search client by given parameters in request")
    public ResponseEntity<?> searchClient(@ApiParam(value = "Parameter value is client username")
                                         @RequestBody ClientRequestDTO requestDTO) {
        return ok().body(clientService.searchClient(requestDTO));
    }

    @PostMapping(value = UPDATE)
    @ApiOperation(value = "Update client information")
    public ResponseEntity<?> updateClient(@RequestBody ClientRequestDTO requestDTO) {
        return ok().body(clientService.updateClient(requestDTO));
    }


    @GetMapping(value = FETCH_CLIENT_BY_USERNAME)
    public ResponseEntity fetchClientByUsername(@PathVariable("username") String username) {
        return ok().body(clientService.fetchClientByUsername(username));
    }

    @GetMapping("/clients")
    public ResponseEntity<ResponseDTO> getClientsToSendEmail() {
        return new ResponseEntity<>(clientService.clientsToSendEmails(), HttpStatus.OK);
    }
}
