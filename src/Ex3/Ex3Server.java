package Ex3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Assume all necessary file are imported
public class Ex3Server {
    PrintWriter out;
    Socket aSocket;
    ServerSocket serverSocket;
    BufferedReader in;
    ExecutorService pool;

    public Ex3Server() { // throws IOException {
        try {
            serverSocket = new ServerSocket(9091);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            System.out.println("Create the new socket error");
        }
        System.out.println("Server is running");
    }

    public void communicate() throws IOException{
        try {
            String line = "";
            while (true) {

                aSocket = serverSocket.accept();
                System.out.println("after accept");
                in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                out = new PrintWriter((aSocket.getOutputStream()), true);

                Ex3Thread thread = new Ex3Thread(aSocket);

                pool.execute(thread);

            }
        } catch(IOException e){
            System.out.println(e.getMessage());
            // Stop accepting new games and finish any active ones, then shutdown the threadpool.
            pool.shutdown();
            try {
                in.close();
                out.close();
                aSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Ex3Server myserver = new Ex3Server();
        myserver.communicate();
    }
}
