
package Modelo;

public class IngresoEgreso {
    
    private double ingresos;
    private double egresos;

    public IngresoEgreso() {
    }
    
    public IngresoEgreso(double ingresos, double egresos) {
        this.ingresos = ingresos;
        this.egresos = egresos;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getEgresos() {
        return egresos;
    }

    public void setEgresos(double egresos) {
        this.egresos = egresos;
    }
    
    public double calcularIngresosNetos() {
        return ingresos - egresos;
    }
    
    
}
