package br.com.quattys.manager.application.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymFilter {

    private String name;
    private String city;
    private String state;
    private String sport;
    private String accessibility;
}
