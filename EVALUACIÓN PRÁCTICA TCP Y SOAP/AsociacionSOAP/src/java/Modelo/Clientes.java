
package Modelo;

public class Clientes extends Usuarios{
    
    private String idclientes;

    public Clientes(String idclientes) {
        this.idclientes = idclientes;
    }

    public Clientes(String idclientes, String nombre, String apellido, String cedula, String direccion, String numTelefono, String correo, String contraseña) {
        super(nombre, apellido, cedula, direccion, numTelefono, correo, contraseña);
        this.idclientes = idclientes;
    }

    public String getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(String idclientes) {
        this.idclientes = idclientes;
    }
    
    
    
}
