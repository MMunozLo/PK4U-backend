package com.smartcity.parking.PK4U.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LevelInfoDTO {
    private int levelId;
    private String levelName;
    private int spotsTotal;

    // Lo tendremos que calcular en el backend según la info de la base de datos
    private int spotsFree;
}
