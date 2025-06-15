package com.smartcity.parking.PK4U.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LevelInfoDTO {
    private String levelId;
    private String levelName;
    private int spotsTotal;

    // Lo tendremos que calcular en el backend según la info de la base de datos
    private int spotsFree;
}
