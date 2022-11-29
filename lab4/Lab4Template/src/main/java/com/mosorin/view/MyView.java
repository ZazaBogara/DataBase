package com.mosorin.view;

import com.mosorin.controller.ChatController;
import com.mosorin.controller.MessageController;
import com.mosorin.controller.ServerController;
import com.mosorin.controller.UserController;
import com.mosorin.controller.impl.ChatControllerImpl;
import com.mosorin.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {

    @Autowired
    private ChatController chatController;
    @Autowired
    private UserController userController;
    @Autowired
    private ServerController serverController;
    @Autowired
    private MessageController messageController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final User nullUser = new User(null, null);
    private final Chat nullChat = new Chat(null, null, null);
    private final Server nullServer = new Server(null, null);
    private final Message nullMessage = new Message(null, null, null,null,null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: User");
        menu.put("11", "  11 - Create User");
        menu.put("12", "  12 - Update User");
        menu.put("13", "  13 - Delete from User");
        menu.put("14", "  14 - Find all Users");
        menu.put("15", "  15 - Find User by ID");
        menu.put("16", "  16 - Find User by user_name");
        menu.put("17", "  17 - Find All related Chats to user_id");

        menu.put("2", "   2 - Table: Server");
        menu.put("21", "  21 - Create Server");
        menu.put("22", "  22 - Update Server");
        menu.put("23", "  23 - Delete from Server");
        menu.put("24", "  24 - Find all Servers");
        menu.put("25", "  25 - Find Server by ID");
        menu.put("26", "  25 - Find Server by server_name");

        menu.put("3", "   3 - Table: Chat");
        menu.put("31", "  31 - Create Chat");
        menu.put("32", "  32 - Update Chat");
        menu.put("33", "  33 - Delete from Chat");
        menu.put("34", "  34 - Find all Chats");
        menu.put("35", "  35 - Find Chat by ID");
        menu.put("36", "  36 - Find all Chats by Name");
        menu.put("37", "  37 - Find all related Users to chat_id");


        menu.put("4", "   4 - Table: Message");
        menu.put("41", "  41 - Create Message");
        menu.put("42", "  42 - Update Message");
        menu.put("43", "  43 - Delete from Message");
        menu.put("44", "  44 - Find all Messages");
        menu.put("45", "  45 - Find Message by ID");
        menu.put("46", "  46 - Find Message by Text");


        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createUser);
        methodsMenu.put("12", this::updateUser);
        methodsMenu.put("13", this::deleteFromUser);
        methodsMenu.put("14", this::findAllUsers);
        methodsMenu.put("15", this::findUserById);
        methodsMenu.put("16", this::findUserByName);
        methodsMenu.put("17", this::findUserRelatedChats);

        methodsMenu.put("21", this::createServer);
        methodsMenu.put("22", this::updateServer);
        methodsMenu.put("23", this::deleteFromServer);
        methodsMenu.put("24", this::findAllServers);
        methodsMenu.put("25", this::findServerById);
        methodsMenu.put("26", this::findServerByName);

        methodsMenu.put("31", this::createChat);
        methodsMenu.put("32", this::updateChat);
        methodsMenu.put("33", this::deleteFromChat);
        methodsMenu.put("34", this::findAllChats);
        methodsMenu.put("35", this::findChatById);
        methodsMenu.put("36", this::findChatByName);
        methodsMenu.put("37", this::findChatRelatedUsers);

        methodsMenu.put("41", this::createMessage);
        methodsMenu.put("42", this::updateMessage);
        methodsMenu.put("43", this::deleteFromMessage);
        methodsMenu.put("44", this::findAllMessages);
        methodsMenu.put("45", this::findMessageById);
        methodsMenu.put("46", this::findMessageByText);
    }

    private void selectAllTable() {
        findAllServers();
        findAllUsers();
        findAllChats();
        findAllMessages();
    }
    // region Server ---------------------------------------------------
    private void createServer() {
        System.out.println("Input 'server_name': ");
        String serverName = input.nextLine();
        Server server = new Server(null, serverName);
        int count = serverController.create(server);
        System.out.printf("There are created %d rows\n", count);
    }
    private void updateServer() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'server_name': ");
        String serverName = input.nextLine();
        Server server = new Server(null, serverName);

        int count = serverController.update(id, server);
        System.out.printf("There are updated %d rows\n", count);
    }
    private void deleteFromServer() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = serverController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }
    private void findAllServers() {
        System.out.println("\nTable: SERVER");
        List<Server> servers = serverController.findAll();
        for (Server server : servers) {
            System.out.println(server);
        }
    }
    private void findServerById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Server> server = serverController.findById(id);
        System.out.println(server.orElse(nullServer));
    }
    private void findServerByName() {
        System.out.println("Input 'book_name': ");
        String bookName = input.nextLine();

        Optional<Server> server = serverController.findByName(bookName);
        System.out.println(server.orElse(nullServer));
    }

    // endregion
    // region User ---------------------------------------------------
    private void createUser() {
        System.out.println("Input 'user_name': ");
        String serverName = input.nextLine();
        User user = new User(null, serverName);
        int count = userController.create(user);
        System.out.printf("There are created %d rows\n", count);
    }
    private void updateUser() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'user_name': ");
        String userName = input.nextLine();
        User user = new User(null, userName);

        int count = userController.update(id, user);
        System.out.printf("There are updated %d rows\n", count);
    }
    private void deleteFromUser() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = userController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }
    private void findAllUsers() {
        System.out.println("\nTable: USER");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    private void findUserById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<User> user = userController.findById(id);
        System.out.println(user.orElse(nullUser));
    }
    private void findUserByName() {
        System.out.println("Input 'user_name': ");
        String userName = input.nextLine();

        Optional<User> user = userController.findByName(userName);
        System.out.println(user.orElse(nullUser));
    }
    private void findUserRelatedChats() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("\nTable: Chat");
        List<Chat> chats = userController.findRelatedChats(id);
        for (Chat chat : chats) {
            System.out.println(chat);
        }
    }
    // endregion
    // region Chat ---------------------------------------------------
    private void createChat() {
        System.out.println("Input 'chat_name': ");
        String chatName = input.nextLine();
        System.out.println("Input 'server_id': ");
        Integer server_id= input.nextInt();
        Chat chat = new Chat(null, chatName, server_id);
        int count = chatController.create(chat);
        System.out.printf("There are created %d rows\n", count);
    }
    private void updateChat() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'chat_name': ");
        String chatName = input.nextLine();
        System.out.println("Input 'server_id': ");
        Integer server_id= input.nextInt();
        Chat chat = new Chat(null, chatName, server_id);

        int count = chatController.update(id, chat);
        System.out.printf("There are updated %d rows\n", count);
    }
    private void deleteFromChat() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = chatController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }
    private void findAllChats() {
        System.out.println("\nTable: USER");
        List<Chat> chats = chatController.findAll();
        for (Chat chat : chats) {
            System.out.println(chat);
        }
    }
    private void findChatById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Chat> chat = chatController.findById(id);
        System.out.println(chat.orElse(nullChat));
    }
    private void findChatByName() {
        System.out.println("Input 'chat_name': ");
        String chatName = input.nextLine();

        Optional<Chat> chat = chatController.findByName(chatName);
        System.out.println(chat.orElse(nullChat));
    }
    private void findChatRelatedUsers() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("\nTable: User");
        List<User> users = chatController.findRelatedUsers(id);
        for (User user : users) {
            System.out.println(user);
        }
    }
    // endregion
    // region Message ---------------------------------------------------
    private void createMessage() {
        System.out.println("Input 'chat_id': ");
        Integer chat_id = input.nextInt();
        System.out.println("Input 'user_id': ");
        Integer user_id= input.nextInt();
        System.out.println("Input 'text': ");
        String text= input.nextLine();
        Date time = new Date();
        Message message = new Message(null, chat_id, user_id, text, time);
        int count = messageController.create(message);
        System.out.printf("There are created %d rows\n", count);
    }
    private void updateMessage() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'chat_id': ");
        Integer chat_id = input.nextInt();
        System.out.println("Input 'user_id': ");
        Integer user_id= input.nextInt();
        System.out.println("Input 'text': ");
        String text= input.nextLine();
        Date time = new Date();

        Message message = new Message(null, chat_id, user_id, text, time);

        int count = messageController.update(id, message);
        System.out.printf("There are updated %d rows\n", count);
    }
    private void deleteFromMessage() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = messageController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }
    private void findAllMessages() {
        System.out.println("\nTable: USER");
        List<Message> messages = messageController.findAll();
        for (Message message : messages) {
            System.out.println(message);
        }
    }
    private void findMessageById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Message> message = messageController.findById(id);
        System.out.println(message.orElse(nullMessage));
    }
    private void findMessageByText() {
        System.out.println("Input 'text': ");
        String text = input.nextLine();

        Optional<Message> message = messageController.findByText(text);
        System.out.println(message.orElse(nullMessage));
    }
    // endregion

    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {
        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //endregion
}
