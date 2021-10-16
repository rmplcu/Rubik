import java.util.Arrays;
import java.util.Scanner;

//OVERVIEW: le istanze della classe rappresentano un cubo di rubik: un cubo composto da 6 faccie 
//          ruotabili di colore diverso
public class Cubo {
    
    //ATTRIBUTI
    protected Faccia bianca;
    protected Faccia arancione;
    protected Faccia verde;
    protected Faccia rossa;
    protected Faccia blu;    
    protected Faccia gialla;

    //Post-condizioni; inizializza this a un nuovo cubo con le faccie di 
    //                 colore bianco, rosso, blu , arancio , verde, giallo.
    public Cubo() {
        bianca = new Faccia('w', 'b', 'r', 'g', 'a');
        arancione = new Faccia('a', 'w', 'g', 'y', 'b');
        rossa = new Faccia('r', 'w', 'b', 'y', 'g');
        verde = new Faccia('g', 'w', 'r', 'y', 'a');
        blu = new Faccia('b', 'w', 'a', 'y', 'r');
        gialla = new Faccia('y', 'g', 'r', 'b', 'a');
    }

    //Post-condizioni: resitsuice la faccia di colore col
    public Faccia getFaccia(char col) {
        switch (col) {
            case 'w':
                return bianca;
            case 'b':
                return blu;
            case 'g':
                return verde;
            case 'y':
                return gialla;
            case 'r':
                return rossa;
            case 'a':
                return arancione;
            default:
                return null;
        }
    }

    //Post-condizioni: ruota la faccia bianca di 90° a destra,ruotando in parte anche le altre faccie
    public void ruotaBianco() {
        bianca.ruota();
        char [] b = Arrays.copyOf(blu.faccia[0], 3); 
        char [] r = Arrays.copyOf(rossa.faccia[0], 3);
        char [] v = Arrays.copyOf(verde.faccia[0], 3);
        char [] a = Arrays.copyOf(arancione.faccia[0], 3);

        blu.faccia[0] = a;
        rossa.faccia[0] = b;
        verde.faccia[0] = r;
        arancione.faccia[0] = v;    
    }

    //Post-condizioni: ruota la faccia arancione di 90° a destra,ruotando in parte anche le altre faccie
    public void ruotaArancio() {
        arancione.ruota();

        char [] bi = new char[3];
        for (int i=0; i<3; i++) {
            bi[i] = bianca.faccia[i][0];
        }

        char [] ve = new char[3];
        for (int i=0; i<3;i++) {
            ve[i] = verde.faccia[i][0];
        }

        char [] bl = new char[3];
        for (int i=0; i<3; i++) {
            bl[i] = blu.faccia[i][2];
        }

        char [] ye = new char[3];
        for (int i=0; i<3; i++) {
            ye[i] = gialla.faccia[i][0];
        }

        //aggiorno bianco
        for (int i=0; i<3; i++) {
            bianca.faccia[i][0] = bl[2-i];
        }

        //aggiorno verde
        for (int i=0; i<3; i++) {
            verde.faccia[i][0] = bi[i];
        }

        //aggiorno giallo
        for (int i=0; i<3; i++) {
            gialla.faccia[i][0] = ve[i];
        }

        //aggiorno blu
        for (int i=0; i<3; i++) {
            blu.faccia[i][2] = ye[2-i];
        }
    }


    //Post-condizioni: ruota la faccia blu di 90° a destra,ruotando in parte anche le altre faccie
    public void ruotaBlu() {
        blu.ruota();

        char [] w = Arrays.copyOf(bianca.faccia[0], 3);
        char [] y = Arrays.copyOf(gialla.faccia[2], 3);

        char [] a = new char[3];
        for (int i=0; i<3; i++) {
            a[i] = arancione.faccia[i][0];
        }

        char [] r = new char[3];
        for (int i=0; i<3; i++) {
            r[i] = rossa.faccia[i][2];
        }

        bianca.faccia[0] = r;

        for (int i=0; i<3; i++) {
            arancione.faccia[i][0] = w[2-i];
        }

        for (int i=0; i<3; i++) {
            rossa.faccia[i][2] = y[2-i];
        }

        gialla.faccia[2] = a;
    }

    //Post-condizioni: ruota la faccia rossa di 90° a destra,ruotando in parte anche le altre faccie
    public void ruotaRosso() {
        rossa.ruota();

        char [] w = new char[3];
        for (int i=0; i<3; i++) {
            w[i] = bianca.faccia[i][2];
        }

        char [] v = new char[3];
        for (int i=0; i<3; i++) {
            v[i] = verde.faccia[i][2];
        }

        char [] y = new char[3];
        for (int i=0; i<3; i++) {
            y[i] = gialla.faccia[i][2];
        }

        char [] b = new char[3];
        for (int i=0; i<3; i++) {
            b[i] = blu.faccia[i][0];
        }

        //aggiorno bianco
        for (int i=0; i<3; i++) {
            bianca.faccia[i][2] = v[i];
        }

        //aggiorno verde
        for (int i=0; i<3; i++) {
            verde.faccia[i][2] = y[i];
        }

        //aggiorno giallo
        for (int i=0; i<3; i++) {
            gialla.faccia[i][2] = b[2-i];
        }

        //aggiorno blu
        for (int i=0; i<3; i++) {
            blu.faccia[i][0] = w[2-i];
        }
    }

    //Post-condizioni: ruota la faccia verde di 90° a destra,ruotando in parte anche le altre faccie
    public void ruotaVerde() {
        verde.ruota();

        char [] w = Arrays.copyOf(bianca.faccia[2],3);
        
        char [] a = new char[3];
        for (int i=0; i<3; i++) {
            a[i] = arancione.faccia[2-i][2];
        }

        char [] y = Arrays.copyOf(gialla.faccia[0], 3);
        
        char [] r = new char[3];
        for (int i=0;i<3; i++) {
            r[i] = rossa.faccia[2-i][0];
        } 

        //aggiorno bianco
        bianca.faccia[2] = a;

        //aggiorno arancione
        for (int i=0; i<3; i++) {
            arancione.faccia[i][2] = y[i];
        }

        //aggiorno giallo
        gialla.faccia[0] = r;

        //aggiorno rosso
        for (int i=0; i<3; i++) {
            rossa.faccia[i][0] = w[i];
        }
    }

    //Post-condizioni: ruota la faccia gialla di 90° a destra,ruotando in parte anche le altre faccie
    public void ruotaGiallo() {
        gialla.ruota();

        char [] v = verde.faccia[2];
        char [] r = rossa.faccia[2];
        char [] b = blu.faccia[2];
        char [] a = arancione.faccia[2];

        arancione.faccia[2] = b;
        verde.faccia[2] = a;
        rossa.faccia[2] = v;
        blu.faccia[2] = r;
    }

    //Post-condizioni: restituisce true se il cubo è risolto (tutte le faccie di un colore), false altrimenti
    public boolean risolto() {
        return bianca.isFull() 
            && rossa.isFull()
            && verde.isFull() 
            && blu.isFull()
            && arancione.isFull()
            && gialla.isFull();
    }

    @Override
    public String toString() {
        String s = "";
        Scanner w = new Scanner(blu.toString());
        while (w.hasNextLine()) {
            s+="         " + w.nextLine() + "\n";
        }
        Scanner sc = new Scanner(arancione.toString());
        Scanner sc1 = new Scanner(bianca.toString());
        Scanner sc2 = new Scanner(rossa.toString());
        Scanner sc3 = new Scanner(gialla.toString());

        while (sc.hasNextLine()) {
            s+=sc.nextLine() + " | " + sc1.nextLine() +" | " + sc2.nextLine() +" | " +sc3.nextLine() +"\n";    
        } 
        Scanner v = new Scanner(verde.toString());
        while (v.hasNextLine()) {
            s+="         " + v.nextLine() + "\n";
        }
        return s;
    }

    
}
