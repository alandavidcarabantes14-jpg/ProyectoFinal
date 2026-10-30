/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;
/**
 *
 * @author Alan
 */
public class proceso {
    
   public static void procesoMenu() {
       //  Scanner
        Scanner sc = new Scanner(System.in);
        
        // creacion de variables 
        int opcion = 0;
        int cantidadProd = 0;
        int formaPago = 0;
        int totalProdVendidos = 0;
        int salirMenu = 0;      
        int ventasEfectivo = 0;
        int ventasTarjeta = 0;
        int ventasTransferencia = 0;
        int totalVentas = 0;       
        double ventaMayor = 0;
        double ventaMenor = 0;
        double descPorcentaje = 0;
        double montoDescuento = 0;
        double montoIva = 0;
        double totalPagar = 0;
        double efectivoRecibido = 0;
        double cambioVuelto = 0;
        double cajaTotal = 0;
        boolean primeraVenta = true;
        double precioUnitario = 0;
        double subtotalItem = 0;

        // Menú principal con do-while
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar una venta");
            System.out.println("2. Consultar caja");
            System.out.println("3. Mostrar estadisticas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            
            //registrar una venta
            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR VENTA ---");
                    System.out.print("¿Cuantos productos diferentes comprara?: ");
                    cantidadProd = sc.nextInt();
                    
                    while (cantidadProd <= 0) {
                        System.out.print("Error. Ingrese un numero mayor a cero: ");
                        cantidadProd = sc.nextInt();
                    }
                    
                    double subtotalCompra = 0;
                    int itemsVenta = 0;
                    
                    for (int i = 1; i <= cantidadProd; i++) {
                        System.out.println("\nProducto " + i);
                        System.out.print("Codigo del producto: ");
                        String codigo = sc.next();
                        
                        // colocar el nombre del producto
                        sc.nextLine(); 
                        System.out.print("Nombre del producto: ");
                        String nombre = sc.nextLine();
                        if (nombre.isEmpty()) {
                            nombre = sc.nextLine();
                        }
                        // agregar el precio del producto pot unidad
                        System.out.print("Precio unitario (Q): ");
                        precioUnitario = sc.nextDouble();
                        
                        while (precioUnitario <= 0) {
                            System.out.print("El precio debe ser mayor a 0. Ingrese de nuevo: Q");
                            precioUnitario = sc.nextDouble();
                        }
                        
                        // cantidad del producto a comprar
                        System.out.print("Cantidad: ");
                        int cantidad = sc.nextInt();
                        
                        while (cantidad <= 0) {
                            System.out.print("La cantidad debe ser mayor a 0. Ingrese de nuevo: ");
                            cantidad = sc.nextInt();
                        }
                        
                        subtotalItem = precioUnitario * cantidad;
                        System.out.println("Subtotal de " + nombre + ": Q" + subtotalItem);
                        
                        subtotalCompra = subtotalCompra + subtotalItem;
                        itemsVenta = itemsVenta + cantidad;
                    }
                    
                    // Descuentos por rangos
                    if (subtotalCompra < 200) {
                        descPorcentaje = 0;
                    } else if (subtotalCompra < 500) {
                        descPorcentaje = 0.05;
                    } else if (subtotalCompra < 1000) {
                        descPorcentaje = 0.08;
                    } else {
                        descPorcentaje = 0.10;
                    }
                    
                    montoDescuento = subtotalCompra * descPorcentaje;
                    double subtotalRebajado = subtotalCompra - montoDescuento;
                    montoIva = subtotalRebajado * 0.12;
                    totalPagar = subtotalRebajado + montoIva;
                    
                    //elegir la opcion de pago
                    System.out.println("\nForma de pago:");
                    System.out.println("1. Efectivo");
                    System.out.println("2. Tarjeta");
                    System.out.println("3. Transferencia");
                    System.out.print("Elija una opcion: ");
                    formaPago = sc.nextInt();
                    
                    while (formaPago < 1 || formaPago > 3) {
                        System.out.print("Opcion invalida. Elija 1, 2 o 3: ");
                        formaPago = sc.nextInt();
                    }
                    
                    String tipoPagoTxt = "";
                    efectivoRecibido = 0;
                    cambioVuelto = 0;
                    
                    switch (formaPago) {
                        case 1:
                            tipoPagoTxt = "Efectivo";
                            System.out.print("Efectivo recibido (Q): ");
                            efectivoRecibido = sc.nextDouble();
                            
             while (efectivoRecibido < totalPagar) {
        System.out.print("Dinero insuficiente. El total es Q" + totalPagar + ". Ingrese de nuevo: Q");
                                efectivoRecibido = sc.nextDouble();
                            }
                            cambioVuelto = efectivoRecibido - totalPagar;
                            ventasEfectivo++;
                            break;
                            
                        case 2:
                            tipoPagoTxt = "Tarjeta";
                            ventasTarjeta++;
                            break;
                            
                        case 3:
                            tipoPagoTxt = "Transferencia";
                            ventasTransferencia++;
                            break;
                    }
                    
                    totalVentas++;
                    totalProdVendidos = totalProdVendidos + itemsVenta;
                    cajaTotal = cajaTotal + totalPagar;
                    
                    if (primeraVenta) {
                        ventaMayor = totalPagar;
                        ventaMenor = totalPagar;
                        primeraVenta = false;
                    } else {
                        if (totalPagar > ventaMayor) {
                            ventaMayor = totalPagar;
                        }
                        if (totalPagar < ventaMenor) {
                            ventaMenor = totalPagar;
                        }
                    }
                    
                    System.out.println("\n*** RESUMEN DE VENTA ***");
                    System.out.println("Productos: " + itemsVenta);
                    System.out.println("Subtotal: Q" + subtotalCompra);
                    System.out.println("Descuento: Q" + montoDescuento);
                    System.out.println("IVA (12%): Q" + montoIva);
                    System.out.println("TOTAL: Q" + totalPagar);
                    if (formaPago == 1) {
                        System.out.println("Efectivo: Q" + efectivoRecibido);
                        System.out.println("Cambio: Q" + cambioVuelto);
                    }
                    System.out.println("************************");
                    break;
                    
                    //  consultar caja    
                case 2:
                    System.out.println("\n--- CAJA ---");
                    System.out.println("Ventas realizadas: " + totalVentas);
                    System.out.println("Productos vendidos: " + totalProdVendidos);
                    System.out.println("Dinero total: Q" + cajaTotal);
                    System.out.println("Efectivo: " + ventasEfectivo);
                    System.out.println("Tarjeta: " + ventasTarjeta);
                    System.out.println("Transferencia: " + ventasTransferencia);
                    break;
                   
                    // mostrar estadisticas
                case 3:
                    System.out.println("\n--- ESTADISTICAS ---");
                    if (totalVentas == 0) {
                        System.out.println("No existen ventas registradas.");
                    } else {
                        double promedio = cajaTotal / totalVentas;
                        System.out.println("Total ventas: " + totalVentas);
                        System.out.println("Productos: " + totalProdVendidos);
                        System.out.println("Dinero total: Q" + cajaTotal);
                        System.out.println("Promedio: Q" + promedio);
                        System.out.println("Efectivo: " + ventasEfectivo);
                        System.out.println("Tarjeta: " + ventasTarjeta);
                        System.out.println("Transferencia: " + ventasTransferencia);
                        System.out.println("Mayor valor: Q" + ventaMayor);
                        System.out.println("Menor valor: Q" + ventaMenor);
                    }
                    break;
                    
                case 4:
                    System.out.print("¿Desea salir? (1 = Si, 2 = No): ");
                    int confirmar = sc.nextInt();
                    while (confirmar != 1 && confirmar != 2) {
                        System.out.print("Opcion invalida. Ingrese 1 o 2: ");
                        confirmar = sc.nextInt();
                    }
                    if (confirmar == 1) {
                        salirMenu = 1;
                        System.out.println("Saliendo del programa...");
                    }
                    break;
                    
                default:
                    System.out.println("Opcion invalida. Elija del 1 al 4.");
            }
            
        } while (salirMenu == 0);
        
      
        
        
    }
}
