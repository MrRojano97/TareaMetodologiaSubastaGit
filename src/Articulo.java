
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elias
 */
public class Articulo {
    private String nombre;
    private double precioBase;
    private boolean vendido;
    private String descripcion;
    private Usuario vendedor;
    private List<Puja> pujas = new ArrayList<>();

    public Articulo(String nombre, double precioBase, String descripcion, Usuario vendedor) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.descripcion = descripcion;
        this.vendedor = vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean isVendido() {
        return vendido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void imprimePujas() {
        for (int x = 0;  x < pujas.size(); x++) {
            System.out.print(x);
            System.out.print(". ");
            System.out.print(pujas.get(x).getDinero());
            System.out.println(" ; ");
            System.out.println(pujas.get(x).getUsuario());
        }
    }
    
    public void addPuja(Puja puja){
        pujas.add(puja);
    }

    public List<Puja> getPujas() {
        return pujas;
    }
    
    
    
    
    
}
