package br.com.quattys.sportive.business.service.postgresql;

import br.com.quattys.sportive.application.dto.mapper.AddressMapper;
import br.com.quattys.sportive.application.dto.request.AddressRequest;
import br.com.quattys.sportive.application.dto.response.AddressResponse;
import br.com.quattys.sportive.application.dto.table.AddressTableRequest;
import br.com.quattys.sportive.application.dto.table.AddressTableResponse;
import br.com.quattys.sportive.business.entity.Address;
import br.com.quattys.sportive.business.service.AddressService;
import br.com.quattys.sportive.business.service.exception.DatabaseViolationException;
import br.com.quattys.sportive.business.service.exception.EntityNotFoundException;
import br.com.quattys.sportive.resource.repository.AddressRepository;
import br.com.quattys.sportive.resource.repository.AthleteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServicePostgreSQL implements AddressService {
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    @Transient
    @Override
    public AddressResponse saveAddress(AddressRequest addressRequest) {
        try {
            Address address = addressMapper.requestToAddress(addressRequest);
            Address entity = addressRepository.save(address);

            return addressMapper.addressToResponse(entity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseViolationException("Database error", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public AddressTableResponse saveAddressesInBatch(AddressTableRequest addressTableRequest) {
        List<Address> addressList = convertAddressTableRequestInEntityList(addressTableRequest);
        List<Address> entityList = addressRepository.saveAll(addressList);
        return AddressTableResponse
                .builder()
                .addressResponses(convertEntityToAddressTableResponse(entityList))
                .build();
    }

    @Override
    public AddressResponse updateByExternalId(String externalId, AddressRequest addressRequest) {
        Address address = verifyIfExist(externalId);
        addressMapper.updateAddressFromRequest(addressRequest, address);
        address = addressRepository.save(address);
        return addressMapper.addressToResponse(address);
    }

    @Override
    public AddressResponse update(Long id, AddressRequest addressRequest) {
        Address address = verifyIfExist(id);
        addressMapper.updateAddressFromRequest(addressRequest, address);
        address = addressRepository.save(address);
        return addressMapper.addressToResponse(address);
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        Address address = verifyIfExist(id);
        return addressMapper.addressToResponse(address);
    }

    @Override
    public AddressResponse getAddressByExternalId(String externalId) {
        Address address = verifyIfExist(externalId);
        return addressMapper.addressToResponse(address);
    }

    @Override
    public void deleteById(Long id) {
        Address address = verifyIfExist(id);
        addressRepository.delete(address);
    }

    @Override
    public void deleteByExternalId(String externalId) {
        Address address = verifyIfExist(externalId);
        addressRepository.delete(address);
    }

    private List<Address> convertAddressTableRequestInEntityList(AddressTableRequest addressTableRequest){
        return addressTableRequest.getAddressRequests()
                .stream()
                .map(addressMapper::requestToAddress)
                .collect(Collectors.toList());
    }

    private List<AddressResponse> convertEntityToAddressTableResponse(List<Address> addressList){
        return addressList.stream()
                .map(addressMapper::addressToResponse)
                .collect(Collectors.toList());
    }

    private Address verifyIfExist(Long id){
        return addressRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Address not registered", HttpStatus.NOT_FOUND));
    }

    private Address verifyIfExist(String externalId){
        UUID uuid = UUID.fromString(externalId);
        return addressRepository.findAddressByExternalId(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Address not registered", HttpStatus.NOT_FOUND));
    }
}
