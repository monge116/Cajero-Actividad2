package cuentas.modelo.dao;

import java.util.List;

import cuentas.modelo.entities.Movimiento;


public interface MovimientoDao {
	Movimiento findById (int idMovimiento);
	List<Movimiento> findAll();
	List<Movimiento> findByIdCuenta(int idCuenta);
	int insertOne(Movimiento movimiento);
	int updateOne(Movimiento movimiento);
	// int deleteOne(Movimiento movimiento);
}
