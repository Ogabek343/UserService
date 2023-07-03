package service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String name;
    private LocalDate birthDate;
}
