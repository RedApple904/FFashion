/**
 * Trần Thảo
 */
package fashion.mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fashion.mock.model.User;
import fashion.mock.service.BCryptPasswordUtility;
import fashion.mock.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class AuthController {

	private final UserService userService;
	private final BCryptPasswordUtility bCryptPasswordUtility;

	public AuthController(UserService userService, BCryptPasswordUtility bCryptPasswordUtility) {
		this.userService = userService;
		this.bCryptPasswordUtility = bCryptPasswordUtility;
	}

	@GetMapping("/loginform")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/loginsuccess")
	public String loginSuccess(@ModelAttribute User user, Model model, HttpSession session) {

		User userFromDB = userService.findUserByEmail(user.getEmail());
		
		if (!userService.getEmail(user.getEmail())) {
			model.addAttribute("errorEmail", "Email chưa được đăng ký!");
			return "login";
		}

		boolean checkPassword = !bCryptPasswordUtility.doPasswordMatch(user.getPassword(), userFromDB.getPassword());
		if (checkPassword) {
			model.addAttribute("errorPassword", "Mật khẩu không chính xác!");
			return "login";
		}

		if (userFromDB.getStatus() == null || !userFromDB.getStatus().equalsIgnoreCase("ACTIVE")) {
			model.addAttribute("errorStatus", "Tài khoản này chưa được active!");
			return "login";
		}
		session.setAttribute("user", userFromDB);
		return "redirect:/home";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/home";
	}

}
