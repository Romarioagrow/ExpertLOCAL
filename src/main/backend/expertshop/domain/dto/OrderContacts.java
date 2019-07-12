package expertshop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderContacts {
    @NotBlank
    private String orderID;

    //@NotBlank(message = "firstName is mandatory")
    private String firstName;

    //@NotBlank(message = "lastName is mandatory")
    private String lastName;

    //@NotBlank(message = "username is mandatory")
    private String username;

    @Email(message = "not an email!")
    //@NotBlank(message = "email is mandatory")
    private String email;
}
