/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agario;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.funbox.GameSettings;
import java.awt.Dimension;
import views.GameView;
import views.SettingsView;

/**
 *
 * @author 999
 */
public class AgarIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new SettingsView();
        GameLoader game = new GameLoader();
        game.setup(new GameView(), new Dimension(800,600), false);//Инициализация графического движка
        game.start();
    }
    
}
