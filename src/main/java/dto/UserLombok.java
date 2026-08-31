package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString

public class UserLombok {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
