
package org.tfgdp2.com.helper;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;
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

		if (s.getAttribute("usuario") != null) {
			usuario = (Usuario) s.getAttribute("usuario");
		}

		// Ya sé quién ha hecho login, y si alguien lo ha hecho
		
		if (usuario == null) { // anon
			if (rol != "anon") {
				PRG.error("Rol inadecuado");
			}
		} else { // Auth o admin o jurado
			if (!usuario.getRol().equals("admin") && rol.equals("admin")) { // admin
				PRG.error("Rol inadecuado");
			}
			if (!usuario.getRol().equals("jurado") && rol.equals("jurado")) { // jurado
				PRG.error("Rol inadecuado");
			}
		}

	}
	
	public static void subirImagen(Usuario u,MultipartFile imagen) throws DangerException {
		String server = "ftp.site4now.net";
		int port= 21;
		String user = "grupodosrfvi-001";
		String pass = "losnuggets45";
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.changeWorkingDirectory("/tgh");
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			InputStream is = imagen.getInputStream();
			boolean result = ftpClient.storeFile(u.getLoginname()+".png", is);
			is.close();
			
			if(result) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (Exception e) {
			PRG.error("Fallo al subir la imagen"+e.getMessage());
		}
	}
}
