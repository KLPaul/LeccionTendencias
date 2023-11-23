
package Modelo;

public class Compras {
    
    private Proveedores proveedor;
    private Productos producto;
    private double precioVentaPublico;

    public Compras() {
    }

    
    public Compras(Proveedores proveedor, Productos producto, double precioVentaPublico) {
        this.proveedor = proveedor;
        this.producto = producto;
        this.precioVentaPublico = precioVentaPublico;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public double getPrecioVentaPublico() {
        return precioVentaPublico;
    }

    public void setPrecioVentaPublico(double precioVentaPublico) {
        this.precioVentaPublico = precioVentaPublico;
    }
    
    
    
}
