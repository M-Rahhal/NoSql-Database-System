import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void enterNameAndPassword() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        Socket socket = new Socket("127.0.0.1" , 4001);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(name);
        out.flush();
        out.writeUTF(password);
        out.flush();
        System.out.println("Node address:");

        System.out.println(in.readUTF());
        System.out.println("Token");
        System.out.println(in.readUTF());
    }

    public static void enterToken(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the address the node");
        String address = scanner.nextLine();
        System.out.println("Enter the port number of the port");
        int port = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a valid token");
        String token = scanner.nextLine();
        while (true){
            System.out.println("Enter 1 for sending a query and 2 to exit");
            String choice = scanner.nextLine();
            if (choice.equals("2"))
                return;
            if (!choice.equals("1")){
                System.out.println("wrong input");
                continue;
            }try {
                Socket socket = new Socket(address, port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Enter a query");
                String query = scanner.nextLine();
                out.writeUTF(token);
                out.flush();
                out.writeUTF(query);
                out.flush();
                System.out.println(in.readUTF());

            }catch (Exception e){
            }
        }

    }

    public static void main(String[] args) throws IOException {
        while (true){
            System.out.println("choose one of the following:");
            System.out.println("1-Enter a name and a password");
            System.out.println("2-Enter a valid token");
            System.out.println("3-Exit");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("1")){
                enterNameAndPassword();
            }
            else if (choice.equals("2")){
                enterToken();
            }
            else if (choice.equals("3"))
                return;
            else
                System.out.println("Wrong input");
        }
        /***
            String query = "create database student name,id";
            String query = "create document in student id=1,name=ayman";
            String query = "create document in student id=2,name=mohammad";
            String query = "create index in student name";
            String query = "create document in student id=3,name=rahhal";
            String query = "read document from student 1";
            String query = "update document from student 1 name=abdullah";
            String query = "delete document from student 1";
            String query = "delete database student";
        ***/
    }
}
