import java.util.Scanner;

public class ZorkGame {
    private static int noRoomVisted = 0, goRoom = 1;

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Do you want to play the Zork (Y/N)?");
        String msg = keyboard.nextLine();
        char direction = msg.charAt(0);


        boolean flag = true;

        if ((direction == 'y') || (direction == 'Y')) {
            // start the game
            while (flag) {
                System.out.println("Which direction would you like to go (N,E,W,S)? Type Q to quit.");
                msg = keyboard.nextLine();
                direction = msg.charAt(0);

                if ((direction == 'Q') || (direction == 'q'))
                    goRoom = -1;

                switch (goRoom) {
                    case 1:
                        gotoRoom1(direction);
                        break;

                    case 2:
                        gotoRoom2();
                        break;

                    default:
                        System.out.println("quit");
                        flag = false;
                        break;
                }
            }
            
        }
        else
            System.out.println("Maybe next time....");

        System.out.println("no room visted is "+ noRoomVisted);
    }

    public static void gotoRoom1(char dir){


        System.out.println("I am in room 1 with "+dir);


        switch (dir) {
            case 'N':
            case 'n':
                goRoom = 2;
                noRoomVisted++;
                break;

            default:
                goRoom = 1;
                break;
        }
        System.out.println("done with room1");

    }

    public static void gotoRoom2() {
        noRoomVisted++;
        System.out.println("I am in room 2");

    }
}
