package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = "         ";
        String answer;

        char[] inputlst = input.toCharArray();
        char[][] game = new char[3][3];

        int x = 0;
        int xcount = 0;
        int ocount = 0;
        int turn = 0;
        int coord1;
        int coord2;

        boolean xwins = false;
        boolean owins = false;
        boolean empty = false;

        //Creación del tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = inputlst[x];
                x++;
            }
        }

        //Imprimir el juego
        tablero(game);

        while (true) {
            String coords = sc.nextLine();

            //Comprobar que las coordenadas sean números
            digits(sc, coords);

            //Pasar las coordenadas a enteros
            coord1 = coords.charAt(0) - '0';
            coord2 = coords.charAt(2) - '0';

            //Comprobar que las coordenadas esten dentro del tablero
            while (coord1 > 3 || coord2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                coords = sc.nextLine();
                digits(sc, coords);
                coord1 = coords.charAt(0) - '0';
                coord2 = coords.charAt(2) - '0';
            }

            while (game[coord1 - 1][coord2 - 1] == 'X' || game[coord1 - 1][coord2 - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                coords = sc.nextLine();
                coord1 = coords.charAt(0) - '0';
                coord2 = coords.charAt(2) - '0';
                while (coord1 > 3 || coord2 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    coords = sc.nextLine();
                    coord1 = coords.charAt(0) - '0';
                    coord2 = coords.charAt(2) - '0';
                }
                digits(sc, coords);
                coord1 = coords.charAt(0) - '0';
                coord2 = coords.charAt(2) - '0';
            }
            if (turn % 2 == 0) {
                game[coord1 - 1][coord2 - 1] = 'X';
            } else {
                game[coord1 - 1][coord2 - 1] = 'O';
            }
            tablero(game);

            //Comprobación de victoria
            if (turn >= 8) {
                xcount = 0;
                ocount = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (game[i][j] == 'X') {
                            xcount++;
                        } else if (game[i][j] == 'O') {
                            ocount++;
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (game[i][j] == ' ') {
                            empty = true;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                if ((game[i][0] == 'X' && game[i][1] == 'X' && game[i][2] == 'X') ||
                        (game[0][i] == 'X' && game[1][i] == 'X' && game[2][i] == 'X') ||
                        (game[0][0] == 'X' && game[1][1] == 'X' && game[2][2] == 'X') ||
                        (game[0][2] == 'X' && game[1][1] == 'X' && game[2][0] == 'X')) {
                    xwins = true;
                    break;
                }
            }
            for (int i = 0; i < 3; i++) {
                if ((game[i][0] == 'O' && game[i][1] == 'O' && game[i][2] == 'O') ||
                        (game[0][i] == 'O' && game[1][i] == 'O' && game[2][i] == 'O') ||
                        (game[0][0] == 'O' && game[1][1] == 'O' && game[2][2] == 'O') ||
                        (game[0][2] == 'O' && game[1][1] == 'O' && game[2][0] == 'O')) {
                    owins = true;
                    break;
                }
            }
            if (empty) {
                answer = "Game not finished";
                break;
            } else if (turn >= 8 && !xwins && !owins) {
                answer = "Draw";
                break;
            } else if (xwins && !owins) {
                answer = "X wins";
                break;
            } else if (owins && !xwins) {
                answer = "O wins";
                break;
            } else if (owins || xcount - 1 > ocount || ocount - 1 > xcount){
                answer = "Impossible";
                break;
            }
            turn++;
        }
        System.out.print(answer);
    }

    //Imprimir el tablero
    private static void tablero(char[][] game) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static  void digits(Scanner sc, String coords) {
        while (!Character.isDigit(coords.charAt(0))) {
            System.out.println("You should enter numbers!");
            coords = sc.nextLine();
        }
    }
}