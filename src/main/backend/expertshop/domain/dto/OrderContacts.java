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

    @NotBlank(message = "modelName is mandatory")
    private String name;

    @NotBlank(message = "surname is mandatory")
    private String surname;

    @NotBlank(message = "email is mandatory")
    private String mobile;

    @Email(message = "not an email!")
    @NotBlank(message = "email is mandatory")
    private String email;
}
