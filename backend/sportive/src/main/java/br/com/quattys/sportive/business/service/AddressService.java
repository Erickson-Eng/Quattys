package br.com.quattys.sportive.business.service;

import br.com.quattys.sportive.application.dto.request.AddressRequest;
import br.com.quattys.sportive.application.dto.response.AddressResponse;
import br.com.quattys.sportive.application.dto.table.AddressTableRequest;
import br.com.quattys.sportive.application.dto.table.AddressTableResponse;

public interface AddressService {

    AddressResponse saveAddress(AddressRequest addressRequest);
    AddressResponse update(Long id, AddressRequest addressRequest);
    AddressResponse getAddressById(Long id);
    void deleteById(Long id);

    AddressTableResponse saveAddressesInBatch(AddressTableRequest addressTableRequest);
    AddressResponse updateByExternalId(String externalId, AddressRequest addressRequest);
    AddressResponse getAddressByExternalId(String externalId);
    void deleteByExternalId(String externalId);

}
