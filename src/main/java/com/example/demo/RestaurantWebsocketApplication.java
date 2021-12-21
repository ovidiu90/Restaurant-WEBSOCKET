package com.example.demo;

import com.example.demo.config.ServerEndpoint;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.glassfish.tyrus.server.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantWebsocketApplication {

  public static void main(String[] args) {
    runServer();
  }

  public static void runServer() {
    Server server = new Server("localhost", 8025, "/websockets", ServerEndpoint.class);

    try {
      server.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Server running. \n Press any key to stop.\n");
      reader.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      server.stop();
    }
  }

}
