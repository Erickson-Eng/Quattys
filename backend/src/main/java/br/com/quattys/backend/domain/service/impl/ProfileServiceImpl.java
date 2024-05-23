package br.com.quattys.backend.domain.service.impl;

import br.com.quattys.backend.domain.entity.Address;
import br.com.quattys.backend.domain.entity.Profile;
import br.com.quattys.backend.domain.service.ProfileService;
import br.com.quattys.backend.infrastructure.persitence.AddressRepository;
import br.com.quattys.backend.infrastructure.persitence.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class ProfileServiceImpl implements ProfileService {


    private ProfileRepository profileRepository;
    private AddressRepository addressRepository;

    @Override
    public Profile createProfile(Profile profile) {
        var address = profile.getHomeAddress();
        if (address != null){
            address = addressRepository.save(profile.getHomeAddress());
        }
        profile.setHomeAddress(address);
        return profileRepository.save(profile);
    }

    @Override
    public void updateProfile(Long id, Profile profile) {
        Optional<Profile> optionalEntityProfile = profileRepository.findById(id);
        optionalEntityProfile.ifPresent(entity -> {
            BeanUtils.copyProperties(profile, entity, "id", "cpf", "homeAddress");
            updateProfileAddress(profile.getHomeAddress(), entity.getHomeAddress());
            profileRepository.save(entity);
        });
    }

    private void updateProfileAddress(Address addressRequest, Address entity) {
        BeanUtils.copyProperties(addressRequest, entity, "id");
        addressRepository.save(entity);
    }

    @Override
    public List<Profile> getProfileByName(String name) {
        var profiles = profileRepository.findByNameContainingIgnoreCase(name);
        if (profiles.isEmpty()){
            return List.of();
        }
        return profiles;
    }

    @Override
    public Page<Profile> getProfileByNamePageable(String name, Pageable pageable) {
        return profileRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
