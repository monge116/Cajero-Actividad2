package cuentas.modelo.dao;

import cuentas.modelo.entities.Cuenta;

public interface CuentaDao {
	Cuenta findById (int idCuenta);
	int updateOne(Cuenta cuenta);
	
}
