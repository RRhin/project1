/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg138.AgendaFitxer;

//import PaquetFuncions.FuncionsFitxers;
import java.util.Scanner;
// Per importar classes d'altres projectes:
// Projectes > llibreries > addProject
//
//      ****  Projecte FuncionsComunes    ****
//import static PaquetFuncions.FuncionsFitxers.ComprovaDirectori;
//import static PaquetFuncions.FuncionsFitxers.CopiarFitxerDirectori;
//import static PaquetFuncions.FuncionsFitxers.EliminarFitxerDirectori;
//import static PaquetFuncions.FuncionsFitxers.EscriuCharacterStream;
//import static PaquetFuncions.FuncionsFitxers.CercarCharacterStream;
//import static PaquetFuncions.FuncionsFitxers.LlegeixCharacterStream;
//import static PaquetFuncions.FuncionsFitxers.MoureFitxerDirectori;
import java.io.FileReader;
import java.io.IOException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)


//public @interface LaMevaAnotacio {
//    String nomFitxerGen() default "test.xml";
//}



/**
 *
 * @author Nuria
 */



public class AgendaEnFitxer {

    /**
     * @param args the command line arguments
     */
        
    @SuppressWarnings("deprecation")
    
    // VARIABLES GLOBALS
    static Scanner scan = new Scanner(System.in);
    static String fitxer = "fitxers/persones.txt";

    
    // <editor-fold defaultstate="collapsed" desc="Funcions">

    private static String pregunta_dades() {
        
        String text;
        String nom,cognom;
        int edat; 
        double sou;
        
        // Recollim dades i retorna una fila de text amb totes les dades
        System.out.println("Dona'm el nom de la persona");
        nom = scan.nextLine();
        nom = scan.nextLine();

        System.out.println("Dona'm el cognom de " + nom);
        cognom = scan.nextLine();
        System.out.println("Dona'm la edat de " + nom);
        edat = scan.nextInt();
        System.out.println("Dona'm el sou de " + nom);
        sou = scan.nextDouble();

        text = nom + "   " + cognom + "   " + edat + "   " + sou;
        return text;

    }

    private static void afegir_persones() throws IOException {

        // Variables
        String text;    // els atributs de persones estan en pregunta_dades()
        // CREEM LA CLASSE PER ACCEDIR A FITXERS
        classeFitxers agf=new classeFitxers();
        
        // Recollim dades
        System.out.println("AFEGIR PERSONES");
        System.out.println("------ --------\n");
        text = pregunta_dades();

        // Afegim en fitxer.     
       agf.EscriuCharacterStream(fitxer, text, true);

    }
    
    private static void elimina(String fitxer, String text) {
         // AQUESTA FUNCIÓ LA UTLITZEM PER A ELIMINAR UN USUARI DEL FITXER
         // I TAMBÉ PER A MODIFICAR UN USUARIO DEL FITXER (ELIMINEM I DESPRÉS JA AFEGIM)
         
        String linia;
        String fitxer_tmp = "../fitxers/fitxer_tmp";
        String[] noms = new String[4];
        // CREEM LA CLASSE PER ACCEDIR A FITXERS
        classeFitxers agf=new classeFitxers();

        
        try ( // BLOC DE TRY .. CATCH

            FileReader in = new FileReader(fitxer);) {
            Scanner input = new Scanner(in);
           
            while (input.hasNextLine()) {           // Mentre hi hagen línies a l'arxiu ...
                linia = input.nextLine();           // Agafa una línia
                noms = linia.split("   ");          // Parteix per columnes
                if (!text.equals(noms[0])) {
                    agf.EscriuCharacterStream(fitxer_tmp, linia, true);
                }
            }
            //EliminarFitxerDirectori(fitxer);
            agf.MoureFitxerDirectori(fitxer_tmp, fitxer);
        } catch (Exception e) {
            System.err.println(";-(");
        }
    }

    private static void modificar_persones() throws IOException {
        // Preguntarà pel nom d'una persona i la modificarà
        // Esborrem la persona a modificar, i al final de l'arxiu tornem a posar totes les dades de la persona
        
        // Variables
        String nom_pregunta, text;     

        System.out.println("Dona'm el nom de la persona a modificar");
        nom_pregunta = scan.nextLine();
        nom_pregunta = scan.nextLine();      
        // CREEM LA CLASSE PER ACCEDIR A FITXERS
        classeFitxers agf=new classeFitxers();
        
        if (agf.CercarCharacterStream(fitxer, nom_pregunta)) {
            // Modifica les dades de la persona 
            text = pregunta_dades();                    // recollim les noves dades
            elimina(fitxer, nom_pregunta);              // esborrem la persona
            agf.EscriuCharacterStream(fitxer, text, true);  // afegim al final la persona

        } else {
            System.err.println(nom_pregunta + " no trobat");
        }
    }

    private static void eliminar_persones() throws IOException {
        // Preguntarà pel nom d'una persona i si existeix la eliminarà
        String nom;

        // CREEM LA CLASSE PER ACCEDIR A FITXERS
        classeFitxers agf=new classeFitxers();
        
        System.out.println("Dona'm el nom de la persona a eliminar");
        nom = scan.nextLine();
        nom = scan.nextLine();

        if (agf.CercarCharacterStream(fitxer, nom)) {

            elimina(fitxer, nom);

        } else {
            System.err.println(nom + " no està al fitxer");
        }

    }
    
    private static void cercar_persona() throws IOException{
    
        String nom;
        // Recollim dades i retorna una fila de text amb totes les dades
        
        // CREEM LA CLASSE PER ACCEDIR A FITXERS
        classeFitxers agf=new classeFitxers();
        
        System.out.println("Dona'm el nom de la persona a cercar");
        nom = scan.nextLine();
        nom = scan.nextLine();
        
        if (agf.CercarCharacterStream(fitxer, nom))
                System.out.println(nom+" SI està al fitxer");
        else
                System.err.println(nom+" NO està al fitxer");        
    }

    private static void cercar_per_sou() throws IOException {
        // Preguntarà pel nom d'una persona i la modificarà
        Double sou;
        String linia = "";
        String[] sous = new String[4];
        Double souReal;
                
        System.out.println("Dona'm el sou a partir del que vols buscar");
        sou = scan.nextDouble();
        

        try ( // BLOC DE TRY .. CATCH

                FileReader in = new FileReader(fitxer);) {
            Scanner input = new Scanner(in);

            while (input.hasNextLine()) {                   // Mentre hi hagen línies a l'arxiu ...
                linia = input.nextLine();                   // Agafa una línia
                sous = linia.split("   ");                  // Parteix per columnes
                souReal = Double.parseDouble(sous[3]);      // Convertim el número extret a double
                if (souReal > sou) {    // Comprovem el sou donat amb els que hi han guardats
                    System.out.println(linia);
                }
            }
          } catch (Exception e) {
          System.err.println(";-( Algun error en el fitxer");
        }
    }
    
    private static int menu(){
        int op;
        System.out.println("\n\tMENÚ");
        System.out.println("\t----\n");
        System.out.println("1) Afegir Persones");
        System.out.println("2) Cercar Persona");
        System.out.println("3) Eliminar Persones");
        System.out.println("4) Modificar Persones");
        System.out.println("5) Buscar Per sou");
        System.out.println("6) Visualitza Fitxer");
        System.out.println("0) Sortir");
        op = scan.nextInt();
            
        return op;
    }

    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Principal">

    /**
     *
     * @param args projecte principal de Agenda en fitxer
     * @throws IOException pot que que sigui llegida per un altra clase
     */

    public static void main(String[] args) throws IOException {
        // Busca en un fitxer de Persones el nom i/o el sou que cobren

        // CREEM LA CLASSE PER ACCEDIR A FITXERS
        classeFitxers agf=new classeFitxers();
        
        int op;
        if (!agf.ComprovaDirectori(fitxer)) {
            System.out.println("El fitxer no existeix. El crearem ara mateix\n\n");
        }

        do {
            
            op=menu();

            switch (op) {
                case 1:
                    afegir_persones();
                    break;
                case 2:
                    cercar_persona();
                    break;
                case 3:
                    eliminar_persones();
                    break;
                case 4:
                    modificar_persones();
                    break;
                case 5:
                    cercar_per_sou();
                    break;
                case 6:
                    agf.LlegeixCharacterStream(fitxer);
                    break;
                case 0:
                    System.out.println("\nAdeu!\n('')--,--,-\n");
                    break;
                default:
                    System.err.println("Opció Incorrecta\n");
                    break;
            }
        } while (op != 0);
    }
    
    // </editor-fold>
    
    
}
