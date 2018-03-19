package Ex3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex3Thread implements Runnable {

    private Socket msgSocket;

    private BufferedReader socketInput;
    private PrintWriter socketOutput;

    Message fromClient = new Message();
    Message toClient;

    private InputStream is;
    private ObjectInputStream objin;
    private OutputStream os;
    private ObjectOutputStream objout;

    public Ex3Thread(Socket inSocket) {
        this.msgSocket = inSocket;
        try{
            socketInput = new BufferedReader(new InputStreamReader(msgSocket.getInputStream()));
            socketOutput = new PrintWriter(msgSocket.getOutputStream(), true);
            setOutputStream();
            setInputStream();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            String line;
            socketOutput.println("Create a message in the format of:");
            socketOutput.println("Sender's Email Address");
            socketOutput.println("Receiver's Email Address");
            socketOutput.println("Subject");
            socketOutput.println("First Number");
            socketOutput.println("Second Number");
            try {
                line = socketInput.readLine();
                while (!line.equals("Done sending message to server")) {
                    if (line.equals("Sending message to server")) {
//                    System.out.println("in server message");
                        fromClient = getMsg();
                        showMsg(fromClient);
                    }
                    line = socketInput.readLine();
                }
//            System.out.println("sending to client");
//                socketOutput.println("Sending message to client");
                sendMessageToClient(fromClient);
//                socketOutput.println("Done sending message to client");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error getting message");
            }
        }
    }

    public void showMsg(Message in){
        System.out.println("-------------------------");
        System.out.println("Server received this:    ");
        System.out.println("From:                    "+in.sender);
        System.out.println("To:                      "+in.receiver);
        System.out.println("Subject:                 "+in.subject);
        System.out.println("Content:                 ");
        System.out.println("First Value:             "+in.content.firstValue);
        System.out.println("Second Value:            "+in.content.secondValue);
        System.out.println("Result:                  "+in.content.sumResult);
        System.out.println("-------------------------");
    }

    public void sendMessageToClient(Message in){
        socketOutput.println("Sending message to client");
        try {
            toClient = new Message(in.subject, in.receiver, in.sender, in.content.firstValue, in.content.secondValue);
            toClient.content.calcSum(in.content.firstValue, in.content.secondValue);
            objout.writeObject(toClient);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error sending message.");
        }
        socketOutput.println("Done sending message to client");
    }

    public void setOutputStream(){
        try {
            os = msgSocket.getOutputStream();
            objout = new ObjectOutputStream(os);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in output stream.");
        }
    }

    public void setInputStream(){
        try {
            is = msgSocket.getInputStream();
            objin = new ObjectInputStream(is);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error setting input stream");
        }
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
}
