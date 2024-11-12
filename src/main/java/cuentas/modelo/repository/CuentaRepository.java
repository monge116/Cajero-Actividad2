package cuentas.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cuentas.modelo.entities.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{
	
}
