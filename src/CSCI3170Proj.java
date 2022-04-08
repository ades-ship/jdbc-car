
import java.io.*;
import java.sql.*;
import java.lang.*;
import java.util.Scanner;

public class CSCI3170Proj {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        String host = "jdbc:mysql://localhost:3306/proj?autoReconnect=true&useSSL=false";
        String dbUsername = "root";
        String dbPassword = "vivekkohli935@gmail.com";

//        to createTable(host, dbUsername, dbPassword);
        master(inp, host, dbUsername, dbPassword);

    }

    static void master(Scanner inp, String host, String dbusr, String dbPass)
    {
        boolean run = true;
        while(run)
        {
            System.out.println("\n");
            System.out.println("Welcome to Car Rental System");
            System.out.println("Press : ");
            System.out.println("1 for Administrator Access");
            System.out.println("2 for User Access");
            System.out.println("3 for Manager Access");
            System.out.println("4 to exit");

            System.out.println();
            System.out.print("Enter your choice : ");

            switch(inp.nextInt())
            {
                case 1: admin(inp, host, dbusr, dbPass);
                    break;
                case 2:
                    break;
                case 3:
                        manager(inp, host, dbusr,dbPass);
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid Input!!");
            }
        }
        return;
    }



    static void admin(Scanner inp, String host, String dbusr, String dbPass)
    {
        /**
         * create all tables
         * delete all tables
         * load form datafile
         * Show number of records in each table
         * Return to the main menu
         * **/

        boolean run = true;
        while(run)
        {
            System.out.println("\n");
            System.out.println("Operations for Administrator menu");
            System.out.println("1. Create all tables\n" +
                    "2. Delete all\n" +
                    "3. Load from datafile\n" +
                    "4. Show number of records in each table\n" +
                    "5. Return to the main menu");
            System.out.println();
            System.out.println("Enter your choice : ");
            switch (inp.nextInt())
            {
                case 1: createTables(host, dbusr, dbPass);
                    break;
                case 2: deleteTables(host, dbusr, dbPass);
                    break;
                case 3:
                {
                    System.out.print("Type in the Source Data Folder Path : ");
                    String path = inp.nextLine();
//                    loadData(inp, path, host, dbusr, dbPass);
                }
                    break;
                case 4:
                    numOfRecinTab(host, dbusr, dbPass);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
        return;
    }

    static void createTables(String host, String dbusr, String dbPass)
    {
        String[] command = new String[]{
                "CREATE TABLE `user_category`(" +
                        "`ucid` int(1) UNSIGNED NOT NULL PRIMARY KEY," + //ucid PRIMARY KEY
                        "`max` int(1) UNSIGNED NOT NULL," +
                        "`period` int(2) UNSIGNED NOT NULL" +
                        ");",

                "CREATE TABLE `users`(" +
                        "`uid` varchar(12) NOT NULL PRIMARY KEY," + // uid PRIMARY KEY
                        "`name` varchar(25) NOT NULL," +
                        "`age` int(2) UNSIGNED NOT NULL," +
                        "`occupation` VARCHAR(20)," +
                        "`ucid` int(1) UNSIGNED NOT NULL" +
                        ");",

                "CREATE TABLE `car_category`(" +
                        "`ccid` int(1) UNSIGNED NOT NULL PRIMARY KEY," + // ccid PRIMARY KEY
                        "`ccname` VARCHAR(20) NOT NULL" +
                        ");",

                "CREATE TABLE `cars`(" +
                        "`callnum` VARCHAR(8) NOT NULL," +
//                    "`num_of_copies` int(1) UNSIGNED NOT NULL," +
                        "`name` VARCHAR(10) NOT NULL," +
//                    "`company` VARCHAR(25)  NOT NULL," +
                        "`manufacture` date NOT NULL," +
                        "`time_rent` int(2) UNSIGNED NOT NULL," +
                        "`ccid` int(1) UNSIGNED NOT NULL PRIMARY KEY" + // ccid PRIMARY KEY
                        ");",

                "CREATE TABLE `copy`(" +
                        "`callnum` VARCHAR(8) NOT NULL PRIMARY KEY," + // callnum PRIMARY KEY
                        "`copynum` int(1) UNSIGNED NOT NULL" +
                        ");",
                "CREATE TABLE `rent`(" +
                        "`uid` VARCHAR(10) NOT NULL PRIMARY KEY," +
                        "`callnum` VARCHAR(8) NOT NULL," +
                        "`copynum` int(1) NOT NULL," +
                        "`checkout` date NOT NULL," +
                        "`return` date NOT NULL" +
                        ");",

                "CREATE TABLE `produce`(" +
                        "`cname` VARCHAR(10) NOT NULL," +
                        "`callnum` VARCHAR(8) NOT NULL PRIMARY KEY" + // callnum PRIMARY KEY
                        ");"
        };

        exUpdate(command, host, dbusr, dbPass);
           return;
    }

    static void deleteTables(String host, String dbusr, String dbPass)
    {
        String[] tab =  {"user_category", "users", "car_category", "cars", "rent", "copy", "produce"};
        String query = "DROP TABLE IF EXISTS ";

        String[] command = new String[tab.length];
        for(int i = 0; i < tab.length; i++)
        {
            command[i] = query+tab[i];
        }
        exUpdate(command, host,dbusr,dbPass);
        return;
    }
    void loadData(String filePath, String l)
    {

        System.out.println("WIP!");
    }

    static void numOfRecinTab(String host, String dbusr, String dbPass)
    {
        try{
            Connection con = DriverManager.getConnection(host, dbusr, dbPass);
            Statement st = con.createStatement();

            String[] tab =  {"user_category", "users", "car_category", "cars", "rent"};
            String query = "SELECT * FROM ";
            System.out.println();

            for(String i : tab)
            {
                String s = query+i;
                ResultSet resultSet = st.executeQuery(s);
                resultSet.last();
                System.out.println(i +" have "+resultSet.getRow()+" rows.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

    static void manager(Scanner inp, String host, String dbusr, String dbPass) {
        boolean run = true;
        while(run)
        {
            System.out.println("\n");
            System.out.println("Operations for Manager menu");
            System.out.println("1. Car Renting\n" +
                    "2. Car Returning\n" +
                    "3. List all un-returned car copies which are checked-out within a period.\n"+
                    "4. Return to the main menu");
            System.out.println();
            System.out.println("Enter your choice : ");
            switch (inp.nextInt())
            {
                case 1: carRenting(inp, host, dbusr, dbPass);
                    break;
                case 2:
//                    carReturning(host, dbusr, dbPass);
                    break;
                case 3:
//                    listallunreturned(host, dbusr, dbPass);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
        return;
    }


    static void carRenting(Scanner inp, String host, String dbusr, String dbPass)
    {
        System.out.println("Enter the User ID : ");
        int usrid = inp.nextInt();
        System.out.println("Enter the Call Number : ");
        int callno = inp.nextInt();
        System.out.println("Enter the Copy Number : ");
        int copynum = inp.nextInt();


    }

    static void exUpdate(String[] commands, String host, String dbusr, String dbPass) {
        try {
            Connection con = DriverManager.getConnection(host, dbusr, dbPass);
            Statement st = con.createStatement();

            int resultSet;
            for(String c : commands)
            {
                resultSet = st.executeUpdate(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}





//            Connection con = DriverManager.getConnection(host, dbusr, dbPass);
//            Statement st = con.createStatement();
//            ResultSet resultSet = st.executeQuery("SELECT * FROM studentdb");
//            int resultSet = st.executeUpdate("DROP TABLE studentdb");
//            System.out.println(resultSet);
//            while(resultSet.next())
//            {
//                System.out.println(resultSet.getString("student_name"));
//            }
