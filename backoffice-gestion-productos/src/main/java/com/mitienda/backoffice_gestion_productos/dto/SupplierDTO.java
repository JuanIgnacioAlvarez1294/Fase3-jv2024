package com.mitienda.backoffice_gestion_productos.dto;

public class SupplierDTO {

    private Long id;
    private String name;
    private String contactEmail;
    private String contactPhone;

    // Constructor vacío
    public SupplierDTO() {}

    // Constructor completo
    public SupplierDTO(Long id, String name, String contactEmail, String contactPhone) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
