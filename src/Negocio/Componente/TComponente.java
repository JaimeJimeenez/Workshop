package Negocio.Componente;

public class TComponente {
	private int id;
	private float precio;
	private String modelo;
	private String marca;
	private int stock;
	private boolean activo;
	private int idProveedor;
	
	public TComponente(int id, int idProveedor, String marca, float precio, String modelo, int stock, boolean activo) {
		this.id = id;
		this.idProveedor = idProveedor;
		this.precio = precio;
		this.modelo = modelo;
		this.marca = marca;
		this.stock = stock;
		this.activo = activo;
	}
	
	public TComponente(int id, int idProveedor, String marca, float precio, String modelo, int stock) {
		this.id = id;
		this.idProveedor = idProveedor;
		this.precio = precio;
		this.modelo = modelo;
		this.marca = marca;
		this.stock = stock;
	}
	
	public TComponente(String marca, int idProveedor,  float precio, String modelo, int stock) {
		this.idProveedor = idProveedor;
		this.precio = precio;
		this.modelo = modelo;
		this.marca = marca;
		this.stock = stock;
		activo = true;
	}
	
	public TComponente(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
