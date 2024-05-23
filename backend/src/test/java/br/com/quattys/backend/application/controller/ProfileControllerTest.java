package br.com.quattys.backend.application.controller;

import br.com.quattys.backend.application.dto.request.AddressRequest;
import br.com.quattys.backend.application.dto.request.ProfileRequest;
import br.com.quattys.backend.domain.entity.Address;
import br.com.quattys.backend.domain.entity.Profile;
import br.com.quattys.backend.infrastructure.persitence.ProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setMockMvc(){

        Address address1 = new Address(1L, "123 Main St", "Apt 101", "Springfield", "IL", "12345");
        Address address2 = new Address(2L, "456 Elm St", "Unit 202", "Rivertown", "NY", "54321");

        Profile profile1 = new Profile(1L,"Erickson Tulio", LocalDate.now(), "Masculino", "206.380.192-49", address1);
        Profile profile2 = new Profile(2L, "erickSon Azevedo", LocalDate.of(1980, 2, 2), "Masculino", "592.862.554-54", address2);

        profileRepository.saveAll(Arrays.asList(profile1, profile2));
    }


    @Test
    void getProfilesByNameContainingIgnoreCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/profile")
                        .param("name", "Erickson")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Erickson Tulio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].name").value("erickSon Azevedo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(2))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void createProfile() throws Exception {

        AddressRequest homeAddress = new AddressRequest("Antonio Cirilo Gomes", null,null, null, "58415-563");
        ProfileRequest profileRequest = new ProfileRequest("Wenderson Azevedo", "1990-05-19", "Masculino", "221.671.483-64", homeAddress);

        String jsonRequest = objectMapper.writeValueAsString(profileRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(profileRequest.name()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateProfile() throws Exception {

        AddressRequest homeAddress = new AddressRequest("Antonio Cirilo Gomes", null,null, null, "58415-563");
        ProfileRequest profileRequest = new ProfileRequest("Wenderson Azevedo", "1990-05-19", "Masculino", "221.671.483-64", homeAddress);

        var profile = profileRepository.findById(1L);
        String actualName = profile.isPresent() ? profile.get().getName() : "Wenderson Azevedo";

        Assertions.assertNotNull(profile);

        String jsonRequest = objectMapper.writeValueAsString(profileRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/profile/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        var updatedProfile = profileRepository.findById(1L);

        Assertions.assertTrue(updatedProfile.isPresent(), "O perfil não foi encontrado");

        updatedProfile.ifPresent(p -> {
            Assertions.assertNotEquals(actualName, p.getName());
            Assertions.assertEquals(profileRequest.name(),updatedProfile.get().getName());
        });



    }

}