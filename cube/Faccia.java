import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

//da javadoccare

//OVERVIEW: le istanze della classe rappresentano una faccia del cubo di rubik: composta da 9 elementi 
//          di un certo colore con la capacità di ruotare.
public class Faccia {

    //ATTRIBUTI
    protected char [][] faccia;
    //nell'ordine su, destra, giù, sinistra
    protected List<Character> adiacenti;

    //Post-condizioni: inizializza this a una faccia di colore colour 
    //                 Solleva IllegalArgumentException se una degli elmenti di c 
    //                 è null o se ci sono meno di 4 elementi in c.
    protected Faccia(char colour, Character ...c) {
        if (c.length != 4) throw new IllegalArgumentException("Numero faccie adiacenti sbagliato");
        
        adiacenti = new ArrayList<>();
        for (Character ch : c) {
            if (ch == null) throw new IllegalArgumentException("Faccia null");
            adiacenti.add(ch);
        }

        faccia = new char [3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                faccia[i][j] = colour;
            }
        }

        
    }

    //Post-condizioni: restituisce la posizione di c nella faccia se c è in un lato della faccia
    public List<Record> sides(char c) {
        List<Record> res = new ArrayList<>();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (faccia[i][j]==c && (i+j) % 2 != 0) {
                    res.add(new Record(i,j));
                }
            }
        }

        return res;
    }

    //Post-condizioni: resitsuisce la posizione di c nella faccia se c è su uno spigolo della faccia
    public List<Record> edges(char c) {
        List<Record> res = new ArrayList<>();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (faccia[i][j] == c && i!=j && (i+j) % 2 == 0) res.add(new Record(i,j));
            }
        }
        return res;
    }

    //Post-condizioni: Setta faccia[i][j] a ch
    //                 Solleva IndexOutOfBoundsException se i o j <0 o i o j > 2
    protected void set(int i, int j, char ch) {
        if (i<0 || i>2 || j<0 || j>2) throw new IndexOutOfBoundsException();

        faccia[i][j] = ch;
    }

    //Ruota la faccia di 90° a destra
    protected void ruota() {
        char [][]copia = new char[3][3];
        for (int i=0; i<3;i++) {
            copia[i] = Arrays.copyOf(faccia[i], 3);
        }

        //ruota faccia 
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                faccia[i][j] = copia[2-j][i];
            }
        }

    }

    //Post-condizioni: restitusice true se tutti i blocchi della faccia sono uguali
    public boolean isFull() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (faccia[i][j] != faccia[1][1]) return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0; i<3; i++) {
            for (int j=0; j<3;j++) {
                String col;
                switch (faccia[i][j]) {
                    case 'w':
                        col = "\u001B[37m";
                        break;
                    case 'g':
                        col = "\u001B[32m";
                        break;
                    case 'r':
                        col = "\u001B[31m";
                        break;
                    case 'b':
                        col = "\u001B[34m";
                        break;
                    case 'a':
                        col = "\033[35m";
                        break;
                    case 'y':
                        col = "\u001B[33m";
                        break;
                    default:
                        col = "\u001B[30m";
                        break;
                }
                s+=col + "\u25A0\u001B[0m ";
            }
            s+="\n";
        }
        return s.substring(0, s.length()-1);
    }

}