package sonda.it.empresaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sonda.it.empresaapi.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
