package Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder

public class Domicilio {
    private long id ;
    private String calle ;
    private Integer numero ;
    private Integer casa ;
    private Integer piso ;
    private Integer cp ;

    private Localidad localidad ;

}
