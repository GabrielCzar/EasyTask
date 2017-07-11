package com.easytask.service;

public interface ISecurityService {

    String findLoggedInUsername();

    boolean reAutenticar(String username, String password);
}
