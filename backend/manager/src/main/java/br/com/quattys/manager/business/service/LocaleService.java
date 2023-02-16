package br.com.quattys.manager.business.service;

import br.com.quattys.manager.application.dto.request.LocaleRequest;
import br.com.quattys.manager.application.dto.response.LocaleResponse;
import br.com.quattys.manager.application.dto.table.LocaleTableResponse;


public interface LocaleService {

    LocaleResponse saveLocale(LocaleRequest localeRequest);
    LocaleResponse updateLocale(Long id, LocaleRequest localeRequest);
    void deleteLocaleById(Long id);
    LocaleResponse getLocaleById(Long id);
    LocaleTableResponse getLocaleByCity(String city);
}
