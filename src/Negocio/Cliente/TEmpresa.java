package Negocio.Cliente;

public class TEmpresa extends TCliente {
	private String paginaWeb;
	
	public TEmpresa(String nombre, String telefono, int id, String nif, String paginaWeb, boolean activo) {
		super(nombre, id, telefono, activo, nif);
		this.paginaWeb = paginaWeb;
	}
	
	public TEmpresa(String nombre, String telefono, String nif, String paginaWeb) {
		super(nombre, telefono, nif);
		this.paginaWeb = paginaWeb;
	}
	
	public String getPaginaWeb() {
		return paginaWeb;
	}
	
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

}
