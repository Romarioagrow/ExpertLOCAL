package expertshop.services;

import expertshop.domain.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log
@Service
@NoArgsConstructor
//@AllArgsConstructor
public class MailService {
    //private final JavaMailSender mailSender;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendOrderDetail(StringBuilder orderList, Integer orderID) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(mailFrom);
        mail.setTo("Lexx1994@gmail.com");
        mail.setSubject("Заказ №"+orderID);
        mail.setText(orderList.toString());

        mailSender.send(mail);
    }

    public void sendEmailToCustomer(Order order, StringBuilder orderList) {
        SimpleMailMessage mail = new SimpleMailMessage();

        String message = String.format(
                "Здравстуйте, %s! \n" +
                        "Ваш заказ №%s принят!\n" +
                        "Список товаров: \n",
                order.getName(),
                order.getOrderID()
        );
        message = message.concat(orderList.toString());

        mail.setFrom(mailFrom);
        mail.setTo(order.getEmail());
        mail.setSubject("Ваш заказ №"+order.getOrderID());
        mail.setText(message);

        mailSender.send(mail);
        log.info("Message sent to " + order.getEmail());
    }
}