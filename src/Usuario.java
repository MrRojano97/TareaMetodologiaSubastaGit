/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elias
 */
public class Usuario {
    
    private double gastosTotales = 0;
    private String nombre;
    private int cantidadComprados = 0;
    private int tipo;

    public Usuario(String nombre, int tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public double getGastosTotales() {
        return gastosTotales;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadComprados() {
        return cantidadComprados;
    }

    public String getTipo() {
        if (tipo==0)
            return "Vendedor";
        else if (tipo==1)
            return "Comprador";
        return "Observador";
    }
    
    public void addGastosTotales(double aumenta) {
        gastosTotales+=aumenta;
    }
    
    public void increaseCantidadComprados() {
        cantidadComprados++;
    }
    
    
}
