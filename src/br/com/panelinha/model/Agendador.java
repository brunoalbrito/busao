/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.panelinha.model;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author 41583469
 */
public class Agendador extends TimerTask{
    
    private Date instanteAtual;

    @Override
    public void run() {
        instanteAtual = new Date();
        System.out.println(instanteAtual);
    }
    
}
