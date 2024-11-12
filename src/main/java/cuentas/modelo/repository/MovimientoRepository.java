package cuentas.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cuentas.modelo.entities.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{

}
