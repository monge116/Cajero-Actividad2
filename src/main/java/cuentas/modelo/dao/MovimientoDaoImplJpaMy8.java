package cuentas.modelo.dao;

import java.util.List;

import cuentas.modelo.entities.Movimiento;
import cuentas.modelo.repository.MovimientoRepository;

public class MovimientoDaoImplJpaMy8 implements MovimientoDao{
	
	private MovimientoRepository mrepo;
	
	@Override
	public Movimiento findById(int idMovimiento) {
		// TODO Auto-generated method stub
		return mrepo.findById(idMovimiento).orElse(null);
	}

	@Override
	public List<Movimiento> findAll() {
		// TODO Auto-generated method stub
		return mrepo.findAll();
	}

	@Override
	public int insertOne(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return (mrepo.save(movimiento) != null) ? 1 : 0;
	}

	@Override
	public int updateOne(Movimiento movimiento) {
		if (mrepo.existsById(movimiento.getIdMovimiento())) {
			mrepo.save(movimiento);
			return 1;
		}
		return 0;
	}

}
