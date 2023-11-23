
package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Productos {
    private String idProducto;
    private String nombrePro;
    private String descripcion;
    private String categoria;
    private double precioEmpre;
    private double precioVentaPublico;
    private int cantidadDisponible;
    private List<Proveedores> proveedores;
    private Map<String, Integer> inventario;

    public Productos() {
    }

    
    
    public Productos(String idProducto, String nombrePro, String descripcion, String categoria, double precioEmpre, double precioVentaPublico, int cantidadDisponible, List<Proveedores> proveedores, Map<String, Integer> inventario) {
        this.idProducto = idProducto;
        this.nombrePro = nombrePro;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioEmpre = precioEmpre;
        this.precioVentaPublico = precioVentaPublico;
        this.cantidadDisponible = cantidadDisponible;
        this.proveedores = proveedores;
        this.inventario = inventario;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioEmpre() {
        return precioEmpre;
    }

    public void setPrecioEmpre(double precioEmpre) {
        this.precioEmpre = precioEmpre;
    }

    public double getPrecioVentaPublico() {
        return precioVentaPublico;
    }

    public void setPrecioVentaPublico(double precioVentaPublico) {
        this.precioVentaPublico = precioVentaPublico;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public List<Proveedores> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedores> proveedores) {
        this.proveedores = proveedores;
    }

    public Map<String, Integer> getInventario() {
        return inventario;
    }

    public void setInventario(Map<String, Integer> inventario) {
        this.inventario = inventario;
    }
    
    public void agregarProveedor(Proveedores proveedor) {
        if (proveedores == null) {
            proveedores = new ArrayList<>();
        }
        proveedores.add(proveedor);
    }

    
    
    
    
    
    
    
    
}
