package Ex3;

import java.io.*;
import java.net.Socket;

public class Message implements Serializable{
    String subject;
    String sender;
    String receiver;
    MathematicalContent content;

    public Message(){}

    public Message(String sub, String send, String rec, Double d1, Double d2) {
        this.subject = sub;
        this.sender = send;
        this.receiver = rec;
        this.content = new MathematicalContent(d1, d2);
    }

    public String toString()
    {
        return ("sub : " + subject + " sender: " + sender + " receiver: " + receiver +
                "content: "+content.toString());
    }

}


