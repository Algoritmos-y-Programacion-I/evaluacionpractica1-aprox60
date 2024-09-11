package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+ calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+ calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+ calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+ consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }
    /**
     * Descripcion: Este metodo se encarga de solicitar al usuario el precio de cada una de sus referencias
     * y la cantidad por unidades de cada una de sus referencias
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Se guardó información sobre los arreglos precios y unidades
     * @param double precios debe ser un numero
     * @param int unidades debe ser un numero entero
     */

    public static void solicitarDatos(){
        System.out.println("Porfavor ingresa el precio de cada una de las referencias de tus productos");
        double suma = 0;
        for (int i = 0; i < precios.length; i++) {
            System.out.println("Ingrese el precio de la referencia " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();
            suma += precios[i];
        }
      
        

        System.out.println("Porfavor ingresa la cantidad por unidades de cada una de las referencias de tus productos");
        suma = 0;
        for (int i = 0; i < unidades.length; i++) {
            System.out.println("Ingrese la cantidad de unidades de la referencia " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
            suma += unidades[i];
        }

        
    }

     /**
     * Descripcion: Este metodo se encarga de calcular el total de unidades vendidas
     * de acuerdo a cada referencia 
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo unidades debe estar declarado
     * pre: la variable total debe estar inicializada
     * pos: Se guardó información sobre el arreglo unidades
     * @return int total de unidades vendidas
     */ 
    public static int calcularTotalUnidadesVendidas(){
        int total = 0;
        for (int i=0; i < unidades.length; i++){
            total+= unidades[i];          
        }

        return total;

    }
     /**
     * Descripcion: Este metodo se encarga de calcular el precio promedio
     * correspondiente a cada referencia
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar declarado
     * pos: Se calculó el precio promedio y se guardó
     * @return double promedio de precios segun referencias vendidas
     */ 

    public static double calcularPrecioPromedio(){
      double suma = 0;
      for(int i = 0; i < precios.length; i++){
        suma+= precios[i];
        
      }
      return precios.length> 0 ?
        suma / precios.length : 0;
    
    }
    /**
     * Descripcion: Este metodo se encarga de calcular las ventas totales
     * correspondientes a todas las referencias
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar declarado
     * pre: la variable igual debe estar inicializada
     * pos: Se calculó el total de ventas y se guardó
     * @return int total de ventas
     */ 
    public static double calcularVentasTotales(){
        int igual = 0;
        for (int i=0; i < precios.length; i++){
            igual+= precios[i];          
        }

        return igual;  

    }
     /**
     * Descripcion: Este metodo se encarga de consultar las referencias que superan
     * el limite de ganancias establecido por el usuario
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar declarado
     * pos: Se encuentra la cantidad de referencias que superaron el limite
     * @param double limite debe ser un numero
     * @return int contador cantidad de productos que superaron el limite de ventas
     */ 
    public static int consultarReferenciasSobreLimite(double limite){
        int contador = 0;
        for (int i=0; i< unidades.length; i++){
            double ventasPorReferencia= unidades[i] * precios[i];
            if (ventasPorReferencia > limite){
                contador++;
            } 
        }
          return contador;
    }

}


