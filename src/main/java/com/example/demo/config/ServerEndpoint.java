package com.example.demo.config;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductsRepository;
import java.io.IOException;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;

@javax.websocket.server.ServerEndpoint(value = "/restaurant")
public class ServerEndpoint {

  @Autowired
  private ProductsRepository productsRepository;

  private Logger logger = Logger.getLogger(this.getClass().getName());

  @OnOpen
  public void onOpen(Session session) throws IOException {
    productsRepository = new ProductsRepository();
    System.out.println("Connected to client. Session id: " + session.getId());
    session.getBasicRemote().sendText("Welcome!");
  }

  @OnMessage
  public String onMessage(String fromClient, Session session) {
    switch (fromClient) {
      case "menu":
        String menu = productsRepository.getMenu();
        return menu;

      case "hi":
        return "hello";

      case "bye":
        try {
          session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Session closed."));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
    }
    Product product = productsRepository.getProduct(fromClient);

    return product.getName();
  }

  @OnClose
  public void onClose(Session session) {
    logger.info(String.format("Session %s closed.", session.getId()));
  }

}
