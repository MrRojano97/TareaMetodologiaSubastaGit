
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase principal del programa
 * @author elias
 */
public class Feria {
    
    private List<Articulo> articulos = new ArrayList<>(); //Lista de Artículos que se pueden vender
    private List<Usuario> usuarios = new ArrayList<>(); //Lista de Usuarios
    Scanner teclado = new Scanner(System.in);
    private Usuario usuarioActual; //Usuario que actualmente tiene la sesión iniciada
    private Articulo articulotemporal; //Artículo actual en  consulta
    
    /**
     * Acá se registra un nombre como un usuario del tipo "tipo".
     * @param nombre
     * @param tipo 
     */
    private void registrarAnadir (String nombre, int tipo){
        usuarios.add(new Usuario(nombre, tipo));
    }
    
    private void registrarDatos () {
        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("¿Será Vendedor (0), Comprador (1) u Observador (2)?");
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
        System.out.print("Ingrese precio base del artículo: ");
        double precio = Double.parseDouble((teclado.nextLine()));
        articulos.add(new Articulo (nombre, precio, desc, usuarioActual));
    }
    
    private void listarArticulos(){
        if (articulos.size()>0) {
            for (int x = 0;  x < articulos.size(); x++) {
                System.out.print(x);
                System.out.print(". ");
                System.out.println(articulos.get(x).getNombre());
            }
        }
        else
            System.out.println("No hay artículos para ver aún");
    }
    
    private void infoArticulo(Articulo articulo){
        System.out.println("INFORMACIÓN DEL ARTÍCULO");
        System.out.print("Nombre del artículo: ");
        System.out.println(articulo.getNombre());
        System.out.print("Estado: ");
        articulo.getVendido();
        System.out.print("Descripción del artículo: ");
        System.out.println(articulo.getDescripcion());
        
        
        int ultimaPuja=0;
        System.out.print("Precio más alto: ");
        if (articulo.getPujas().size()>0) {
            ultimaPuja=articulo.getPujas().size()-1;
            System.out.println(articulo.getPujas().get(ultimaPuja).getDinero());
        }
        
        else
            System.out.println(articulo.getPrecioBase());
        
        System.out.print("Vendedor: ");
        System.out.println(articulo.getVendedor().getNombre());
    }
    
    private void listarPujas(Articulo articulo){
        articulo.imprimePujas();
    }
    
    private void opcionesArticulo(Articulo articulo){
        
        boolean salir=false;
        
        while (!salir) {
            System.out.println("Seleccione una opción: ");
            if (usuarioActual.equals(articulo.getVendedor()))
                System.out.println("0. Modificar Articulo");
            
            if (!articulo.isVendido() && usuarioActual.getTipo().equals("Comprador")) {
                System.out.println("1. Ofertar dinero");
            }
            System.out.println("2. Ver pujas del artículo");
            System.out.println("3. Mostrar información del artículo");
            System.out.println("4. Volver atrás");

            System.out.print("Selección: ");
            int opcion = Integer.parseInt(teclado.nextLine());
            
            if (opcion==0) {
                if (usuarioActual.equals(articulo.getVendedor())) {
                    System.out.println("Seleccione una opción: ");
                    System.out.println("0. Establecer Vendido");
                    System.out.println("1. Modificar nombre");
                    System.out.println("2. Modificar descripción");
                    System.out.println("3. Volver atrás");
                    System.out.print("Opcion: ");
                    int opcion1 = Integer.parseInt(teclado.nextLine());
                    
                    if (opcion1==0)
                        articulo.setVendido(true);
                    else if (opcion1==1) {
                        System.out.print("Ingrese nuevo nombre: ");
                        articulo.setNombre((teclado.nextLine()));
                    }
                    else if (opcion==2) {
                        System.out.print("Ingrese nueva descripción: ");
                        articulo.setDescripcion(teclado.nextLine());
                    }
                    
                    else
                        break;
                }
            }
            
            else if (opcion==1)
                articulo.addPuja(ofertar());

            else if (opcion==2)
                listarPujas(articulo);
            
            else if (opcion==3)
                infoArticulo(articulo);
            
            else if (opcion==4)
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
            if (usuarioActual.getTipo().equals("Vendedor"))
                System.out.println("2. Vender un artículo");
            System.out.println("3. Cerrar Sesión");
            System.out.println("4. Cerrar Programa");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(teclado.nextLine());

            if (opcion==1) {
                
                if (articulos.size()>0) {
                    System.out.println("Escriba el número del artículo que desea consultar:");
                    listarArticulos();
                    System.out.print("Selección: ");
                    articulotemporal=articulos.get(Integer.parseInt(teclado.nextLine()));
                    opcionesArticulo(articulotemporal);
                }
                
                else
                    System.out.println("No hay artículos para mostrar");

            }
            else if (opcion==2)
                anadirArticulo();
            
            else if (opcion==3)
                usuarioActual=null;
            
            else if (opcion==4)
                salir=true;
                
        }
    }
}
    

