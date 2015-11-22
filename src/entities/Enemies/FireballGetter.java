package entities.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entities.Animation;
import entities.Enemy;
import logic.ReadProperties;
import TileMap.TileMap;

/**
 * Clase que hereda de la clase Enemigo. Permite la instanciacion de un "Power Up" que le permite al 
 * jugador lanzar cohetes con mayor ataque de esta forma es mas f�cil matar a los enemigos. Los Jefes
 * de nivel solo requieren dos disparos si se ha obtenido este "Power Up" previamente.
 * Utiliza la variable took para poder identificar si el jugador ya ha tomado el objeto.
 * 
 * @author Fabian A. Solano Madriz
 * @version 2.0
 * 
 *
 */
public class FireballGetter extends Enemy {
	private BufferedImage[] sprites;
	
	public FireballGetter(TileMap tm){
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		took = false;
		
		try{
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(ReadProperties.file.getSetting("fgetter")));
			sprites = new BufferedImage[7];
			for (int i = 0; i < sprites.length;i++)
			{
				sprites[i] = spritesheet.getSubimage(i*width,0,width,height);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
	}
	public void update(){
		//Actualizar Posicion
		
		//getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		
		//Actualizar Animacion
		animation.update();
	}
	public void draw(Graphics2D g){
		//if (notOnScreen()) return;
		setMapPosition();
		super.draw(g); //Se llama al metodo de la clase padre para dibujo del Enemigo
	}
	
	public void takeWeapon(){
		took = true;
	}

}
