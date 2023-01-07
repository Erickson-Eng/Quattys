package br.com.quattys.sportive.application.controller;

import br.com.quattys.sportive.application.dto.request.AddressRequest;
import br.com.quattys.sportive.application.dto.response.AddressResponse;
import br.com.quattys.sportive.application.dto.table.AddressTableRequest;
import br.com.quattys.sportive.application.dto.table.AddressTableResponse;
import br.com.quattys.sportive.business.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/sportive/api/v1/address")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest addressRequest){
        AddressResponse addressResponse = addressService.saveAddress(addressRequest);
        URI uri = URI.create(addressResponse.getExternalId());
        return ResponseEntity.created(uri).body(addressResponse);

    }

    @PostMapping("/save-in-batch")
    public ResponseEntity<AddressTableResponse> saveAddressInBatch(@RequestBody AddressTableRequest addressTableRequest){
        AddressTableResponse addressTableResponse = addressService.saveAddressesInBatch(addressTableRequest);

        return ResponseEntity.ok().body(addressTableResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable Long id){
        AddressResponse addressResponse = addressService.update(id, addressRequest);
        return ResponseEntity.ok().body(addressResponse);
    }

    @PutMapping("/update-by-external-id/{id}")
    public ResponseEntity<AddressResponse> updateAddressByExternalId(@RequestBody AddressRequest addressRequest, @PathVariable String id){
        AddressResponse addressResponse = addressService.updateByExternalId(id, addressRequest);
        return ResponseEntity.ok().body(addressResponse);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id){
        AddressResponse addressResponse = addressService.getAddressById(id);
        return ResponseEntity.ok().body(addressResponse);
    }

    @GetMapping("/get-by-external-id/{externalId}")
    public ResponseEntity<AddressResponse> getAddressByExternalId(@PathVariable String externalId){
        AddressResponse addressResponse = addressService.getAddressByExternalId(externalId);
        return ResponseEntity.ok().body(addressResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id){
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-by-externalId/{externalId}")
    public ResponseEntity<Void> deleteByExternalId(@PathVariable String externalId){
        addressService.deleteByExternalId(externalId);
        return ResponseEntity.noContent().build();
    }
}
