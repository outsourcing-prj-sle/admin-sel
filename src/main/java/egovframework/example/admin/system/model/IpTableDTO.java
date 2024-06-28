package egovframework.example.admin.system.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IpTableDTO {
    private String allowedIp;
    private String adminId;
    private char active;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
}
