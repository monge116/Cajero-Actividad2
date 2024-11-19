package cuentas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cuentas.modelo.dao.CuentaDao;
import cuentas.modelo.dao.MovimientoDao;
import cuentas.modelo.entities.Cuenta;
import cuentas.modelo.entities.Movimiento;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private CuentaDao cdao;
	
	@Autowired
	private MovimientoDao mdao;
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String procesarLogin(@RequestParam int idCuenta, HttpSession sesion, RedirectAttributes ratt, Model model) {
		
		Cuenta cuenta = cdao.findById(idCuenta);
		
		if(cuenta != null) { 
			sesion.setAttribute("cuenta", cuenta);
			//List<Movimiento> lista = mdao.findByIdCuenta(idCuenta);
			// model.addAttribute("movimiento", lista);
			// ratt.addFlashAttribute("usuario2", usuario.getNombre());
			return "redirect:/cuenta";
		}else {
			ratt.addFlashAttribute("mensaje", "usuario p password incorecto");
			return "redirect:/login";
		}
		
	}
	
	@GetMapping({"/", "", "/home"})
	public String home(HttpSession sesion, Model model) {
		sesion.removeAttribute("cuenta");
		sesion.getId();
		sesion.invalidate();
	
		return "home";
	}
	
	@PostMapping("/cuenta")
	public String cuenta() {
		return "cuenta";
	}
	
	@GetMapping("/cuenta")
	public String mostrarMov(HttpSession sesion, Model model) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		if (cuenta != null) {
	
	        List<Movimiento> movimientos = mdao.findByIdCuenta(cuenta.getIdCuenta());
	        
	       
	        model.addAttribute("movimientos", movimientos);
	        model.addAttribute("cuenta", cuenta);
	        model.addAttribute("verMas", true);
	        
	        return "cuenta";
	    } else {
	        
	        return "redirect:/login";
	    }
		
	}
	
	@GetMapping("/verMas")
	public String mostrarMasMov(HttpSession sesion, Model model) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		if (cuenta != null) {
	
	        List<Movimiento> movimientos = mdao.findByAllIdCuenta(cuenta.getIdCuenta());
	        
	       
	        model.addAttribute("movimientos", movimientos);
	        model.addAttribute("cuenta", cuenta);
	        model.addAttribute("verMas", false);
	        
	        return "cuenta";
	    } else {
	        
	        return "redirect:/login";
	    }
		
	}
	
	@PostMapping("/extraer")
	public String extraer (HttpSession sesion, Model model, @RequestParam double cantidad, RedirectAttributes ratt) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		
		
		
		if(cuenta.extraer(cantidad)) {
			
		    cdao.updateOne(cuenta);
		    
		    Movimiento movimiento= new Movimiento();
			movimiento.setCuenta(cuenta);
			movimiento.setCantidad(cantidad);
			movimiento.setFecha(new java.util.Date());
			movimiento.setOperacion("Retiro");
			
			mdao.insertOne(movimiento);
			
			
		    
		    
		}else {
			ratt.addFlashAttribute("error", "Fondos insuficientes.");
	        
		}
		
		
        model.addAttribute("cuenta", cuenta);
		return "redirect:/cuenta";
		
	}
	
	@PostMapping("/ingresar")
	public String ingresar (HttpSession sesion, Model model, @RequestParam double cantidad, RedirectAttributes ratt) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		
		
		
		cuenta.ingresar(cantidad);
			
		    cdao.updateOne(cuenta);
		    
		    Movimiento movimiento= new Movimiento();
			movimiento.setCuenta(cuenta);
			movimiento.setCantidad(cantidad);
			movimiento.setFecha(new java.util.Date());
			movimiento.setOperacion("Ingreso");
			
			mdao.insertOne(movimiento);

        model.addAttribute("cuenta", cuenta);
		return "redirect:/cuenta";
		
	}
	
	@PostMapping("/transferir")
	public String transferir (HttpSession sesion, Model model, @RequestParam double cantidad, @RequestParam int idCuentaDest, RedirectAttributes ratt) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		Cuenta destinatario = cdao.findById(idCuentaDest);
		
		
		if (cuenta.transferir(destinatario, cantidad)) {
			
			cdao.updateOne(cuenta);
			cdao.updateOne(destinatario);
			
			Movimiento movimiento= new Movimiento();
			movimiento.setCuenta(cuenta);
			movimiento.setCantidad(cantidad);
			movimiento.setFecha(new java.util.Date());
			movimiento.setOperacion("Retiro");
			
			mdao.insertOne(movimiento);
			
			Movimiento movimientoDestinatario= new Movimiento();
			movimientoDestinatario.setCuenta(destinatario);
			movimientoDestinatario.setCantidad(cantidad);
			movimientoDestinatario.setFecha(new java.util.Date());
			movimientoDestinatario.setOperacion("Ingreso");
			
			mdao.insertOne(movimientoDestinatario);
		}else {
			ratt.addFlashAttribute("error", "Fondos insuficientes.");
	        
		};
			
		    
		    
		    

        model.addAttribute("cuenta", cuenta);
        
		return "redirect:/cuenta";
		
	}
	
	//@GetMapping("/")
	//public String cerrarSesion (HttpSession sesion) {
		//sesion.removeAttribute("cuenta");
		//sesion.getId();
		//sesion.invalidate();
		//return "forward:/";
	//}
	
	
	
	
	
	
}
