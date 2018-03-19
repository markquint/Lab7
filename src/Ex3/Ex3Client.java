package Ex3;

import java.io.*;
import java.net.Socket;

// Assume all necessary file are imported
public class Ex3Client {

    private PrintWriter socketOutput;
    private Socket aSocket;
    private BufferedReader stdIn;
    private BufferedReader socketInput;

    Message fromServer = new Message();
    Message myMsg;

    private InputStream is;
    private ObjectInputStream objin;
    private OutputStream os;
    private ObjectOutputStream objout;

    public Ex3Client(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketInput = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOutput = new PrintWriter((aSocket.getOutputStream()), true);
            setOutputStream();
            setInputStream();
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public void communicate() {
        double temp1 = 0;
        double temp2 = 0;
        String sender = "";
        String receiver = "";
        String subject = "";
        String line = "";
        String response;
        try {
            while(true){
                response = socketInput.readLine();
                System.out.println(response);
                if(response.equals("Second Number")){
//                    System.out.println("Getting inputs...");
                    try {
                        sender = stdIn.readLine();
//                        System.out.println("sender captured");
                        receiver = stdIn.readLine();
//                        System.out.println("receiver captured");
                        subject = stdIn.readLine();
//                        System.out.println("subject captured");
                        temp1 = Double.parseDouble(stdIn.readLine());
//                        System.out.println("content captured");
                        temp2 = Double.parseDouble(stdIn.readLine());
//                        System.out.println("end input");
                        myMsg = new Message(subject, sender, receiver, temp1, temp2);

//                        socketOutput.println("Sending message to server");
                        sendMessageToServer(myMsg);
//                        socketOutput.println("Done sending message to server");

                    } catch(IOException e){
                        e.printStackTrace();
                    } catch(NumberFormatException e){
                        e.printStackTrace();
                        System.out.println("invalid number");
                    }
                }

                if(response.equals("Sending message to client")){
                    while (!line.equals("Done sending message to client")) {
//                            System.out.println("in client message");
                            fromServer = getMsg();
                            showMsg(fromServer);
                        line = socketInput.readLine();
                    }
                }

                if(line.toUpperCase().equals("QUIT")){
                    break;
                }
            }
            stdIn.close();
            socketInput.close();
            socketOutput.close();
        } catch (IOException e) {
            System.out.println("closing error: " + e.getMessage());
        }
    }


    public String showMsg(Message in){
        String msg;
        System.out.println("-------------------------");
        System.out.println("Server returned this:    ");
        System.out.println("From:                    "+in.sender);
        System.out.println("To:                      "+in.receiver);
        System.out.println("Subject:                 "+in.subject);
        System.out.println("Content:                 ");
        System.out.println("First Value:             "+in.content.firstValue);
        System.out.println("Second Value:            "+in.content.secondValue);
        System.out.println("Result:                  "+in.content.sumResult);
        System.out.println("-------------------------");

        msg =   "-------------------------\n" +
                "Server returned this:    \n" +
                "From:                    \n" +
                "To:                      \n" +
                "Subject:                 \n" +
                "Content:                 \n" +
                "First Value:             \n" +
                "Second Value:            \n" +
                "Result:            \n" ;

        return msg;
    }

    public Message getMsg() throws IOException{
        Message in = null;
        try {
            in = (Message) objin.readObject();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Class not found exception caught.");
        }catch(Exception e){
            System.err.println("Cant get message");
        }
        return in;
    }

    public void sendMessageToServer(Message in){
        socketOutput.println("Sending message to server");
        try {
            objout.writeObject(in);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error sending message.");
        }
        socketOutput.println("Done sending message to server");
    }

    public void setOutputStream(){
        try {
            os = aSocket.getOutputStream();
            objout = new ObjectOutputStream(os);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error setting output stream.");
        }
    }

    public void setInputStream(){
        try {
            is = aSocket.getInputStream();
            objin = new ObjectInputStream(is);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error setting input stream");
        }
    }

    public static void main(String[] args) throws IOException {
        Ex3Client aClient = new Ex3Client("localhost", 9091);
        aClient.communicate();
    }
}
