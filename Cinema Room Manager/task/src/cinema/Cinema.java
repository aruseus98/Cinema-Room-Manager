package cinema;

import java.util.Scanner;

public class Cinema {

    private static char[][] cinema; //Cinema 2D Array
    private static int currentIncome = 0;
    private static int numberOfPurchasedTickets = 0;

    private static void initSeats(){
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }
    }

    private static void showSeats() {
        // Affiche la disposition actuelle des sièges
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void buyTickets(Scanner scanner){
        while(true){
            // Gère l'achat des billets
            // Demander les coordonnées du siège à l'utilisateur
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            if(rowNumber <= 0 || seatNumber <= 0 || rowNumber > cinema.length || seatNumber > cinema[0].length){
                System.out.println("Wrong input");
            }
            else if(cinema[rowNumber -1][seatNumber - 1] == 'B'){
                System.out.println("That ticket has already been purchased!");
            }
            else{
                int ticketPrice = calculatePrice(rowNumber);
                cinema[rowNumber - 1][seatNumber - 1] = 'B';
                currentIncome += ticketPrice;
                numberOfPurchasedTickets++;
                System.out.println("Ticket price: $" + ticketPrice);
                break;
            }
        }
    }

    private static int calculatePrice(int rowNumber){
        // Logique pour calculer le prix du billet
        int price = 10;
        if (cinema.length * cinema[0].length > 60 && rowNumber > cinema.length / 2) {
            price = 8;
        }
        return price;
    }

    private static void showStatistics(){
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        double percentage = (double) numberOfPurchasedTickets / (cinema.length * cinema[0].length) * 100;
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Current income: $" + currentIncome);
        int totalIncome = (cinema.length * cinema[0].length <= 60 ? 10 : (cinema.length / 2 * cinema[0].length * 10) + ((cinema.length - cinema.length / 2) * cinema[0].length * 8));
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        cinema = new char[rows][seats];
        initSeats();

        while (true){
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTickets(scanner);
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.print("Wrong input!");
                    break;
            }

        }
    }
}
