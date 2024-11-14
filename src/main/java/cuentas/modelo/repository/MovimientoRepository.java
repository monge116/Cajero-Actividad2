package cuentas.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cuentas.modelo.entities.Movimiento;


public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	@Query(value = "SELECT * FROM movimientos WHERE id_cuenta = ?1 ORDER BY fecha DESC LIMIT 3", nativeQuery = true)
	public List<Movimiento> findByIdCuenta(int IdCuenta);
}
