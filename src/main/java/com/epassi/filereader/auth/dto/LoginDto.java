package com.epassi.filereader.auth.dto;

public record LoginDto ( String login, String password) {
    @Override
    public String toString(){
        return "{\"login\" : \"".concat(login).concat("\" , \"password\" : \"").concat(password+"\" }");
    }
}