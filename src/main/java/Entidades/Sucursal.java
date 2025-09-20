package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "empresa")
@SuperBuilder
public class Sucursal {
    private long id;
    private String Nombre ;
    private LocalTime horarioApertura ;
    private LocalTime horarioCierre ;
    private boolean es_Casa_Matriz ;

    private Domicilio domicilio ;

    private Empresa empresa ;
}
