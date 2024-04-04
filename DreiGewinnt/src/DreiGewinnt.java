import java.util.*;

public class DreiGewinnt {

    static ArrayList<Integer> Spielerpositionen = new ArrayList<Integer>();
    static ArrayList<Integer> CPUpositionen = new ArrayList<Integer>();

    public static void main(String[] args) {

        char [] []  Spielbrett = {{' ','|', ' ', '|', ' '},
                {'-','+', '-', '+', '-'},
                {' ','|', ' ', '|', ' '},
                {'-','+', '-', '+', '-'},
                {' ','|', ' ', '|', ' '}};

        Spielbrettausgabe(Spielbrett);



        while (true){
            Scanner Eingabe  = new Scanner(System.in);
            System.out.println("Gib dein Feld ein (1-9)");
            int Spielerpos = Eingabe.nextInt();
            while(Spielerpositionen.contains(Spielerpos) || CPUpositionen.contains(Spielerpos)){
                System.out.println("Feld belegt! Wähle ein anderes Feld");
                Spielerpos = Eingabe.nextInt();
            }


            FeldAnkreuzen(Spielbrett, Spielerpos, "Spieler");

            String Ergebnis = Siegkondition();
            if(Ergebnis.length()>0) {
                System.out.println(Ergebnis);
                break;
            }

            Random Zufall = new Random();
            int CPUPos = Zufall.nextInt(9) + 1;
            while(Spielerpositionen.contains(CPUPos) || CPUpositionen.contains(CPUPos)){
                CPUPos = Zufall.nextInt(9) + 1;
            }


            FeldAnkreuzen(Spielbrett, CPUPos, "CPU");


            Spielbrettausgabe(Spielbrett);

            Ergebnis = Siegkondition();
            if(Ergebnis.length()>0) {
                System.out.println(Ergebnis);
                break;
            }
        }


    }

    public static void Spielbrettausgabe(char [] [] Spielbrett){
        for (char [] Reihe : Spielbrett) {
            for (char c : Reihe) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void FeldAnkreuzen(char [] [] Spielbrett,int pos, String Nutzer) {

        char symbol = ' ';

        if(Nutzer.equals("Spieler")){
            symbol = 'X';
            Spielerpositionen.add(pos);
        } else if (Nutzer.equals("CPU")) {
            symbol = 'O';
            CPUpositionen.add(pos);

        }

        switch (pos) {
            case 1:
                Spielbrett[0][0] = symbol;
                break;
            case 2:
                Spielbrett[0][2] = symbol;
                break;
            case 3:
                Spielbrett[0][4] = symbol;
                break;
            case 4:
                Spielbrett[2][0] = symbol;
                break;
            case 5:
                Spielbrett[2][2] = symbol;
                break;
            case 6:
                Spielbrett[2][4] = symbol;
                break;
            case 7:
                Spielbrett[4][0] = symbol;
                break;
            case 8:
                Spielbrett[4][2] = symbol;
                break;
            case 9:
                Spielbrett[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String Siegkondition(){

        List obereReihe = Arrays.asList(1, 2, 3);
        List mittlereReihe = Arrays.asList(4, 5, 6);
        List untereReihe = Arrays.asList(7, 8, 9);
        List linkeReihe = Arrays.asList(1, 4, 7);
        List vertikalReihe = Arrays.asList(2, 5, 8);
        List rechteReihe = Arrays.asList(3, 6, 9);
        List kreuz1 = Arrays.asList(1, 5, 9);
        List kreuz2 = Arrays.asList(7, 5, 3);

        List<List> Siegkonditionen = new ArrayList<List>();
        Siegkonditionen.add(obereReihe);
        Siegkonditionen.add(mittlereReihe);
        Siegkonditionen.add(untereReihe);
        Siegkonditionen.add(linkeReihe);
        Siegkonditionen.add(vertikalReihe);
        Siegkonditionen.add(rechteReihe);
        Siegkonditionen.add(kreuz1);
        Siegkonditionen.add(kreuz2);

        for(List l:Siegkonditionen){
            if(Spielerpositionen.containsAll(l)){
                return "Glückwunsch du hast gewonnen :)";
            } else if (CPUpositionen.containsAll(l)) {
                return "Du hast leider verloren :(";
            }else if (Spielerpositionen.size() + CPUpositionen.size() == 9){
                return "Unentschieden";
            }
        }



        return "";
    }
}
