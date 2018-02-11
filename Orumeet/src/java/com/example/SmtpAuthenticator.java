/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author David
 */
public class SmtpAuthenticator extends Authenticator{

    /**
     *
     */
    public SmtpAuthenticator() {
        super();
    }
    
    /**
     *
     * @return
     */
    //Creating a class for handling the Account information
    @Override
    public PasswordAuthentication getPasswordAuthentication(){
        String username = "david.w91@hotmail.com";
        String password = "Terminated-97889";
        if((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)){
            return new PasswordAuthentication(username, password);
        }
        return null;
    }
}
