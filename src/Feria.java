
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
    
    public void iniciarSesion (Usuario usuario) {
        this.usuarioActual=usuario;
    }
    
    public void registrarAnadir (String nombre, int tipo){
        usuarios.add(new Usuario(nombre, tipo));
    }
    
    public void registrarDatos () {
        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("¿Será vendedor (0) o comprador (1)?");
        int tipo = Integer.parseInt(teclado.nextLine());
        
        registrarAnadir (nombre, tipo);
    }
    
    public void inicio () {
        registrarAnadir("La Roca", 1);
        registrarAnadir("Elvitek", 0);
        System.out.println("Bienvenido a la Feria de Pujas!\nPor favor seleccione una opción:");
        
        while (usuarioActual==null) {
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
    }
}
    

