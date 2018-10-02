package habitacion;

import java.util.Scanner;

public class Habitacion {

    static Scanner scaner = new Scanner(System.in);
    
    //datos
    static double[] paredConstante = new double[20];
    static double[] paredAncho = new double[20];
    static double[] paredAlto = new double[20];
    static double[] paredProfundidad = new double[20];
    static int[] cosaCantidad = new int[20];
    static double[] cosaConstante = new double[20];
    static double[] cosaAncho = new double[20];
    static double[] cosaAlto = new double[20];
    static double[] cosaProfundidad = new double[20];
    static int[] cosaPadre = new int[20];
    static double[] fueraTemperatura = new double[20];
    static int dentroTemperatura = 20;
    static int editando = 0;
    
    //resultados
    static double[] superficiePared = new double[20];
    static double[] superficieCosa = new double[20];
    static double[] cedidasParcialesJ = new double[40];
    static double[] cedidasParcialesW = new double[40];
    static double cedidaTotalesJ;
    static double cedidaTotalesW;
    
    
    
    public static void main(String[] args) {
        
        //show logo
        System.out.println("||---------------\\**/-|Perdidas de habitación|-\\**/---------------||");
        System.out.println("||----------------\\**********/-|B0vE|-\\**********/---------------||\n\n");
        menu();
        
    }
    
    private static void menu() {
        
        //show menu
        System.out.print("--> Menu de opciones <--:\n"
                + "1) Añadir una pared\n"
                + "2) Añadir una discontinuidad\n"
                + "3) Definir temperatura interna\n"
                + "4) Calcular\n"
                + "Introduce tu acción: ");
       
        //read and act by option unputted
        switch(scaner.nextInt()){
            case 1:
                añadirPared();
                break;
            case 2:
                añadirCosa();
                break;
            case 3:
                definirTemperatura();
                break;
            case 4:
                calcular();
                break;
            default:
                menu();
                break;
        }
    }
    
    private static void añadirPared(){
        System.out.println("\n--|Añadir una pared|--");
        System.out.print("->ID de la pared (1-20): ");
        editando = scaner.nextInt()-1;
        System.out.println("Se ha seleccionado la pared "+Integer.toString(editando+1));
        System.out.print("->Constante del material (J/m2h): ");
        paredConstante[editando] = scaner.nextDouble();
        System.out.println("La constante del material es de "+Double.toString(paredConstante[editando])+" J/m2h");
        System.out.print("->Ancho de la pared (m): ");
        paredAncho[editando] = scaner.nextDouble();
        System.out.println("El ancho de la pared es de "+Double.toString(paredAncho[editando])+" m");
        System.out.print("->Alto de la pared (m): ");
        paredAlto[editando] = scaner.nextDouble();
        System.out.println("La altura de la pared es de "+Double.toString(paredAlto[editando])+" m");
        System.out.print("->Profundidad de la pared (m): ");
        paredProfundidad[editando] = scaner.nextDouble();
        System.out.println("La profundidad de la pared es de "+Double.toString(paredProfundidad[editando])+" m2");
        System.out.print("->Temperatira fuera de la pared (ºC): ");
        fueraTemperatura[editando] = scaner.nextDouble();
        System.out.println("Temperatura fuera de la pared es de "+Double.toString(fueraTemperatura[editando])+" ºC");
        System.out.println("--|Pared añadida|--\n");
        menu();
        
    }
    
    private static void añadirCosa(){
        System.out.println("\n--|Añadir una discontinuidad|--");
        System.out.print("->ID de la discontinuidad (1-20): ");
        editando = scaner.nextInt()-1;
        System.out.println("Se ha seleccionado la discontinuidad "+Integer.toString(editando+1));
        System.out.print("->Constante de material (J/m2h): ");
        cosaConstante[editando] = scaner.nextDouble();
        System.out.println("La constante del material es de "+Double.toString(cosaConstante[editando])+" J/m2h");
        System.out.print("->Ancho de la discontinuidad (m): ");
        cosaAncho[editando] = scaner.nextDouble();
        System.out.println("El ancho de la discontinuidad es de "+Double.toString(cosaAncho[editando])+" m");
        System.out.print("->Alto de la discontinuidad (m): ");
        cosaAlto[editando] = scaner.nextDouble();
        System.out.println("La altura de la discontinuidad es de "+Double.toString(cosaAlto[editando])+" m");
        System.out.print("->Profundidad de la discontinuidad (m): ");
        cosaProfundidad[editando] = scaner.nextDouble();
        System.out.println("La profundidad de la discontinuidad es de "+Double.toString(cosaProfundidad[editando])+" m2");
        System.out.print("->Pared en la que se encuentra (1-20): ");
        cosaPadre[editando] = scaner.nextInt()-1;
        System.out.println("La discontinuidad esta en la pared  "+Double.toString(cosaPadre[editando]+1));
        System.out.print("->Cantidad de elementos (1-127): ");
        cosaCantidad[editando] = scaner.nextInt();
        System.out.println("La discontinuidad esta en la pared  "+Double.toString(cosaCantidad[editando]));
        System.out.println("--|Discontinuidad añadida|--\n");
        menu();
        
    }
    
    private static void definirTemperatura(){
        System.out.println("\n--|Definir la temperatura interna|--");
        System.out.print("->Temperatura dentro de las paredes (ºC): ");
        dentroTemperatura = scaner.nextInt();
        System.out.println("Letemperatura interna es de "+Integer.toString(dentroTemperatura));
        System.out.println("--|Temperatura interior definida|--\n");
        
        menu();
    }
    
    private static void calcular(){
        
        System.out.println("\n--|Resultados|--\n");
        
        
        //superficie de paredes
        for (int i = 0; i < 20; i++) {
            superficiePared[i] = (paredAncho[i]*paredAlto[i]);
        }
        
        //recortar paredes
        for (int i = 0; i < 20; i++) {
            superficieCosa[i]= cosaAlto[i]*cosaAncho[i];
            superficiePared[cosaPadre[i]] -= superficieCosa[i];
        }
        
        //enseñar la superficie de las paredes
        for (int i = 0; i < 20; i++) {
            System.out.println("Superficie pared "+Integer.toString(i+1)+" = "+Double.toString(superficiePared[i])+" m2");
        }
        
        //energia conducida por paredes
        for (int i = 0; i < 20; i++) {
            cedidasParcialesJ[i] = (paredConstante[i]/paredProfundidad[i])*superficiePared[i]*(dentroTemperatura-fueraTemperatura[i]);
            if (Double.isNaN(cedidasParcialesJ[i])){
                cedidasParcialesJ[i] = 0;
            }
            cedidasParcialesW[i] = cedidasParcialesJ[i]/864;
            System.out.println("Energia cediada por pared "+Integer.toString(i+1)+" = "+Double.toString(cedidasParcialesJ[i])+" J/s = "+cedidasParcialesW[i]+" W/h");
        }
        
        //energia conducida por otras cosas
        for (int i = 20; i < 40; i++) {
            cedidasParcialesJ[i] = ((cosaConstante[i-20]/cosaProfundidad[i-20])*superficieCosa[i-20]*(dentroTemperatura-fueraTemperatura[cosaPadre[i-20]]))*cosaCantidad[i-20];
            if (Double.isNaN(cedidasParcialesJ[i])){
                cedidasParcialesJ[i] = 0;
            }
            cedidasParcialesW[i] = cedidasParcialesJ[i]/864;
            System.out.println("Energia cediada por discontinuidad "+Integer.toString(i-19)+" = "+Double.toString(cedidasParcialesJ[i])+" J/s = "+cedidasParcialesW[i]+" W/h");
        }
        
        //Energia total
        cedidaTotalesJ = 0;
        cedidaTotalesW = 0;
        for (int i = 0; i < 40; i++) {
            cedidaTotalesJ += cedidasParcialesJ[i];
            cedidaTotalesW += cedidasParcialesW[i];
        }
        System.out.println("Energia cedida por conducción total = "+Double.toString(cedidaTotalesJ)+" J/s = "+Double.toString(cedidaTotalesW)+" W/h");
        
        //Fin preguntar por reiniciar
        System.out.println("\nRegresar al menu?(s/n)");
        switch(scaner.next().toLowerCase()){
            case "s":
                menu();
                break;
            case "n":
                System.exit(0);
                break;
            default:
                menu();
                break;
        }
        
    }
    
    
}
