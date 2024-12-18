package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Role;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String agence;
    private Boolean isVerified;
}
