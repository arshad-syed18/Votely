package com.example.voteme;

public class Users {

    String name;
    String email;
    String imageUri;

    public Users(String name, String email, String imageUri) {
        this.name = name;
        this.email = email;
        this.imageUri = imageUri;
    }
    @SuppressWarnings("UnusedDeclaration")
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }


    @SuppressWarnings("UnusedDeclaration")
    public Users() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @SuppressWarnings("UnusedDeclaration")
    public String getEmail() { return email; }
    @SuppressWarnings("UnusedDeclaration")
    public void setEmail(String email) { this.email = email; }

}
