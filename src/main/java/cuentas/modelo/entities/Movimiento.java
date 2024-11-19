package cuentas.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="movimientos")
public class Movimiento implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_movimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int idMovimiento;
	private Date fecha;
	private double cantidad;
	private String operacion;
	
	
	@ManyToOne
    @JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;
	
}
