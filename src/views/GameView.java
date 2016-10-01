/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.TileBackground;
import events.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import models.GameModel;
import models.Particle;

/**
 *
 * @author 999
 */
public class GameView extends Game {
    
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    private int[][] tiles = new int[WIDTH][HEIGHT];
    
    private final PlayField field = new PlayField();
    
    /**
     * Фон игры
     */
    private TileBackground bg = null;
    
    
    private GameModel game = new GameModel(WIDTH, HEIGHT);
    
    ArrayList<SpriteView> Sprites = new ArrayList<>();

    @Override
    public void initResources() {
        
        
        loadBots();
        loadPlayers();
        loadAgars();

        bg = new TileBackground(getImages("img/background.png", 1, 1), tiles);
        bg.setClip(0, 0, this.dimensions().width, this.dimensions().height);

        field.setBackground(bg);
    }

    @Override
    public void update(long l) {
        game.updateGame(mousePosition());
        bg.update(l);
        field.update(l);
    }

    @Override
    public void render(Graphics2D g) {
        bg.render(g);
        field.render(g);
        if (Sprites.get(0) != null) {
            bg.setToCenter(field.getGroup("bot_0").getActiveSprite());
        }
    }
    
    /**
     * Текущая позиция координат мыши
     * @return 
     */
    public Point mousePosition() {
        Point p = new Point(this.getMouseX(), this.getMouseY());
        p.x += bg.getX();
        p.y += bg.getY();
        return p;
    }
    
    private void loadBots() {
        for( Particle particle : game.getBots() ) {
            AIView ai = new AIView( particle );
            ai.particle.setGameListener(new GameObserver());
            field.addGroup(ai.getGroup());
            for(SpriteView sprite : Sprites ) {
                field.addCollisionGroup( ai.getGroup(), sprite.getGroup(), new Collision());
            }
            Sprites.add(ai);
        }
    }
    
    private void loadPlayers() {
        for( Particle particle : game.getPlayers()) {
            PlayerView player = new PlayerView( particle );
            player.particle.setGameListener(new GameObserver());
            field.addGroup(player.getGroup());
            for(SpriteView sprite : Sprites ) {
                field.addCollisionGroup( player.getGroup(), sprite.getGroup(), new Collision());
            }
            Sprites.add(player );
        }
    }
    
    private void loadAgars() {
        for( Particle particle : game.getAgars()) {
            AgarView agar = new AgarView( particle );
            agar.particle.setGameListener(new GameObserver());
            field.addGroup(agar.getGroup());
            for(SpriteView sprite : Sprites ) {
                field.addCollisionGroup( agar.getGroup(), sprite.getGroup(), new Collision());
            }
            Sprites.add( agar );
        }
    }
    
    /**
     * Возвращает размеры окна для изображения
     * @return 
     */
    public Dimension dimensions() {
        return new Dimension(WIDTH, HEIGHT);
    }
    
    protected class GameObserver implements GameListener{

        @Override
        public void ParticleDied(GameEvent e) {
            SpriteGroup group = field.getGroup(e.getParticle().getName());
            field.removeGroup(group);
        }
    }
    
}
