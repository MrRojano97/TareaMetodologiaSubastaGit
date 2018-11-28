
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elias
 */
public class Feria {
    
    private List<Articulo> articulos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    private Usuario usuarioActual;
    
    private void iniciarSesion (Usuario usuario) {
        this.usuarioActual=usuario;
    }
    
    private void registrarAnadir (String nombre, int tipo){
        usuarios.add(new Usuario(nombre, tipo));
    }
    
    private void registrarDatos () {
        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("¿Será vendedor (0) o comprador (1)?");
        int tipo = Integer.parseInt(teclado.nextLine());
        
        registrarAnadir (nombre, tipo);
    }
    
    private Puja ofertar(){
        System.out.println("Ingrese dinero a ofertar: ");
        System.out.print("Dinero: ");
        return new Puja (Double.parseDouble((teclado.nextLine())), usuarioActual);
    }
    
    private void anadirArticulo(){
        System.out.print("Ingrese Nombre del artículo: ");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese descripción del artículo: ");
        String desc = teclado.nextLine();
        System.out.print("Ingres precio base del artículo: ");
        double precio = Double.parseDouble((teclado.nextLine()));
        articulos.add(new Articulo (nombre, precio, desc, usuarioActual));
    }
    
    private void listarArticulos(){
        for (int x = 0;  x < articulos.size(); x++) {
            System.out.print(x);
            System.out.print(". ");
            System.out.println(articulos.get(x).getNombre());
        }
    }
    
    private void infoArticulo(Articulo articulo){
        System.out.println(articulo.getNombre());
        System.out.println(articulo.getDescripcion());
        System.out.println(articulo.getPrecioBase());
        System.out.println(articulo.getVendedor());
        
        int ultimaPuja=0;
        System.out.print("Precio más alto: ");
        if (articulo.getPujas().size()>=0) {
            ultimaPuja=articulo.getPujas().size()-1;
            System.out.println(articulo.getPujas().get(ultimaPuja).getDinero());
        }
        
        else
            System.out.println(articulo.getPrecioBase());
    }
    
    private void listarPujas(Articulo articulo){
        articulo.imprimePujas();
    }
    
    private void opcionesArticulo(Articulo articulo){
        
        boolean salir=false;
        
        while (!salir) {
            if (usuarioActual.equals(articulo.getVendedor()))
                System.out.println("0. Modificar Articulo");

            System.out.println("1. Ofertar dinero");
            System.out.println("2. Ver pujas actuales");
            System.out.println("3. Volver atrás");

            int opcion = Integer.parseInt(teclado.nextLine());

            if (opcion==1)
                articulo.addPuja(ofertar());

            else if (opcion==2)
                listarPujas(articulo);
            
            else if (opcion==3)
                salir=true;
        
        }

    }
    
    private void iniciaRegistra(){
        System.out.println("Por favor seleccione una opción:");
        System.out.println("Iniciar Sesión (1)");
        System.out.println("Registrar Usuario Nuevo (2)");
        System.out.print("Selección: ");
        int opcion = Integer.parseInt(teclado.nextLine());

        if (opcion==1) {
            System.out.println("Seleccione al usuario que desea utilizar: ");
            for (int x = 0;  x < usuarios.size(); x++) {
                System.out.print(x);
                System.out.print(". ");
                System.out.println(usuarios.get(x).getNombre());
                }
            System.out.println("Opción: (ingrese número de Usuario)");;
            System.out.print("Selección: ");
            int seleccionUsuario = Integer.parseInt(teclado.nextLine());
            usuarioActual=usuarios.get(seleccionUsuario);

            System.out.println("Se ha seleccionado el usuario: ");
            System.out.print(usuarioActual.getNombre());
            System.out.print(", ");
            System.out.println(usuarioActual.getTipo());

            }
        if (opcion==2) {
            registrarDatos();
        }
    }
    
    public void inicio () {
        registrarAnadir("La Roca", 1);
        registrarAnadir("Elvitek", 0);
        boolean salir=false;
        System.out.println("Bienvenido a la Feria de Pujas!");
        
        while (!salir) {

            while (usuarioActual==null) {
                iniciaRegistra();
            }
            
            System.out.println("Selecciona una opción: ");
            System.out.println("1. Mostrar artículos disponibles");
            System.out.println("2. Vender un artículo");
            
            int opcion = Integer.parseInt(teclado.nextLine());
            
            if (opcion==1) {
                listarArticulos();
                
            }
            else if (opcion==2)
                anadirArticulo();
                
        }
    }
}
    

