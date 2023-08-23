import java.util.Arrays;
import java.util.Scanner;

public class BankingApp{
    private static final Scanner SCANNER = new Scanner(System.in);
    final static String CLEAR = "\033[H\033[2J";
    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String COLOR_GREEN_BOLD = "\033[33;1m";
    final static String RESET = "\033[0m";

    final static String DASHBOARD = " Welcome to Smart Banking";
    final static String CREATE_ACCOUNT = "Create New Account";
    final static String DIPOSITS = "Deposits";
    final static String WITHDRAWLS = "Withdrawls";
    final static String TRANSFER = "Transfer";
    final static String CHECK_BALANCE = "Check Account Balance";
    final static String DELETE_ACCOUNT = "Delete Account";

    final static String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
    final static String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

    static String[][] accounts = new String[0][];
    static String[][] newAccounts = new String[accounts.length + 1][2];


    public static void main(String[] args) {
       

        String screen = DASHBOARD;
        
        do{
            final String APP_TITLE = String.format("%s%s%s",
            COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\t[1]. Create New Account");
                    System.out.println("\t[2]. Deposits");
                    System.out.println("\t[3]. Withdrawls");
                    System.out.println("\t[4]. Transfer");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Delete Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = CREATE_ACCOUNT; break;
                        case 2: screen = DIPOSITS; break;
                        case 3: screen = WITHDRAWLS; break;
                        case 4: screen = TRANSFER; break;
                        case 5: screen = CHECK_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;

                    case CREATE_ACCOUNT:
                    String id;
                    String name;
                    boolean valid;
                    System.out.printf(id="Account ID: SDB-%05d ", (accounts.length + 1));

                   
                     name = getUserInput("name");
                  
                    

                    do{
                        valid = true;
                        System.out.print("\tEnter Initial Deposit: ");
                        int deposits = SCANNER.nextInt();
                        if (deposits<5000){
                            System.out.printf(ERROR_MSG, "Insufficient Account Balance");
                            valid = false;
                        continue;
                        }

                            
                            for (int i = 0; i < accounts.length; i++) {
                                newAccounts[i] = accounts[i];
                            }
                    
                    newAccounts[newAccounts.length - 1][0]= id;
                    newAccounts[newAccounts.length - 1][1] = name;
                    accounts = newAccounts;
                    

                   

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    String input=SCANNER.nextLine();
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;}while (!valid);
                    }
                
                

        }while(true);

        case DIPOSITS:
                    //int acNum;
        
                    boolean valid;
       
      
        
                    do{
                        valid = true;
                        System.out.print("\tEnter Account Number: ");
                        //acNum = SCANNER.nextInt();
                        // if (acNum.isBlank()){
                        //     System.out.printf(ERROR_MSG, "Account number can't be empty");
                        //     valid = false;
                        //     continue;
                        // }
                        // for (int i = 0; i < acNum.length(); i++) {
                        //     if (!(Character.isDigit(name.charAt(i)) || 
                        //         Character.isSpaceChar(name.charAt(i))) ) {
                        //         System.out.printf(ERROR_MSG, "Invalid number");
                        //         valid = false;
                        //         break;
                        //     }
                       
                      
                       
                    }while (!valid);
    }
     public static String getUserInput(String input){
                            boolean valid;
                            String value;
                    
                            inputValidation:
                            do {
                                valid = true;
                                System.out.printf("Enter your %s: ", 
                                input.substring(0, 1).toUpperCase()
                                .concat(input.substring(1)));
                                value = SCANNER.nextLine().strip();
                    
                                /* Empty */
                                if (value.isBlank()){
                                    valid = false;
                                    System.out.printf("%s can't be empty \n", input.substring(0, 1).toUpperCase()
                                .concat(input.substring(1)));
                                    continue;
                                }
                    
                                /* Format (Letters and Spaces) */
                                for (int i = 0; i < value.length(); i++) {
                                    if (!(Character.isLetter(value.charAt(i)) || 
                                    Character.isSpaceChar(value.charAt(i)))){
                                        valid = false;
                                        System.out.printf("Invalid %s \n", input);
                                        continue inputValidation;
                                    }
                                }            
                            }while (!valid);
                            return value;} 
                                
                        }