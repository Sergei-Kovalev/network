package ru.ngs.summerjob;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(25225, 2000);
        System.out.println("Server is started.");

        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client).start();
        }
    }
}

class SimpleServer extends Thread {
    private Socket client;
    SimpleServer(Socket client) {
        this.client = client;
    }


    public void run() {
        handleRequest();
    }

    private void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            StringBuilder sb = new StringBuilder("Hello, ");
            String userName = br.readLine();
            System.out.println("Server got string: " + userName);
            Thread.sleep(2000);

            sb.append(userName);
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();

            bw.close();
            br.close();

            client.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
