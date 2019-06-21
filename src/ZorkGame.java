/*
 * Write an application that asks the user what direction they wish to travel in.
 * Once they tell you the direction, move them to the next room and tell them
 * what is in it and what direction the other exits are.
 *
 * When you develop your program you must include each room in its own method.
 * (Reminder: Creating Methods).  The only role of the main method is to get input
 * from the user and direct the user to the appropriate room.
 *
 * This program allows
 * - the user to move from one room back and forth to any other connected room.
 * - the user to find the secret room only 25% of the time. However, once they
 *   find the secret room they can always find it.
 *
 * When the user exits the house or quits there is a 25% chance they will be
 * followed by a ghost. Let them know when they are being followed.
 *
 * Also let the user know how many rooms they visited after they exit or quit.
 *
 * Bonus:
 * Extend the Zork program to allow for each room to have a random amount of money
 * between $0 and $1000.00, that you can take, adding it to your personal total.
 * If the money is taken it is not in the room anymore.
 */


import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class ZorkGame {
    private static int noRoomVisted = 0, goRoom = 0;
    private static boolean secretKnown = false;
    private static Hashtable<String, String> moneyInRoom = new Hashtable<String, String>();
    private static double moneyAtHand = 0;
    private static String[] roomNames = {"outside", "foyer", "front room", "library",
                                         "kitchen", "dining room",
                                         "vault", "parlor", "secret room"};

    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Do you want to play the Zork (Y/N)?");
        String msg = keyboard.nextLine();
        char direction = msg.charAt(0);
        boolean flag = true;

        if ((direction == 'y') || (direction == 'Y')) {
            // initialize the game
            initRoom();


System.out.println("***** moneyInRoom: " + moneyInRoom);



            // start the game
            while (flag && goRoom != -1) {
                if (goRoom == 0)
                    System.out.println("You are at the front of the house.");
                else if (goRoom == 8)
                    System.out.println("You are in the secret room!!!");
                else
                    System.out.printf("*****You are in %s with $%.2f\n", roomNames[goRoom], moneyAtHand);

                System.out.println("Which direction would you like to go (N,E,W,S)? Type Q to quit.");

                msg = keyboard.nextLine();
                direction = msg.charAt(0);

                if ((direction == 'Q') || (direction == 'q'))
                    goRoom = -1;
                else {

                    getMoney(roomNames[goRoom]);
                    switch (goRoom) {
                        case 0:
                            gotoStart(direction);
                            break;

                        case 1:
                            gotoRoom1(direction);
                            break;

                        case 2:
                            gotoRoom2(direction);
                            break;

                        case 3:
                            gotoRoom3(direction);
                            break;

                        case 4:
                            gotoRoom4(direction);
                            break;

                        case 5:
                            gotoRoom5(direction);
                            break;

                        case 6:
                            gotoRoom6(direction);
                            break;

                        case 7:
                            gotoRoom7(direction);
                            break;

                        case 8:
                            gotoRoom8(direction);
                            break;


                        default:
                            flag = false;
                            break;
                    }
                }
            }
            finishGame();
        }
        else
            System.out.println("Maybe next time....");

    }

    public static void initRoom() {
        Random random = new Random();
        double randNo;

        /*
         * initialize each room with a random amount of money between 0 - 1000
         * using Hash Table where key is the name of a room
         */
        for (String room : roomNames) {
            // get a random number between 0.0 - 1,00000
            randNo = (double)random.nextInt(100000);

            // convert to $ amount
            randNo = randNo / 100.00;

            // save the amount where its key is the name of a room
            moneyInRoom.put(room, Double.toString(randNo));
        }
    }


    /*
     * Take the money in the room
     */
    public static void getMoney(String varRoom) {
        String tmp = moneyInRoom.get(varRoom);

        double amt = Double.parseDouble(tmp);

        moneyAtHand += amt;
        moneyInRoom.put(varRoom,"0.0");

        System.out.println("MoneyAtHand=" + moneyAtHand);
    }

    public static void gotoStart(char dir){

        switch (dir) {
            case 'N':
            case 'n':
                goRoom = 1;
                break;

            default:
                goRoom = 0;
                break;
        }
        
    }

    public static void gotoRoom1(char dir){


        switch (dir) {
            case 'N':
            case 'n':
                goRoom = 2;
                noRoomVisted++;
                break;

            case 'S':
            case 's':
                goRoom = -1;
                noRoomVisted++;
                break;


            default:
                goRoom = 1;
                break;
        }

    }

    public static void gotoRoom2(char dir) {
        switch (dir) {
            case 'W':
            case 'w':
                goRoom = 3;
                noRoomVisted++;
                break;

            case 'E':
            case 'e':
                goRoom = 4;
                noRoomVisted++;
                break;

            case 'S':
            case 's':
                goRoom = 1;
                noRoomVisted++;
                break;

            default:
                goRoom = 2;
                break;
        }
    }

    public static void gotoRoom3(char dir){

        switch (dir) {
            case 'N':
            case 'n':
                goRoom = 5;
                noRoomVisted++;
                break;

            case 'E':
            case 'e':
                goRoom = 2;
                noRoomVisted++;
                break;

            default:
                goRoom = 3;
                break;
        }
    }

    public static void gotoRoom4(char dir){
        switch (dir) {
            case 'N':
            case 'n':
                goRoom = 7;
                noRoomVisted++;
                break;

            case 'W':
            case 'w':
                goRoom = 2;
                noRoomVisted++;
                break;

            default:
                goRoom = 4;
                break;
        }
    }


    public static void gotoRoom5(char dir) {
        switch (dir) {
            case 'S':
            case 's':
                goRoom = 3;
                noRoomVisted++;
                break;
            default:
                goRoom = 5;
                break;
        }
    }

    public static void gotoRoom6(char dir){

        Random rnd = new Random();
        int chance;

        switch (dir) {
            case 'E':
            case 'e':
                /*
                 *
                 */
                if (secretKnown) {
                    System.out.println("Do you want to re-enter the secret room (Y/N)?");
                    Scanner keyB = new Scanner(System.in);
                    String msg = keyB.nextLine();
                    char ch = msg.charAt(0);
                    if ((ch == 'Y') || (ch == 'y')) {
                        goRoom = 8;
                    }
                    else {
                        goRoom = 7;
                    }
                } else {
                    chance = rnd.nextInt(4) + 1;

                    if (chance == 1) {
                        goRoom = 8;
                        //noRoomVisted++;
                    } else {
                        goRoom = 7;
                        //noRoomVisted++;
                    }
                }
                noRoomVisted++;
                break;

            default:
                goRoom = 6;
                break;
        }

    }

    public static void gotoRoom7(char dir){

        switch (dir) {
            case 'W':
            case 'w':
                goRoom = 6;
                noRoomVisted++;
                break;
            case 'S':
            case 's':
                goRoom = 4;
                noRoomVisted++;
                break;
            default:
                goRoom = 7;
                break;

        }

    }

    public static void gotoRoom8(char dir){
        /*
         * The secret room
         */
        secretKnown = true;
        switch (dir) {
            case 'W':
            case 'w':
                goRoom = 6;
                noRoomVisted++;
                break;
            default:
                goRoom = 8;
                break;

        }

    }



    public static void finishGame() {
        Random rand = new Random();
        int ghost = rand.nextInt(4); // random number from 0 to 3

        if (ghost == 0)
            System.out.println("You were followed by a ghost.");
        else
            System.out.println("You were not followed by a ghost.");

        System.out.print("You have visited "+ noRoomVisted + " rooms");

        if (secretKnown)
            System.out.println(" including the secret room.");
    }
}
