package cuentas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cuentas.modelo.dao.CuentaDao;
import cuentas.modelo.dao.MovimientoDao;
import cuentas.modelo.entities.Cuenta;
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
	public String procesarLogin(@RequestParam int idCuenta, HttpSession sesion, RedirectAttributes ratt) {
		Cuenta cuenta = cdao.findById(idCuenta);
		if(cuenta != null) { 
			sesion.setAttribute("cuenta", cuenta);
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
	
	@GetMapping("/cuenta")
	public String cuenta(Model model) {
		model.addAttribute("movimiento", mdao.findAll());
		return "cuenta";
	}
	
	
	
	
	
}
