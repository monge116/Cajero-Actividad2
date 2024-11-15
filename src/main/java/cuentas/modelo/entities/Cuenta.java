package cuentas.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="id_cuenta")
	@Id
	private int idCuenta;
	private double saldo;
	@Column(name="tipo_cuenta")
	private String tipoCuenta;
	
	
	
	public void ingresar(double importe) {
		saldo+=importe;
	}
	public boolean extraer(double importe) {
		if(importe>saldo) {
			return false;
		}
		saldo-=importe;
		return true;
	}
	public boolean transferir(Cuenta cuentaDestino , double importe) {
		if(this.extraer(importe)) {
			cuentaDestino.ingresar(importe);
			return true;
		}
		return false;
	}
	
}
