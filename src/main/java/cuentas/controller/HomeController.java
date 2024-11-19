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
	
	@GetMapping({"/", "", "/home"})
	public String home(HttpSession sesion, Model model) {
		sesion.removeAttribute("cuenta");
		sesion.getId();
		sesion.invalidate();
	
		return "home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@GetMapping("/")
	//public String cerrarSesion (HttpSession sesion) {
		//sesion.removeAttribute("cuenta");
		//sesion.getId();
		//sesion.invalidate();
		//return "forward:/";
	//}
	
	
	
	
	
	
}
