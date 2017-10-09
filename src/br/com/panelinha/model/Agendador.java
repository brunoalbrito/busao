package br.com.panelinha.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        System.out.println("Onde está o busao?");
        Onibus onibus = new  Onibus("Peri peri", 10, 10);
        PontoOnibus pontoOnibus = new PontoOnibus("Raposo Tavares", 0, 0);
        System.out.println(onibus);
        System.out.println(pontoOnibus);
    }
    
}
