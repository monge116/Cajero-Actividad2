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
	public String home(Model model) {
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
	        
	        return "cuenta";
	    } else {
	        
	        return "redirect:/login";
	    }
		
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion (HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.getId();
		sesion.invalidate();
		return "forward:/";
	}
	
	
	
	
	
	
}
