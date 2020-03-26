package expertshop.domain.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class OrderContacts {
    @NotBlank
    private String orderID;

    @Pattern(regexp = "^[A-Za-zA-zА-яЁё]+$", message = "Имя должно содержать только буквы!")
    private String firstName;

    @Pattern(regexp = "^[A-Za-zA-zА-яЁё]+$", message = "Фамилия должна содержать только буквы!")
    private String lastName;

    private String username, otchestvo;

    @Email(message = "not an email!")
    private String email;

    private String city, street, house, apartment;
}
