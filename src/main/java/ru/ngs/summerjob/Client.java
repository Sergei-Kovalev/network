package ru.ngs.summerjob;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            SimpleClient sc = new SimpleClient();
            sc.start();
        }
    }
}

class SimpleClient extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Client started:" + LocalDateTime.now());
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String sb = "Sergei";

            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("Client got string: " + answer);

            bw.close();
            br.close();
            System.out.println("Client finished:" + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
