package repositorios;

import Entidades.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();
        System.out.println(" -----------PROBAMOS EL SISTEMA ----------");
        Pais argentina = Pais.builder().nombre("Argentina").
                build();


        Provincia Mendoza = Provincia.builder()
                .id (1L)
                .nombre("Mendoza")
                .pais(argentina)
                .build();


        Localidad GodoyCruz = Localidad.builder()
                .id(1L)
                .nombre ("GodoyCruz")
                .provincia (Mendoza)
                .build();

        Domicilio domicilio1 = Domicilio.builder()
                .id(1L)
                .calle("San Martin")
                .numero (500)
                .cp (510)
                .localidad (GodoyCruz)
                .build();

        Localidad Ciudad = Localidad.builder()
                .id(2L)
                .nombre("Ciudad")
                .provincia (Mendoza)
                .build();

        Domicilio domicilio2 = Domicilio.builder()
                .id(2L)
                .calle("La Plata")
                .numero(1600)
                .cp(520)
                .localidad(Ciudad)
                .build();

        Provincia Cordoba = Provincia.builder()
                .id(3L)
                .nombre ("Cordoba")
                .pais (argentina)
                .build();

        Localidad CordobaCapítal = Localidad.builder()
                .id(3L)
                .nombre ("Cordoba Capital")
                .provincia (Cordoba)
                .build();

        Domicilio domicilio3 = Domicilio.builder()
                .id(3L)
                .calle("Cruz Conde")
                .numero (500)
                .cp (510)
                .localidad (CordobaCapítal)
                .build();

        Localidad VillaMaria = Localidad.builder()
                .id(4L)
                .nombre ("VillaMaria")
                .provincia (Cordoba)
                .build();

        Domicilio domicilio4 = Domicilio.builder()
                .id(4L)
                .calle("Callejón de la Cruz")
                .numero (500)
                .cp (510)
                .localidad (VillaMaria)
                .build();

            //Sucursales de Mendoza

        Sucursal changomas = Sucursal.builder()
                .id(1L)
                .Nombre("ChangoMas")
                .horarioApertura(LocalTime.of(10,0))
                .horarioCierre(LocalTime.of(18,0))
                .domicilio(domicilio1)
                .es_Casa_Matriz(false)
                .build();

        Sucursal vea = Sucursal.builder()
                .id(2L)
                .Nombre("Vea")
                .horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(21,0))
                .domicilio(domicilio2)
                .es_Casa_Matriz(true)
                .build();

        //Sucursles de Cordoba

        Sucursal arcor = Sucursal.builder()
                .id(3L)
                .Nombre("Arcor")
                .horarioApertura(LocalTime.of(10,0))
                .horarioCierre(LocalTime.of(18,0))
                .domicilio(domicilio3)
                .es_Casa_Matriz(true)
                .build();

        Sucursal naranja = Sucursal.builder()
                .id(4L)
                .Nombre("Naranja")
                .horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(18,0))
                .domicilio(domicilio3)
                .es_Casa_Matriz(false)
                .build();

        Empresa empresa1 = Empresa.builder()
                .Nombre("ChangoMas")
                .RazonSocial("Razon Social 1")
                .cuil(12345678901L)
                .sucursales(new HashSet<>(Set.of(changomas)))
                .logo("Mas")
                .build();


        Empresa empresa2 = Empresa.builder()
                .Nombre("Vea")
                .RazonSocial("Razon Social 2")
                .cuil(79876543232L)
                .sucursales(new HashSet<>(Set.of(vea)))
                .logo("Vea")
                .build();


        Empresa empresa3 = Empresa.builder()
                .Nombre("Arcor")
                .RazonSocial("Razon Social 3")
                .cuil(67890123453L)
                .sucursales(new HashSet<>(Set.of(arcor)))
                .logo("Arcor")
                .build();


        Empresa empresa4 = Empresa.builder()
                .Nombre("Naranja")
                .RazonSocial("Razon Social 4")
                .cuil(12345098764L)
                .sucursales(new HashSet<>(Set.of(naranja)))
                .logo("X")
                .build();

        //Le asignamos a las empresas una sucursal
        changomas.setEmpresa(empresa1);
        vea.setEmpresa(empresa2);
        arcor.setEmpresa(empresa3);
        naranja.setEmpresa(empresa4);

        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);
        empresaRepository.save(empresa3);
        empresaRepository.save(empresa4);

        System.out.println("Todas las empresas: ");
        List<Empresa> TodasLasEmpresas = empresaRepository.findAll();
        TodasLasEmpresas.forEach(System.out::println);

        Optional<Empresa> empresaEncontrada = empresaRepository.findById(1L);
        empresaEncontrada.ifPresent(e -> System.out.println("Empresa encontrada por ID 1: " + e));

        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("nombre", "ChangoMas");
        System.out.println("Empresas con nombre 'ChangoMas':");
        empresasPorNombre.forEach(System.out::println);
        

        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .Nombre("ChangoMas Actualizada")
                .RazonSocial("Razon Social 1 Actualizada")
                .cuil(1234561L)
                .sucursales(empresa1.getSucursales())
                .logo("Mas , Actualizada")
                .build();

        empresaRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("Empresa después de la actualización: " + e));

        // Eliminar empresa por ID
        empresaRepository.genericDelete(1L);
        Optional<Empresa> empresaEliminada = empresaRepository.findById(1L);
        if (empresaEliminada.isEmpty()) {
            System.out.println("La empresa con ID 1 ha sido eliminada.");
        }

        // Mostrar todas las empresas restantes
        System.out.println("Todas las empresas después de la eliminación:");
        List<Empresa> empresasRestantes = empresaRepository.findAll();
        empresasRestantes.forEach(System.out::println);
    }
}

