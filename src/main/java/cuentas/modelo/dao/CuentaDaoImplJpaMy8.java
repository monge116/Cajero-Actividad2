package cuentas.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cuentas.modelo.entities.Cuenta;
import cuentas.modelo.repository.CuentaRepository;

public class CuentaDaoImplJpaMy8 implements CuentaDao {
	
	
	@Autowired
	private CuentaRepository crepo;
	
	@Override
	public Cuenta findById(int idCuenta) {
		
		return crepo.findById(idCuenta).orElse(null);
	}

	@Override
	public int updateOne(Cuenta cuenta) {
	
		if (crepo.existsById(cuenta.getIdCuenta())) {
			crepo.save(cuenta);
			return 1;
		}
		return 0;
	}

}
