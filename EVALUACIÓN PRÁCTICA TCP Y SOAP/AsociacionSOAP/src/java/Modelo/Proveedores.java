
package Modelo;

public class Proveedores{
    
    private String idproveedor;
    private String nombrePro;

    public Proveedores() {
    }

    
    
    public Proveedores(String idproveedor, String nombrePro) {
        this.idproveedor = idproveedor;
        this.nombrePro = nombrePro;
    }

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }
    
    

 
    
}
