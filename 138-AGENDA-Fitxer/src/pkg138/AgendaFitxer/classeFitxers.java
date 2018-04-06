/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg138.AgendaFitxer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nuria
 */


//@LaMevaAnotacio(nomFitxerGen = "testProva.xml")
public class classeFitxers {

    // propietats
    private String fitxer;      // ruta al fitxer

    // variable global de la classe
    static Scanner scan = new Scanner(System.in);

    // mètodes
    // constructors
    /**
     *
     * @param fitxer parametre fitxer
     */
    public classeFitxers(String fitxer) {
        this.fitxer = fitxer;
    }

    /**
     *
     */
    public classeFitxers() {
    }

    // gets & sets
    /**
     *
     * @return retorna el fitxer
     */
    public String getFitxer() {
        return fitxer;
    }

    /**
     *
     * @param fitxer Aquest es el fitxer
     */
    public void setFitxer(String fitxer) {
        this.fitxer = fitxer;
    }

    // ALTRES MÈTODES
    /**
     *
     * @param fitxer Aquest es el fitxer de character stream
     * @param dada la dada que escriura en el fitxer
     * @param afegeix la linia
     * @throws IOException pot que que sigui llegida per un altra clase
     */
    public static void EscriuCharacterStream(String fitxer, String dada, boolean afegeix) throws IOException {
        // Donat un fitxer, un string i un boolea (true o false) escriu/afegeix un string al fitxer
        // /////////////////////////// //
        // FITXERS CHARACTER STREAMS //
        // ///////////////////////// //

        try ( // BLOC DE TRY .. CATCH

                FileWriter out = new FileWriter(fitxer, afegeix);) {
            // escrivim números al fitxer   // El boolean afegeix=true. Afegirà. False-> Fitxer nou
            //out.writeDouble(dada);
            out.write(dada + "\r\n");
            // EN WINDOWS \n SOL NO FA SALT DE LÍNIA AL FITXER. Hem d'agregar \r
            //out.write(dada + "\r\n");

        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");

        }
    }

    /**
     *
     * @param fitxer fitxer que character stream
     * @see #LlegirEscriureFitxer(java.lang.String) 
     * @throws IOException pot que que sigui llegida per un altra clase
     */
    public static void LlegeixCharacterStream(String fitxer) throws IOException {
        // Mostra per pantalla el text d'un fitxer "fitxer" de text
        // /////////////////////////// //
        // FITXERS CHARACTER STREAMS //
        // ///////////////////////// //

        try ( // BLOC DE TRY .. CATCH

                FileReader entra = new FileReader(fitxer);) {
            int c;
            while ((c = entra.read()) != -1) {
                System.out.print((char) (c));
            }
        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");

        }
    }

    /**
     *
     * @param fitxer fitxer de character stream
     * @param text lo que s'escriurá en el fitxer
     * @see #LlegirEscriureFitxer(java.lang.String) 
     * @return es retornarà
     * @throws FileNotFoundException  no hi ha excepcio
     * @throws IOException pot que que sigui llegida per un altra clase
     */
    public static boolean CercarCharacterStream(String fitxer, String text) throws FileNotFoundException, IOException {
        // Cerca un String dintre d'un fitxer. Retorna true si trobat o false si no trobat

        //boolean trobat = false;     // per dir si la paraula està al fitxer o no
        String linia;                   // per recollir la línia

        // /////////////////////////// //
        // FITXERS CHARACTER STREAMS //
        // ///////////////////////// //
        try ( // BLOC DE TRY .. CATCH

                FileReader in = new FileReader(fitxer);) {
            Scanner input = new Scanner(in);

            String[] noms = new String[4];

            // input.hasNextLine()  // RETORNA TRUE MENTRE HI HAGEN LÍNIES
            // input.nextLine       // RETORNA UNA LÍNIA SENCERA
            //STRING.contains(text) // RETORNA TRUE SI L'string CONTÉ EL TEXT (text)
            /**
             * FORMA 1 PODEM TENIR EL ERROR QUE SI POSEM UNA PARAULA CURTA,
             * SURTEN TOTES. per exemple, si pose: "A" es cercarà totes les
             * paraules del fitxer que continguen una A*
             */
            /*
            
            while (input.hasNextLine()) {   // Mentre hi hagen línies a l'arxiu ...
                p = input.nextLine();       // Extrau una línia del fitxer
                if (p.contains(text))       // Cerca una subseqüència dintre la línia
            
                // en cas que vullguem 2 seqüencies, per exemple nom i cognom
                //if (p.contains(text1) && p.contains(text2))
                    return trobat = true;               
            }
            
            /** FORMA 2, MÉS CORRECTA: AMB PARAULES EXACTES **/
            while (input.hasNextLine()) {           // Mentre hi hagen línies a l'arxiu ...
                linia = input.nextLine();               // Agafa una línia
                noms = linia.split("   ");              // Parteix per columnes
                if (text.equals(noms[0])) {         // noms[0]-> nom; noms[1]-> cognom; nom[2]-> edat, etc...
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");

        }

        return false;
    }

    /**
     *
     * @param fitxer fitxer que character stream
     * @param dada la dada del fitxer
     * @param afegeix afegira la dada 
     * @throws IOException pot se que sigui lleguda per una altra clase
     */
    public static void EscriuIntDataStream(String fitxer, int dada, boolean afegeix) throws IOException {
        // Donat un fitxer, un número sencer i un booleà (afegir o no) escriu el número sencer dintre del fitxer de tipus datastream
        // //////////////////// //
        // FITXERS DATA STREAMS //
        // //////////////////// //

        try ( // BLOC DE TRY .. CATCH

                DataOutputStream out = new DataOutputStream(new FileOutputStream(fitxer, afegeix));) {

            // escrivim números al fitxer   // El boolean afegeix=true. Afegirà. False-> Fitxer nou
            //out.writeDouble(dada);
            out.writeInt(dada);
        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");
        }

        // AL DATA STREAM PODEM ESCRIURE FÀCILMENT NÚMEROS
        // PERÒ QUE PASSA AMB STRINGS??
        // HO PODEM FER AIXÍ:
        /*        
                // Write data
        String str="foo";
        byte[] data=str.getBytes("UTF-8");
        out.writeInt(data.length);
        out.write(data);

         */
    }

    /**
     *
     * @param fitxer fitxer que character stream
     * @param v el vector
     * @param afegeix afegira la dada
     * @throws IOException pot se que sigui lleguda per una altra clase
     */ 
    public static void EscriuVectorInt(String fitxer, int v[], boolean afegeix) throws IOException {

        int i;
        for (i = 0; i < v.length; i++) {
            EscriuIntDataStream(fitxer, v[i], true);
        }

    }

    /**
     *
     * @param fitxer fitxer que character stream
     * @throws IOException pot se que sigui lleguda per una altra clase
     */
    public static void MostraIntDataStream(String fitxer) throws IOException {
        // Mostra per pantalla el contingut d'un fitxer DataStream, sols amb un número sencer
        // //////////////////// //
        // FITXERS DATA STREAMS //
        // //////////////////// //

        try ( // BLOC DE TRY .. CATCH
                DataInputStream in = new DataInputStream(new FileInputStream(fitxer));) {

            int c;
            while (in.available() > 0) {   // Per finalitzar de llegir un fitxer binari. available()>0
                c = in.readInt();
                System.out.println(c);
            }

        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");
        }

        // AL DATA STREAM PODEM LLEGIR FÀCILMENT NÚMEROS
        // PERÒ QUE PASSA AMB STRINGS??
        // HO PODEM FER AIXÍ:
        /*        
                // Read data
        int length=in.readInt();
        byte[] data=new byte[length];
        in.readFully(data);
        String str=new String(data,"UTF-8");

         */
    }

    /**
     *
     * @param fitxer fitxer que character stream
     * @return retornarà el fitxer
     * @throws IOException pot se que sigui lleguda per una altra clase
     */
    public static int SumaIntDataStream(String fitxer) throws IOException {

        /**
        @exception Pot donar una excepcio si no es llegeix el fitxer correctament
        */
        
        // Suma els elements sencers de un dataStream
        int suma = 0;
        // Fem la suma dels elements del fitxer
        try ( // BLOC DE TRY .. CATCH
                DataInputStream in = new DataInputStream(new FileInputStream(fitxer));) {
            int c;
            while (in.available() > 0) {   // Per finalitzar de llegir un fitxer binari. available()>0
                c = in.readInt();
                suma = suma + c;
            }

        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");
        }

        return suma;
    }

    /**
     *
     * @param dir parametre que comprovarà el directori
     * @return retornarà que el directori existeix o es correcte
     */
    public static boolean ComprovaDirectori(String dir) {

        // Comprova la existència d'un directori/fitxer
        Path path = Paths.get(dir);

        boolean ok = Files.exists(path);

        return ok;
    }

    /**
     *
     * @param fitx el parametre comprovarà que es pot accedir al fitxer/directori
     */
    public static void FitxerAccessible(String fitx) {

        // Comprova l'accessibilitat d'un directori / fitxer
        Path path = Paths.get(fitx);

        // podem llegir el fitxer?
        boolean isReadable = Files.isDirectory(path);

        // podem executar-lo?
        boolean isExecutable = Files.isExecutable(path);

        // podem modificar-lo?
        boolean isWritable = Files.isWritable(path);

        System.out.println("Lectura: " + isReadable + "\nEscriptura: " + isWritable + "\n Executable: " + isExecutable);
    }

    /**
     *
     * @param fitx es borarrà el fitxer/directori
     * @throws IOException ltres tipus d'errors. Per exemple no tenir permissos per borrar
     */
    public static void EliminarFitxerDirectori(String fitx) throws IOException {

        // Borrem un fitxer o directori
        // try{
        Path path = Paths.get(fitx);
        Files.delete(path);                 // No veiem encara la clàusula try .. catch .. finally
        // }catch(NoSuchFileException x){
        // // No existeix el fitxer

        // }catch(DirectoryNotEmptyException x){
        //     // El directori no està buit i no es pot borrar
        // }catch (IOException x){
        //     // Altres tipus d'errors. Per exemple no tenir permissos per borrar
        //}
    }

    /**
     *
     * @param origen fitxer origen
     * @param desti fitxer desti
     * @throws IOException Altres tipus d'errors. Per exemple no tenir permissos per borrar/copiar
     */
    public static void CopiarFitxerDirectori(String origen, String desti) throws IOException {

        // copia un fitxer (origen) en un fitxer (desti)
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);

        Files.copy(pathOrigen, pathDesti);
    }

    /**
     *
     * @param origen fitxer origen
     * @param desti fitxer desti
     * @throws IOException Altres tipus d'errors. Per exemple no tenir permissos per moure
     */
    public static void MoureFitxerDirectori(String origen, String desti) throws IOException {

        // copia un fitxer (origen) en un fitxer (desti)
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);

        // AMB LA OPCIÓ REPLACE_EXISTING. ESBORRAREM SI JA EXSITEIX ALGUN ARXIU AMB EL MATEIX NOM
        Files.move(pathOrigen, pathDesti, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     *
     * @param fitx nom del fitxer
     * @throws IOException Altres tipus d'errors.
     */
    public static void MetadadesFitxer(String fitx) throws IOException {

        if (ComprovaDirectori(fitx)) {
            Path path = Paths.get(fitx);
            System.out.println("Metadades del fitxer " + fitx + "\n");
            System.out.println("Tamany: " + Files.size(path) + " Bytes");
            System.out.println("Directory?: " + Files.isDirectory(path));
            System.out.println("Fitxer?: " + Files.isRegularFile(path));
            System.out.println("Simbòlic: " + Files.isSymbolicLink(path));
            System.out.println("Ocult: " + Files.isHidden(path));
            System.out.println("Ultima Modificació: " + Files.getLastModifiedTime(path));
            System.out.println("Propietari: " + Files.getOwner(path));
        } else {
            System.out.println("No existeix el fitxer");
        }
    }

    /**
     *
     * @param fitx Recorre de forma recursiva un directori i tots els seus directoris.
     */
    public static void RecorrerArbreDirectoris(String fitx) {

        // Recorre de forma recursiva un directori i tots els seus directoris, mostrant per pantalla tots els fitxers que hi han
        // 
        // NO UTILITZAT EN PROGRAMA EXEMPLE
        //
        File arx = new File(fitx);
        File listFile[] = arx.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    RecorrerArbreDirectoris(listFile[i].toString());        //  RECURSIU  //
                } else {
                    System.out.println(listFile[i].getPath());
                }
            }
        }
    }

    /**
     *
     * @param fitx Llegeix i/o escriu en un fitxer
     * @throws IOException Altres tipus d'errors.
     */
    public static void LlegirEscriureFitxer(String fitx) throws IOException {

        // Llegeix i/o escriu en un fitxer
        if (!ComprovaDirectori(fitx)) {

            Path path = Paths.get(fitx);
            String text, linia = null;

            // Creem un fitxer nou
            Files.createFile(path);

            // Inserim dades dintre del fitxer amb el mètode Write
            // Important les classes java.util.ArrayList  i  java.util.List;
            List<StringBuffer> dades = new ArrayList<StringBuffer>();
            System.out.println("Dona'm un text a inserir al fitxer " + fitx);
            text = scan.nextLine();
            dades.add(new StringBuffer(text));

            // ESCRIVIM DINTRE DEL FITXER FINALMENT
            Files.write(path, dades, Charset.forName("US-ASCII"));

            // LLEGIM EL CONTINGUT DEL FITXER
            Charset charset = Charset.forName("US-ASCII");
            BufferedReader reader = Files.newBufferedReader(path, charset);
            System.out.println("I en el fitxer hi ha escrit..");

            while ((linia = reader.readLine()) != null) {
                System.out.println(linia);
            }

        } else {
            System.out.println("El fitxer " + fitx + " ja existeix");
        }
    }

    /**
     *
     * @return et retornarà la ruta (ha de existir)
     */
    public static String pregunta_fitxer() {

        String fitx;
        System.out.println("Dona'm un directori / fitxer (amb la ruta). \nSi és amb windows recorda forma C:\\dir1\\dir2\\etc..");
        fitx = scan.next();

        return fitx;
    }

    /**
     * @version quest mètode és la versió 1.2  
     * @return retornará el fitxer (no ha d'existir, si no el borrarà)
     */
    public static String pregunta2_fitxer() {
        // fitxer nou. esborrarà si hi ha algun amb aquest nom
        String fitx;
        System.out.println("Dona'm un nom d'un fitxer (amb la ruta). \nSi és amb windows recorda forma C:\\dir1\\dir2\\etc..");
        System.out.println("WARNING!! EL FITXER NO HA D'EXISTIR. SINÒ L'ESBORRARÀ");
        fitx = scan.next();

        return fitx;
    }

    
   

    
}
