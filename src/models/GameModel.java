/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 999
 */
public class GameModel {
    
    /**
     * Группа объектов, участвующих в коллизиях
     */
    private ArrayList<Particle> players = new ArrayList<>();
    private ArrayList<Particle> AIplayers = new ArrayList<>();
    private ArrayList<Particle> agars = new ArrayList<>();
    //для отладки, стереть и никому не показывать
    int n = 0;
    /**
     * Число ботов
     */
    private final int botsCount = 10;
    
    /**
     * Число ботов
     */
    private final int agarCount = 100;
    
    /**
     * Массив контроллеров для игроков и ботов
     */
    private final List<Controller> controllers = new ArrayList<>();
    
    public GameModel(int maxWidth, int maxHeight) {
        for(int i = 0; i < botsCount; i++) {
            createBot(maxWidth, maxHeight);
        }
        for(int i = 0; i < agarCount; i++) {
            createAgar(maxWidth, maxHeight);
        }
        //createPlayer(maxWidth, maxHeight);
    }
    
    private void startGame() {
        
    }
    
    public void updateGame(Point mousePosition) {
        for(Controller controller : controllers) {
            controller.update(mousePosition);
        }
    }
    
    public Particle createPlayer(int maxWidth, int maxHeight) {
        Particle particle = new Particle(maxWidth, maxHeight);
        particle.setName("ivan");
        particle.setType("player");
        players.add( particle );
        controllers.add( new PlayerController(this, particle) );
        return particle;
    }
    
    public Particle createBot(int maxWidth, int maxHeight) {
        Particle particle = new Particle(maxWidth, maxHeight);
        //particle.setName("bot_" + (AIplayers.size() + 1));
        particle.setName("bot_" + n);
        n++;
        particle.setType("bot");
        AIplayers.add( particle );
        controllers.add( new AIController(this, particle) );
        return particle; 
    }
    
    public Particle createAgar(int maxWidth, int maxHeight) {
        Particle particle = new Particle(maxWidth, maxHeight);
        particle.setName("bot_" + (agars.size() + 1));
        particle.setType("agar");
        agars.add( particle );
        return particle; 
    }
    
    public void removeParticle(Particle p){
        if("player".equals(p.getType())){
            players.remove(p);
            System.out.print("I am the game model, i remove player");
        }
        else if("agar".equals(p.getType())){
            agars.remove(p);
            System.out.print("I am the game model, i remove agar");
        }
        else if("bot".equals(p.getType())){
            AIplayers.remove(p);
            System.out.print("I am the game model, i remove bot");
        }
    }
    public ArrayList<Particle> getBots() {
        return AIplayers;
    }
    
    public ArrayList<Particle> getPlayers() {
        return players;
    }
    
    public ArrayList<Particle> getAgars() {
        return agars;
    }
    
}
