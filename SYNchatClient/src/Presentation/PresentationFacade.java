/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Acquaintance.IBusiness;
import Acquaintance.IPresentation;
import Business.BusinessFacade;

/**
 *
 * @author Sigurd E. Espersen
 */
public class PresentationFacade implements IPresentation {
    
    private IBusiness Ibus;
    private SYNchat synchat;

    private static PresentationFacade instance = null;

    private PresentationFacade() {
    }

    public static PresentationFacade getInstance() {
        if (instance == null) {
            instance = new PresentationFacade();
        }
        return instance;
    }
    
    @Override
    public void injectBusiness(IBusiness bus) {
       this.Ibus = bus;
    }
    
    public int login(String mail, String pw) {
        return BusinessFacade.getInstance().login(mail, pw);
        //return Ibus.login(mail, pw);
    }
    
    public Boolean regUser(String tmpName, String mail, String pw) {
        return Ibus.regUser(tmpName, mail, pw);
    }
    
    //Call from Starter to SYNchat with System Startup command
    @Override
    public void startApplication(String[] args) {
        if (synchat == null) {
            synchat = new SYNchat();
        }
        synchat.startApplication(args);
    }

}