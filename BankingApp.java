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
    
    static double[] accountBalance = new double[0];
    
    static int id=1;
    static String acNum;
    static int idRef;
    static int idRefFind = 0;
    static int deposits;


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

                    String[][] newAccounts = new String[accounts.length + 1][2];
                    double[] newAccountBalance = new double[accountBalance.length + 1];
                    String name;
                    boolean valid;
                    String ID=String.format("SDB-%05d",id);
                    System.out.println(ID);

                   
                    name = getUserInput("name");
                

                    do{
                        valid = true;
                        System.out.print("\tEnter Initial Deposit: ");
                        deposits = SCANNER.nextInt();
                       
                        SCANNER.nextLine();
                        if (deposits<5000){
                            System.out.printf(ERROR_MSG, "Insufficient Account Balance");
                            valid = false;
                        continue;
                        }
                    }while (!valid);
                    
                            
                    for (int i = 0; i < accounts.length; i++) {
                        newAccounts[i] = accounts[i];
                    }
                    
                    newAccounts[newAccounts.length - 1][0]= ID;
                    newAccounts[newAccounts.length - 1][1] = name;
                    accounts = newAccounts;

                    for (int i = 0; i < accountBalance.length; i++) {
                        newAccountBalance[i] = accountBalance[i];
                    }
                    
                    newAccountBalance[newAccountBalance.length-1] = deposits;
                    accountBalance = newAccountBalance;
                   

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                    String.format("%s:%s has been saved successfully",ID , name));

                        id++;
                    
                        System.out.print("\tDo you want to continue adding (Y/n)? ");
                    String input=SCANNER.nextLine();
                    
                    if (input.strip().toUpperCase().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;
            
       
                    case DIPOSITS:

                    idRefFind = accountNum(accounts, "");
                    System.out.printf("CurrentBalance : Rs. %,.2f\n", accountBalance[idRefFind]);

                    double amountDeposit = depositAmountValidate(accountBalance,idRefFind);
                    accountBalance[idRefFind] += amountDeposit;

                    System.out.printf("New Balance: Rs. %,.2f\n" , accountBalance[idRefFind]);

                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;

                case WITHDRAWLS:

                    idRefFind = accountNum(accounts,"");
                    System.out.printf("CurrentBalance : Rs. %,.2f\n", accountBalance[idRefFind]);

                    double amountWithdrawal = withdrawalAmountValidate(accountBalance,idRefFind);
                    accountBalance[idRefFind] -= amountWithdrawal;

                    System.out.printf("New Balance: Rs. %,.2f" , accountBalance[idRefFind]);

                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;

                case TRANSFER:

                    int idRefFindSender = accountNum(accounts,"from");
                    System.out.printf("From account name: %s",accounts[idRefFindSender][1]);
                    System.out.println();

                    int idRefFindRecepient;

                    do{
                        idRefFindRecepient = accountNum(accounts,"To ");
                        if(idRefFindSender!=idRefFindRecepient){
                            break;
                        }else{
                            System.out.printf(ERROR_MSG,"Invalid Enter!");
                        }
                    }while(true);
                    
                    System.out.printf("To account name: %s",accounts[idRefFindRecepient][1]);
                    System.out.println();
                    System.out.printf("From account balance: Rs. %,.2f",accountBalance[idRefFindSender]);
                    System.out.println();
                    System.out.printf("To account balance: Rs. %,.2f",accountBalance[idRefFindRecepient]);
                    System.out.println();

                    double amountTransfer;
                    do {
                        System.out.print("Enter Amount : Rs. ");
                        amountTransfer = SCANNER.nextDouble();
                        SCANNER.nextLine();
                        if(amountTransfer<100 || (accountBalance[idRefFindSender] - amountTransfer) < 500){
                            System.out.println("Minimum Withdrawal amount should be Rs. 100.00 & minimum account balance should be Rs. 500.00 ");
                        }
                        else break;
                    }while (true);

                    double amountAfterDeduct = (accountBalance[idRefFindSender] - amountTransfer) - amountTransfer*2/100;
                    System.out.printf("New From account balance: Rs. %,.2f",amountAfterDeduct );
                    System.out.println();

                    System.out.printf("New TO account balance: Rs. %,.2f",accountBalance[idRefFindRecepient] + amountTransfer);

                    System.out.println();
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;

                case CHECK_BALANCE:

                    idRefFind = accountNum(accounts,"");
                    System.out.printf("Name: %s",accounts[idRefFind][1]);
                    System.out.println();
                    System.out.printf("Current account balance: Rs. %,.2f",accountBalance[idRefFind]);
                    System.out.println();

                    if ((accountBalance[idRefFind] - 500)<500) System.out.println(ERROR_MSG+"Insufficient account balance!\n");

                    else System.out.printf("Available balance withdrawal: Rs. %,.2f",accountBalance[idRefFind] - 500);

                    System.out.println();
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    else screen = DASHBOARD;
                    break;

                case DELETE_ACCOUNT:

                    
            
                    int idRefDelete;
                    idRefDelete = accountNum(accounts,"");
                    String deleteId = accounts[idRefDelete][0];
                    String deleteName = accounts[idRefDelete][1];

                    System.out.printf("Name: %s",accounts[idRefDelete][1]);
                    System.out.println();
                    System.out.printf("Current account balance: Rs. %,.2f",accountBalance[idRefDelete]);
                    System.out.println();
                    System.out.print("\tAre you sure to delete?  (Y/n)? ");

                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) {
                        String[][] accountsTemp = new String[accounts.length - 1][2];
                        double[] accountBalanceTemp = new double[accountBalance.length-1];
                        //assign values
                        for (int i = 0; i < accounts.length; i++) {
                            if (i < idRefDelete) {
                                accountsTemp[i][0] = accounts[i][0];
                                accountsTemp[i][1] = accounts[i][1];
                                accountBalanceTemp[i] = accountBalance[i];
                            } else if (idRefDelete == i) {
                                continue;
                            } else {
                                accountsTemp[i - 1][0] = accounts[i][0];
                                accountsTemp[i - 1][1] = accounts[i][1];
                                accountBalanceTemp[i - 1] = accountBalance[i];
                            }
                        }
                        accounts = accountsTemp;
                        accountBalance = accountBalanceTemp;

                        System.out.printf("A/C: %s Name: %s deleted successfully. ",deleteId,deleteName);

                    }
                    else screen = DASHBOARD;
                    //break;

                
                System.out.println();
                System.out.print("\tDo you want to continue adding (Y/n)? ");
                if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                else screen = DASHBOARD;
                break;
             }
        }while(true);

    }
     public static int accountNum(String[][] acc,String content){ 
                    
                    boolean valid;
                    idRef = -1;
                     do{
                        valid = true;
                        System.out.printf("\tEnter %s Account Number: ",content);
                        acNum = SCANNER.nextLine();
                        
                        if (acNum.isBlank()){
                            System.out.printf(ERROR_MSG, "Account number can't be empty");
                            valid = false;
                            continue;
                        }

                        else if (!(acNum.startsWith("SDB-") && acNum.length() == 9)){
                        System.out.printf(ERROR_MSG, "Invalid ID format");
                        valid = false;
                        continue;
                        }
                        
                        String number = acNum.substring(4);
                        for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))){
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    valid = false;
                                    continue;
                                    
                                    //break;
                                 }
                        }
                        
                        for(int i=0; i < acc.length; i++){
                            if(acNum.equals(acc[i][0])){
                                idRef=i;
                                valid=true;
                                break;
                            }     
                            // break;                                                  
                        }
                        

                        if(idRef==(-1))
                            System.out.printf(ERROR_MSG,"ID not found");
                            //  valid=false;
                            
                        
                       

                    }while(!valid);
                    
                    return idRef;
        
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
                    
                               
                                if (value.isBlank()){
                                    valid = false;
                                    System.out.printf("%s can't be empty \n", input.substring(0, 1).toUpperCase()
                                .concat(input.substring(1)));
                                    continue;
                                }
                    
                                
                                for (int i = 0; i < value.length(); i++) {
                                    if (!(Character.isLetter(value.charAt(i)) || 
                                    Character.isSpaceChar(value.charAt(i)))){
                                        valid = false;
                                        System.out.printf("Invalid %s \n", input);
                                        continue inputValidation;
                                    }
                                }            
                            }while (!valid);
                            return value;
                        }

    public static double depositAmountValidate(double[] accountBalance , int idRefFind){

            double amountDeposit=0;
            do{
                System.out.print("Deposite Amount : ");
                amountDeposit = SCANNER.nextDouble();
                SCANNER.nextLine();
                if(amountDeposit<500){
                    System.out.printf(ERROR_MSG,"Insufficent Balance for Deposit..Minimum Deposit amount : Rs 500.00/=");
                    continue;
                }else{
                    break;
                }
            }while(true);

            return amountDeposit;
     }

        

    public static double withdrawalAmountValidate(double[] accountBalance , int idRefFind){
            double amountWithdrawal=0;
            do {
                System.out.print("Withdrawal Amount : ");
                amountWithdrawal = SCANNER.nextDouble();
                SCANNER.nextLine();
                if(amountWithdrawal<100 || accountBalance[idRefFind] - amountWithdrawal < 500){
                    System.out.println("Minimum Withdrawal amount shold be Rs. 100.00 & minimum account balance should be Rs. 500.00 ");
                 }
                else break;
            }while (true);
            return amountWithdrawal;
    }


    }
