import java.util.ArrayList;
import java.util.List;

//OVERVIEW: Le istanze della classe rappresentano un risolutore del primo strato del cubo di rubik: 
//          dato un cubo, è capace di risolvere il primo strato: cioè di rendere il primo strato di ogni faccia di un colore unico.
public class CubeSolver {

    //ATTRIBUTI
    //il cubo da risolvere
    private Cubo cube;
    private int numMosse;

    //Post-condizioni: inizializza this a un risolutore del cubo c
    //                 Solleva IllegalArgumentException se c == null
    public CubeSolver(Cubo c) {
        if (c==null) throw new IllegalArgumentException("Cubo nullo");

        cube = c;
        numMosse = 0;
    }

    //Post-condizioni: trova il le facce i cui spigoli hanno almeno un colore c
    private List<Faccia> findEdges(char c) {
        List<Faccia> list = new ArrayList<>();

        if (!cube.arancione.edges(c).isEmpty()) list.add(cube.arancione);
        if (!cube.verde.edges(c).isEmpty()) list.add(cube.verde);
        if (!cube.rossa.edges(c).isEmpty()) list.add(cube.rossa);
        if (!cube.blu.edges(c).isEmpty()) list.add(cube.blu);
        if (!cube.gialla.edges(c).isEmpty()) list.add(cube.gialla);
        if (!cube.bianca.edges(c).isEmpty()) list.add(cube.bianca);

        return list;
    }

    //Post-condizioni: trova il le facce i cui lati hanno almeno un colore c
    private List<Faccia> findSides(char c) {
        List<Faccia> list = new ArrayList<>();

        if (!cube.arancione.sides(c).isEmpty()) list.add(cube.arancione);
        if (!cube.verde.sides(c).isEmpty()) list.add(cube.verde);
        if (!cube.rossa.sides(c).isEmpty()) list.add(cube.rossa);
        if (!cube.blu.sides(c).isEmpty()) list.add(cube.blu);
        if (!cube.gialla.sides(c).isEmpty()) list.add(cube.gialla);
        if (!cube.bianca.sides(c).isEmpty()) list.add(cube.bianca);

        return list;
    }

    //Post-condizioni: ruota la faccia di colore c, se c è un colore valido
    private void ruota(char c) {
        numMosse+=1;
        switch (c) {
            case 'w':
                cube.ruotaBianco();
                break;
            case 'b':
                cube.ruotaBlu();
                break;
            case 'a':
                cube.ruotaArancio();
                break;
            case 'g':
                cube.ruotaVerde();
                break;
            case 'y':
                cube.ruotaGiallo();
                break;
            case 'r':
                cube.ruotaRosso();
                break;
            default:
                numMosse--;
                break;
        }
        System.out.println(c);
        System.out.println(cube.toString());
    }

    //Post-condizioni: posiziona uno spigolo biancho di f nel modo corretto per le facce laterali
    //                 Solleva illegalArgumentException se f è bianca.
    protected void whiteEdges(Faccia f) {
        if (f.equals(cube.bianca)) throw new IllegalArgumentException("Faccia non valida");

        Record r; 
        if (!f.sides('w').isEmpty()) {
            r = f.sides('w').get(0);
        } else {
            return;
        }

        char col1;
        if (!f.equals(cube.gialla)) {
            //TODO...
        }
    }

    //Post-condizioni: posiziona un lato biancho di f nel modo corretto per le facce laterali
    //                 Solleva illegalArgumentException se f è bianca.
    protected void whiteSides(Faccia f) {     
        if (f.equals(cube.bianca)) throw new IllegalArgumentException("Faccia non valida");

        Record r; 
        if (!f.sides('w').isEmpty()) {
            r = f.sides('w').get(0);
        } else {
            return;
        }

        char col;
        if (!f.equals(cube.gialla)) {

            if (r.getX() == 0 && r.getY() == 1) {
                for (int i=0; i<2; i++) {
                    ruota(f.faccia[1][1]);
                }
            } else if (r.getX()==1 && r.getY()==2) {
                ruota(f.faccia[1][1]);
            } else if(r.getX() == 1 && r.getY()==0) {
                for (int i=0; i<3; i++) {
                    ruota(f.faccia[1][1]);
                }
            }

            if (f.faccia[1][1] == cube.gialla.adiacenti.get(0)) {
                col = cube.gialla.faccia[0][1];
            } else if (f.faccia[1][1] == cube.gialla.adiacenti.get(1)) {
                col = cube.gialla.faccia[1][2];
            } else if (f.faccia[1][1] == cube.gialla.adiacenti.get(2)) {
                col = cube.gialla.faccia[2][1];
            } else {
                col = cube.gialla.faccia[1][0];
            }
        } else {
            char col2;
            if (r.getX() == 0 && r.getY() == 1) {
                col = cube.verde.faccia[2][1];
                col2 = 'g';
            } else if (r.getX() == 1 && r.getY()==2) {
                col = cube.rossa.faccia[2][1];
                col2 = 'r';
            } else if (r.getX() == 2 && r.getY()==1) {
                col = cube.blu.faccia[2][1];
                col2='b';
            } else {
                col = cube.arancione.faccia[2][1];
                col2='a';
            }

            if (col == col2) {
                ruota(col);
                ruota(col);
                return;
            }

        }

        int index = Math.abs(cube.gialla.adiacenti.indexOf(col) - cube.gialla.adiacenti.indexOf(f.faccia[1][1]));
        for (int i=0; i<index+1; i++) {
            ruota('y');
        }

        char ad = cube.getFaccia(col).adiacenti.get(1);
        ruota(ad);
        for (int i=0; i<3; i++) {
            ruota(col);
        }

        for (int i=0; i<3; i++) {
            ruota(ad);
        }
    }

    //Post-condizioni: resituisce true se la croce di colore col è presente, false altrimenti
    private boolean cross(Faccia f) {
        return f.faccia[0][1] == f.faccia[1][1] 
            && f.faccia[1][2] == f.faccia[1][1]
            && f.faccia[2][1] == f.faccia[1][1]
            && f.faccia[1][0] == f.faccia[1][1]
            && f.adiacenti.get(0) == cube.getFaccia(f.adiacenti.get(0)).faccia[0][1]
            && f.adiacenti.get(1) == cube.getFaccia(f.adiacenti.get(1)).faccia[0][1]
            && f.adiacenti.get(2) == cube.getFaccia(f.adiacenti.get(2)).faccia[0][1]
            && f.adiacenti.get(3) == cube.getFaccia(f.adiacenti.get(3)).faccia[0][1];
    }

    //Post-condizioni: ruota le facce per formare la croce bianca, resituendo le mosse necessarie
    protected void whiteCross() {
        List<Faccia> list = findSides('w');

        if (list.indexOf(cube.bianca)!=-1) {
            Record rec = cube.bianca.sides('w').get(0);
            if (rec.getX() == 1 && rec.getY() == 0) {
                ruota('w');
            } else if (rec.getX()==1 && rec.getY()==2) {
                for (int i=0; i<3; i++) {
                    ruota('w');
                }
            } else if (rec.getX()==2 && rec.getY()==1) {
                for (int i=0; i<3; i++) {
                    ruota('w');
                }
            }

            char col = cube.blu.faccia[0][1];
            int index = cube.bianca.adiacenti.indexOf(col);

            for (int i=0; i<index; i++) {
                ruota('w');
            }
            list.remove(list.indexOf(cube.bianca));
        }

        while (!cross(cube.bianca)) {
            for (Faccia f : list) {
                if (!f.equals(cube.bianca)) {
                    whiteSides(f);
                }
            }
            list = findSides('w');
        }

    }
}
