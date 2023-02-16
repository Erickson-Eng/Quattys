package br.com.quattys.manager.business.service;

import br.com.quattys.manager.application.dto.request.GymRequest;
import br.com.quattys.manager.application.dto.response.GymResponse;

public interface GymService {

    GymResponse saveGym(GymRequest gymRequest);
    GymResponse updateGym(Long id, GymRequest gymRequest);
    void deleteGymById(Long id);

}
