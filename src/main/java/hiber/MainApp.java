package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("Pavel", "Fedinishin", "fallenangel@gmail.com");
      User user2 = new User("Des", "Krevniy", "rot@gmail.com");
      User user3 = new User("Mishail", "Diakov", "badarda@gmail.com");


      user1.setCar(new Car(514, "адская колесница"));
      user2.setCar(new Car(7654, "труповозка"));
      user3.setCar(new Car(827, "танк"));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar(514, "адская колесница"));


      context.close();
   }
}
