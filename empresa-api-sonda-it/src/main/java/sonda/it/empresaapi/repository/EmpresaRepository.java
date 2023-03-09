package sonda.it.empresaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonda.it.empresaapi.entity.Empresa;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> findAllByOrderByNameAsc();

}
