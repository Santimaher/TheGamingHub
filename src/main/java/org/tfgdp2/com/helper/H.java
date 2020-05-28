
package org.tfgdp2.com.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.hibernate.Hibernate;
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
		String user = "ftpgp2";
		String pass = "losnuggets45";
		FTPClient con = null;
	    String fileExtension =  imagen.getOriginalFilename().split("\\.")[1];
		
	    try {
	        con = new FTPClient();
	        con.connect(server);

	        if (con.login(user, pass)) {
	            con.enterLocalPassiveMode(); // important!
	            con.setFileType(FTP.BINARY_FILE_TYPE);

	            boolean result = con.storeFile(u.getLoginname()+"#"+u.getId()+"."+fileExtension, imagen.getInputStream());
	            con.logout();
	            con.disconnect();
	            
	        }
	    } catch (Exception e) {
	    	PRG.error("Fallo al subir la imagen"+e.getMessage());
	    }
	}
	public static Blob blobCreator(MultipartFile imagen) throws IOException, SerialException, SQLException {
		
		    byte[] bytes = imagen.getBytes();
		    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		    
		return blob;
		
	}
}
