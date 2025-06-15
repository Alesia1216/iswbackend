package net.ausiasmarch.iswart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDTO {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
