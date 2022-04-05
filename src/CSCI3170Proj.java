import java.sql.*;
import java.lang.*;
import java.util.Scanner;

public class CSCI3170Proj {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        String host = "jdbc:mysql://localhost:3306/proj?autoReconnect=true&useSSL=false";
        String dbUsername = "root";
        String dbPassword = "vivekkohli935@gmail.com";

//        createTable(host, dbUsername, dbPassword);
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
        try{
            Connection con = DriverManager.getConnection(host, dbusr, dbPass);
            Statement st = con.createStatement();

            int resultSet;
            // user_category
            resultSet = st.executeUpdate("CREATE TABLE `user_category`(" +
                    "`user_cat` int(1) UNSIGNED NOT NULL," +
                    "`max_cars` int(1) UNSIGNED NOT NULL," +
                    "`loan_period` int(2) UNSIGNED NOT NULL" +
                    ");");


            // users
            resultSet = st.executeUpdate("CREATE TABLE `users`(" +
                    "`user_id` int(1) UNSIGNED NOT NULL," +
                    "`name` int(1) UNSIGNED NOT NULL," +
                    "`age` int(2) UNSIGNED NOT NULL," +
                    "`occupation` VARCHAR(20)," +
                    "`user_cat` int(1) UNSIGNED NOT NULL" +
                    ");");


            //car_category
            resultSet = st.executeUpdate("CREATE TABLE `car_category`(" +
                    "`car_cat_id` int(1) UNSIGNED NOT NULL," +
                    "`car_cat` VARCHAR(20) NOT NULL" +
                    ");");

            //cars
            resultSet = st.executeUpdate("CREATE TABLE `cars`(" +
                    "`call_num` VARCHAR(8) NOT NULL," +
                    "`num_of_copies` int(1) UNSIGNED NOT NULL," +
                    "`car_name` VARCHAR(10) NOT NULL," +
                    "`company` VARCHAR(25)  NOT NULL," +
                    "`dom` date NOT NULL," +
                    "`times_rented` int(2) UNSIGNED NOT NULL," +
                    "`car_cat_id` int(1) UNSIGNED NOT NULL" +
                    ");");


            //rent
            resultSet = st.executeUpdate("CREATE TABLE `rent`(" +
                    "`call_num` VARCHAR(8) NOT NULL," +
                    "`copy_num` int(1) UNSIGNED NOT NULL," +
                    "`user_id` VARCHAR(10) NOT NULL," +
                    "`rent_date` date NOT NULL," +
                    "`return_date` date NOT NULL" +
                    ");");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

    static void deleteTables(String host, String dbusr, String dbPass)
    {
        try{
            Connection con = DriverManager.getConnection(host, dbusr, dbPass);
            Statement st = con.createStatement();
            String[] tab =  {"user_category", "users", "car_category", "cars", "rent"};
            String query = "DROP TABLE IF EXISTS ";
            for(String i : tab)
            {
                String s = query+i;
                int resultSet = st.executeUpdate(s);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return;
    }
    void LoadData()
    {
        System.out.println("WIP!");
        return;
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