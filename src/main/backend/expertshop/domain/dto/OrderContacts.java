package expertshop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class OrderContacts {
    @NotBlank
    private String orderID;

    //@NotBlank(message = "firstName is mandatory")
    @Pattern(regexp = "^[A-Za-zA-zА-яЁё]+$", message = "Имя должно содержать только буквы!")
    private String firstName;

    //@NotBlank(message = "lastName is mandatory")
    @Pattern(regexp = "^[A-Za-zA-zА-яЁё]+$", message = "Фамилия должна содержать только буквы!")
    private String lastName;

    //@NotBlank(message = "username is mandatory")
    private String username;

    private String otchestvo;

    @Email(message = "not an email!")
    //@NotBlank(message = "email is mandatory")
    private String email;

    private String city, street, house, apartment;
}
