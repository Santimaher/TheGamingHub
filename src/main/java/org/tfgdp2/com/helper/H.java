
package org.tfgdp2.com.helper;

import javax.servlet.http.HttpSession;

import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;

public class H {
	/**
	 * 
	 * @param rol Tres posibilidades "anon", "auth", "admin"
	 * @param s   la sesión activa
	 * @throws DangerException si el rol no coincide con el del usuario activo
	 */
	public static void isRolOK(String rol, HttpSession s) throws DangerException {
		Usuario usuario = null;

		if (s.getAttribute("persona") != null) {
			usuario = (Usuario) s.getAttribute("usuario");
		}

		// Ya sé quién ha hecho login, y si alguien lo ha hecho
		
		if (usuario == null) { // anon
			if (rol != "anon") {
				PRG.error("Rol inadecuado");
			}
		} else { // Auth o admin
			if (!usuario.getRol().equals("admin") && rol.equals("admin")) { // anon
				PRG.error("Rol inadecuado");
			}
		}

	}
}
