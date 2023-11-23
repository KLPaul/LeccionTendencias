package ws;

import Modelo.Productos;
import Modelo.Proveedores;
import Modelo.Usuarios;
import Modelo.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "ServicioWS")
public class ServicioWS {

    private List<Usuarios> usuarios = new ArrayList<>();

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "registrarUsuario")
    public boolean registrarUsuario(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "cedula") String cedula,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "contrasena") String contrasena) {

        // Validación de datos (puedes agregar más validaciones según tus necesidades)
        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || direccion.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            return false; // Datos incompletos, el registro falla
        }

        // Verifica si el usuario ya existe (puedes mejorar esto con una búsqueda más eficiente en un entorno de producción)
        for (Usuarios usuarioExistente : usuarios) {
            if (usuarioExistente.getCorreo().equals(correo)) {
                return false; // El correo ya está registrado, el registro falla
            }
        }

        // Crea un nuevo usuario
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setCedula(cedula);
        nuevoUsuario.setDireccion(direccion);
        nuevoUsuario.setNumTelefono(telefono);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContraseña(contrasena);

        // Cifra la contraseña antes de almacenarla
        nuevoUsuario.cifrarContrasena();

        // Agrega el usuario a la lista (en un entorno real, guardar en una base de datos)
        usuarios.add(nuevoUsuario);

        return true;
    }

    // Métodos auxiliares para validaciones y cifrado
    private boolean esValido(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }

    private boolean esCorreoValido(String correo) {
        // Implementa una validación más completa si es necesario
        return esValido(correo) && correo.contains("@");
    }

    private boolean esContrasenaValida(String contrasena) {
        // Implementa una validación más compleja si es necesario
        return esValido(contrasena) && contrasena.length() >= 8;
    }

    private boolean usuarioExistente(String correo) {
        // Verifica si el usuario ya existe (puedes mejorar esto con una búsqueda más eficiente en un entorno de producción)
        for (Usuarios usuarioExistente : usuarios) {
            if (usuarioExistente.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    private List<Productos> invent = new ArrayList<>();
    private List<Proveedores> prov = new ArrayList<>();

    @WebMethod(operationName = "subirProducto")
    public boolean subirProducto(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "categoria") String categoria,
            @WebParam(name = "precioEmprendedor") double precioEmprendedor,
            @WebParam(name = "cantidadDisponible") int cantidadDisponible,
            @WebParam(name = "inventario") Map<String, Integer> inventario) {

        if (!esValido(nombre) || !esValido(descripcion) || !esValido(categoria) || precioEmprendedor <= 0 || cantidadDisponible < 0) {
            return false; // Datos no válidos, la subida falla
        }

        Productos nuevoProducto = new Productos();
        nuevoProducto.setNombrePro(nombre);
        nuevoProducto.setDescripcion(descripcion);
        nuevoProducto.setCategoria(categoria);
        nuevoProducto.setPrecioEmpre(precioEmprendedor);
        nuevoProducto.setCantidadDisponible(cantidadDisponible);
        nuevoProducto.setInventario(inventario);
        invent.add(nuevoProducto);

        return true;
    }

    @WebMethod(operationName = "gestionarCompra")
    public boolean gestionarCompra(
            @WebParam(name = "idProducto") String idProducto,
            @WebParam(name = "idProveedor") String idProveedor,
            @WebParam(name = "porcentajeGanancia") double porcentajeGanancia) {

        Productos prod = buscarProducto(idProducto);
        Proveedores prov = buscarProveedor(idProveedor);

        if (prod == null || prov == null || porcentajeGanancia < 0) {
            return false; // Datos no válidos, la gestión de compra falla
        }
        prod.agregarProveedor(prov);

        double pvp = prod.getPrecioEmpre() * (1 + porcentajeGanancia);

        prod.setPrecioVentaPublico(pvp);

        actualizarProductoEnInventario(prod);
        return true;
    }

    public Productos buscarProducto(String idproducto) {

        for (Productos producto : invent) {
            if (producto.getIdProducto().equals(idproducto)) {
                return producto;
            }
        }
        return null;
    }

    public Proveedores buscarProveedor(String idproveedor) {

        for (Proveedores proveedor : prov) {
            if (proveedor.getIdproveedor().equals(idproveedor)) {
                return proveedor;
            }
        }
        return null;
    }

    public void actualizarProductoEnInventario(Productos producto) {

        Productos productoExistente = buscarProducto(producto.getIdProducto());

        if (productoExistente != null) {
            productoExistente.setNombrePro(producto.getNombrePro());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setCategoria(producto.getCategoria());
            productoExistente.setPrecioEmpre(producto.getPrecioEmpre());
            productoExistente.setPrecioVentaPublico(producto.getPrecioVentaPublico());
            productoExistente.setCantidadDisponible(producto.getCantidadDisponible());
        }

    }
    
    private List<Ventas> listaVentas = new ArrayList<>();


    @WebMethod(operationName = "obtenerVentas")
    public List<Ventas> obtenerVentas() {
        
        return listaVentas; // Devuelve la lista de ventas
    }
    
     @WebMethod(operationName = "calcularIngresos")
     public double calcularIngresosNetosPorEmprendedor(@WebParam(name = "idEmprendedor") String idEmprendedor) {
       
        return 0.0; // Devuelve los ingresos netos
    }
    
}
