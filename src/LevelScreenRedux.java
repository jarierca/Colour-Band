
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Time;
import java.util.Timer;
import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LevelScreenRedux extends BaseScreen{
    Player band;
    
    float volumenMusica = 0;
    float volumenEfecto = 0;
    
    boolean gameOver;
    int coins;
    float time;
    int mana;
    boolean coinx2 = false;
    int health;
    
    Sound coinSound;
    Sound fireballSound;
    Sound daño;
    Sound estrella;
    Sound vidaMas;
    
    static Music music;
    
    float tiempoBooster = 15;
    float actTiempoBooster = 0;
    
    float tiempoTripleCoin = 15;
    float actTiempoTripleCoin = 0;
    
    boolean recibirDaño = true;
    float tiempoVida = 3;
    float actTiempoVida = 0;
    
    boolean dispararBola = true;
    float tiempoDisparo = 0.6f;
    float actTiempoDisparo = 0;

    Label coinLabel;
    Label timeLabel;
    Label messageLabel;
    Label lblJumpBoost;
    Label lblTripleCoin;
    Label lblHealth;
    Label lblMana;
    
    BaseActor iconBigCoin1;
    BaseActor iconBigCoin2;
    BaseActor iconBigCoin3;
    
    private Animation<TextureRegion> TRcoin1;
    private Animation<TextureRegion> TRcoin2;
    private Animation<TextureRegion> TRcoin3;
    
    static String tieneBigCoin1Demo = "no";
    static String tieneBigCoin2Demo = "no";
    static String tieneBigCoin3Demo = "no";
    
    Table keyTable;
    
    ArrayList<Color> keyList;
    
    static boolean juegoEnMarcha = false;

    public void initialize() {        
    	Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
        TilemapActor tma = new TilemapActor("assets/MapaRedux.tmx", mainStage);
         
        volumenMusica = MenuScreenMusica.getNivelMusica();
        System.out.println("Volumen Musica"+volumenMusica);
        
        volumenEfecto = MenuScreenMusica.getNivelEfectos();
        System.out.println("Volumen Efecto"+volumenEfecto);
        
        coinSound = Gdx.audio.newSound(Gdx.files.internal("assets/coin.wav"));
        fireballSound = Gdx.audio.newSound(Gdx.files.internal("assets/fireball2.mp3"));
        daño = Gdx.audio.newSound(Gdx.files.internal("assets/classic_hurt.mp3"));
        estrella = Gdx.audio.newSound(Gdx.files.internal("assets/jumping-mike-mario.mp3"));
        vidaMas = Gdx.audio.newSound(Gdx.files.internal("assets/vidaMas.mp3"));
        
        music = Gdx.audio.newMusic(Gdx.files.internal("assets/Getting it Done.mp3"));
        music.setLooping(true);
        System.out.println(volumenMusica);
        music.setVolume(volumenMusica);
        music.play();

        for (MapObject obj : tma.getRectangleList("Solid") ) {
            MapProperties props = obj.getProperties();
            new Solid( (float)props.get("x"), (float)props.get("y"),
                (float)props.get("width"), (float)props.get("height"), 
                mainStage );
        }
        for (MapObject obj : tma.getRectangleList("Muerte") ) {
            MapProperties props = obj.getProperties();
            new Muerte( (float)props.get("x"), (float)props.get("y"),
                (float)props.get("width"), (float)props.get("height"), 
                mainStage );
        }
        

        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        band = new Player( (float)startProps.get("x"), (float)startProps.get("y"), mainStage);

        for (MapObject obj : tma.getTileList("Meta") ){
            MapProperties props = obj.getProperties();
            
            new Meta( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Lava") ){
        	
            MapProperties props = obj.getProperties();
            new Lava( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
       
        for (MapObject obj : tma.getTileList("TripleCoin") ){
        	
            MapProperties props = obj.getProperties();
            new TripleCoin( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("JumpBoost") ){
        	
            MapProperties props = obj.getProperties();
            new JumpBoost( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getRectangleList("Enemy1Solid") ) {
            MapProperties props = obj.getProperties();
            new Enemy1Solid( (float)props.get("x"), (float)props.get("y"),
                (float)props.get("width"), (float)props.get("height"), 
                mainStage );
        }
               
        for (MapObject obj : tma.getTileList("Coin") ){
        	
            MapProperties props = obj.getProperties();
            new Coin( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("BigCoin1") ){
        	
            MapProperties props = obj.getProperties();
            new BigCoin1( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("BigCoin2") ){
        	
            MapProperties props = obj.getProperties();
            new BigCoin2( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("BigCoin3") ){
	
		    MapProperties props = obj.getProperties();
		    new BigCoin3( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Vida") ){
        	
            MapProperties props = obj.getProperties();
            new Vida( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("FireballItem") ){
        	
            MapProperties props = obj.getProperties();
            new Fireballitem( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Enemy1") ){
        	
            MapProperties props = obj.getProperties();
            new Enemy1( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Enemy2") ){
        	
            MapProperties props = obj.getProperties();
            new Enemy2( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        for (MapObject obj : tma.getTileList("Enemy3") ){
        	
            MapProperties props = obj.getProperties();
            new Enemy3( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Enemy4") ){
        	
            MapProperties props = obj.getProperties();
            new Enemy4( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Enemy8") ){
        	
            MapProperties props = obj.getProperties();
            new Enemy8( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
        
        for (MapObject obj : tma.getTileList("Enemy9") ){
        	
            MapProperties props = obj.getProperties();
            new Enemy9( (float)props.get("x"), (float)props.get("y"), mainStage );  
        }
        

        for (MapObject obj : tma.getTileList("Platform") ) {
        	
            MapProperties props = obj.getProperties();
            
            new Platform( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        //HUD
        band.toFront();

        gameOver = false;
        coins = 0;
        health = 5;
        time = 180;
        mana = 10;
        
   /*     keyTable = new Table();
        
        BaseActor keyIcon =  new BaseActor(0,0,uiStage);
        keyIcon.loadTexture("assets/items/hudCoin.png");
        keyTable.add(keyIcon);
        */
        
        BaseActor coinIcon = new BaseActor(0,0,uiStage);
        coinIcon.loadAnimationFromSheet("assets/items/coinAn.png", 1, 6, 0.1f,true);
        
        BaseActor healthIcon = new BaseActor(0,0,uiStage);
        healthIcon.loadAnimationFromSheet("assets/items/heart2.png", 1, 2, 0.6f,true);
        
        BaseActor fireballIcon = new BaseActor(0,0,uiStage);
        fireballIcon.loadAnimationFromSheet("assets/items/fireball.png", 1, 2,0.3f,true);
        
        iconBigCoin1 = new BaseActor(0,0,uiStage);
        iconBigCoin1.loadTexture("assets/items/BigCoin0.png");
        TRcoin1 = iconBigCoin1.loadTexture("assets/items/CoinBig1.png");
        
        iconBigCoin2 = new BaseActor(0,0,uiStage);
        iconBigCoin2.loadTexture("assets/items/BigCoin0.png");
        TRcoin2 = iconBigCoin2.loadTexture("assets/items/CoinBig1.png");
        
        iconBigCoin3 = new BaseActor(0,0,uiStage);
        iconBigCoin3.loadTexture("assets/items/BigCoin0.png");
        TRcoin3 = iconBigCoin3.loadTexture("assets/items/CoinBig1.png");
 
        //Info Monedas
        coinLabel = new Label(" x "+coins, BaseGame.labelStyle);
        coinLabel.setColor(Color.GOLD);
        
        //Info Power UP Salto
        lblJumpBoost = new Label(" ", BaseGame.labelStyle);
        lblJumpBoost.setColor(Color.GREEN);
        
        //Info Power Up Triple Monedas
        lblTripleCoin = new Label(" ", BaseGame.labelStyle);
        lblTripleCoin.setColor(Color.YELLOW);
        
        //Info mana
        lblMana = new Label(" x "+mana, BaseGame.labelStyle);
        lblMana.setColor(Color.ORANGE);
        
        //Info Vida
        lblHealth = new Label(" x " + health, BaseGame.labelStyle);
        lblHealth.setColor(Color.PINK);
        
        //Info Tiempo
        timeLabel = new Label("Time: " + (int)time, BaseGame.labelStyle);
        timeLabel.setColor(Color.LIGHT_GRAY); 
		
        BaseActor menu = new BaseActor(0, 0, pausaStage);
        menu.loadTexture("assets/samplePause.png");
		menu.setSize(screenSize.width,screenSize.height);
		
		uiTable.pad(11);  
        uiTable.add(healthIcon);
        uiTable.add(lblHealth);
        uiTable.add().expandX();
        uiTable.add(fireballIcon);
        uiTable.add(lblMana);
        uiTable.add().expandX();
        uiTable.add(iconBigCoin1);
        uiTable.add(iconBigCoin2);
        uiTable.add(iconBigCoin3);
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add(coinIcon);
        uiTable.add(coinLabel);
        uiTable.add().expandX();
        uiTable.row();
        uiTable.add().colspan(15);
        uiTable.add(lblJumpBoost);
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.row();
        uiTable.add().colspan(15);
        uiTable.add(lblTripleCoin);
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        uiTable.add().expandX();
        /** 
         * Timer
         */
       // uiTable.add(timeLabel);
        uiTable.row();
        uiTable.add().colspan(3).expandY();

        keyList = new ArrayList<Color>();
    }

    public void update(float dt){
    	volumenEfecto = MenuScreenMusica.getNivelEfectos();	
    	
        if ( gameOver )
            return;
        /**
         * Colisiones de la Meta con el Jugador
         */
        for (BaseActor flag : BaseActor.getList(mainStage, "Meta")){
            if ( band.overlaps(flag) ) {   

                band.remove();
                ColourBand.setActiveScreen( new LevelScreenCavern() );
                MenuScreen.comprobarPropertiesPintar();
                gameOver = true;
                music.pause();
            }
        }
        /**
         * Colisiones de las Monedas con el Jugador
         */
        for (BaseActor coin : BaseActor.getList(mainStage, "Coin")) {
        	
            if ( band.overlaps(coin) ){
            	if(coinx2 == true) {
            		coins = coins + 3;
            		coinLabel.setText(" x "+coins);
            		coinSound.play(volumenEfecto);
            		
                    coin.remove();
                    
                    if(coins >= 100) {
                    	if(coins > 100){
                    		health++;
                        	coins = coins - 100;
                        	coinLabel.setText(" x "+coins);
                        	lblHealth.setText(" x "+health); 
                        	vidaMas.play(volumenEfecto);
                    	}else {
                    		health++;
                        	coins = 0;
                        	coinLabel.setText(" x "+coins);
                        	lblHealth.setText(" x "+health); 
                        	vidaMas.play(volumenEfecto);
                    	}
                    }
                    
            	}else if(coinx2  == false) {
            		coins++;
                    coinLabel.setText(" x "+coins);
                    coinSound.play(volumenEfecto);
                    
                    coin.remove();
                    
                    if(coins >= 100) {
                    	if(coins > 100){
                    		health++;
                        	coins = coins - 100;
                        	coinLabel.setText(" x "+coins);
                        	lblHealth.setText(" x "+health); 
                        	vidaMas.play(volumenEfecto);
                    	}else {
                    		health++;
                        	coins = 0;
                        	coinLabel.setText(" x "+coins);
                        	lblHealth.setText(" x "+health);
                        	vidaMas.play(volumenEfecto);
                    	}
                    }
            	}      
            }
        }
        
        /*
         * Big Coin 1
         */
        for (BaseActor bigCoin1 : BaseActor.getList(mainStage, "BigCoin1")) {
        	
            if ( band.overlaps(bigCoin1) ){       	
            	iconBigCoin1.setAnimation(TRcoin1);
            	tieneBigCoin1Demo = "si";
            	estrella.play(volumenEfecto);
            	bigCoin1.remove();
            }
        }
        
        /*
         * Big Coin 2
         */
        for (BaseActor bigCoin2 : BaseActor.getList(mainStage, "BigCoin2")) {
        	
            if ( band.overlaps(bigCoin2) ){
            	iconBigCoin2.setAnimation(TRcoin2);    
            	tieneBigCoin2Demo = "si";
            	estrella.play(volumenEfecto);
            	bigCoin2.remove();
            }
        }

        /*
         * Big Coin 3
         */
        for (BaseActor bigCoin3 : BaseActor.getList(mainStage, "BigCoin3")) {
        	
            if ( band.overlaps(bigCoin3) ){
            	iconBigCoin3.setAnimation(TRcoin3);     
            	tieneBigCoin3Demo = "si";  
            	estrella.play(volumenEfecto);
            	bigCoin3.remove();
            }
        }
        
        /**
         * Corazon que sum 1 vida
         */
        for (BaseActor vida : BaseActor.getList(mainStage, "Vida")) {
        	
        	if ( band.overlaps(vida)){
            	
            	health++;
                lblHealth.setText(" x "+health);
                vidaMas.play(volumenEfecto);
                vida.remove();
            }              
        }

        /**
         * Bola de fuego item
         */
        for (BaseActor fireballItem : BaseActor.getList(mainStage, "Fireballitem")) {
        	if ( band.overlaps(fireballItem)){         	
        		mana = mana + 2;
                lblMana.setText(" x "+mana);
                fireballItem.remove();                     
            } 	
                    
        }
        time -= dt;
        timeLabel.setText("Time: " + (int)time);

   /*     for (BaseActor timer : BaseActor.getList(mainStage, "Timer")){
        	
            if ( band.overlaps(timer) ){   
            	
                time += 20;
                timer.remove();
            }
        }*/
        
        //Muerte por falta de vidas
        if (health < 0){
        	
            band.remove();
            gameOver = true;
            
            music.pause();
            ColourBand.setActiveScreen( new MenuScreenGameOver() );
            
        }

        /**
         * Colisiones del Suelo
         */
        for (BaseActor actor : BaseActor.getList(mainStage, "Solid")){
        	
            Solid solid = (Solid)actor;

            if ( solid instanceof Platform ) {
            	
                if ( band.isJumping() && band.overlaps(solid) )
                    solid.setEnabled(false);

                if ( band.isJumping() && !band.overlaps(solid) )
                    solid.setEnabled(true);
                
                if ( band.isFalling() && !band.overlaps(solid) && !band.belowOverlaps(solid) )
                    solid.setEnabled(true);
            }

            if ( band.overlaps(solid) && solid.isEnabled() ) {
            	
                Vector2 offset = band.preventOverlap(solid);

                if (offset != null) {
                	
                    // collided in X direction
                    if ( Math.abs(offset.x) > Math.abs(offset.y) )
                    	band.velocityVec.x = 0;
                    else // collided in Y direction
                    	band.velocityVec.y = 0;
                }
            }
            /**
             * Colisiones del Enemigo1 con el Suelo
             */
            for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){
            	
                if (enemy1.overlaps(solid)){
                	
                	enemy1.preventOverlap(solid);
                    enemy1.setMotionAngle( enemy1.getMotionAngle() + 180 );
                }
            }
            /**
             * Colisiones del Enemigo2 con el Suelo
    /*         */
            for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){
            	
                if (enemy2.overlaps(solid)){
                	enemy2.preventOverlap(solid);
		        	if(enemy2.velocityVec.x > 0) {
		        		enemy2.velocityVec.x = -(MathUtils.random(60,100));
		        		enemy2.setScaleX(1);
		            		
		            }else {
		            		enemy2.velocityVec.x = (MathUtils.random(60,100));
		            		enemy2.setScaleX(-1);
		            }
                }
            }
            /**
             * Colisiones del Enemigo3 con el Suelo
             */
            for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){
            	
                if (enemy3.overlaps(solid)){
                	enemy3.preventOverlap(solid);
                	if(enemy3.velocityVec.x > 0) {
                		enemy3.velocityVec.x = -(MathUtils.random(50,65));
                		enemy3.setScaleX(1);
		            		
		            }else {
		            	enemy3.velocityVec.x = (MathUtils.random(50,65));
		            	enemy3.setScaleX(-1);
		            }
                	
                }
            }
            /**
             * Colisiones del Enemigo4 con el Suelo
             */
            for (BaseActor enemy4 : BaseActor.getList(mainStage, "Enemy4")){
            	
                if (enemy4.overlaps(solid)){
                	
                	enemy4.preventOverlap(solid);
                	enemy4.setMotionAngle( enemy4.getMotionAngle()+ 180);
                }
            }
            /**
             * Colisiones del Enemigo2 con el Suelo
             */
            for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){
            	
                if (enemy8.overlaps(solid)){
                	enemy8.preventOverlap(solid);
		        	if(enemy8.velocityVec.x > 0) {
		        		enemy8.velocityVec.x = -(MathUtils.random(60,100));
		        		enemy8.setScaleX(1);
		            		
		            }else {
		            		enemy8.velocityVec.x = (MathUtils.random(60,100));
		            		enemy8.setScaleX(-1);
		            }
                }
            }
        }

        /**
         * Colisiones del Enemigo1 con Colisiones para que no se salgan
         */
        for (BaseActor enemy1Solid : BaseActor.getList(mainStage, "Enemy1Solid")){    

            for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){
            	
                if (enemy1.overlaps(enemy1Solid)){
                	
                	enemy1.preventOverlap(enemy1Solid);
                    enemy1.setMotionAngle( enemy1.getMotionAngle() + 180 );
                }
            }
        }
        /**
         * Colision del Enemigo2 y el Enemigo3
         */
        for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){    

            for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){
            	
                if (enemy2.overlaps(enemy3)){
                	
                	if(enemy2.velocityVec.x > 0) {
                		enemy2.velocityVec.x = -(MathUtils.random(60,100));
                		enemy2.setScaleX(1);
		            		
		            }else {
		            	enemy2.velocityVec.x = (MathUtils.random(60,100));
		            	enemy2.setScaleX(-1);
		            }    	
                }
            }         
        }
        
        /**
         * Colision del Enemigo8 y el Enemigo3
         */
        for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){    

            for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){
            	
                if (enemy8.overlaps(enemy3)){
                	
                	if(enemy8.velocityVec.x > 0) {
                		enemy8.velocityVec.x = -(MathUtils.random(60,100));
                		enemy8.setScaleX(1);
		            		
		            }else {
		            	enemy8.velocityVec.x = (MathUtils.random(60,100));
		            	enemy8.setScaleX(-1);
		            }    	
                }
            }         
        }
        
        /**
         * Colision del Enemigo3 y el Enemigo2
         */
        for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){    

            for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){
            	
            	if(enemy3.overlaps(enemy2)){
                	
                	if(enemy3.velocityVec.x > 0) {
                		enemy3.velocityVec.x = -(MathUtils.random(50,65));
                		enemy3.setScaleX(1);
                    		
                    }else {
                    	enemy3.velocityVec.x = (MathUtils.random(50,65));
                    	enemy3.setScaleX(-1);
                    }
                }
            }         
        }   
        
        /**
         * Colision del Enemigo3 y el Enemigo8
         */
        for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){    

            for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){
            	
            	if(enemy3.overlaps(enemy8)){
                	
                	if(enemy3.velocityVec.x > 0) {
                		enemy3.velocityVec.x = -(MathUtils.random(50,65));
                		enemy3.setScaleX(1);
                    		
                    }else {
                    	enemy3.velocityVec.x = (MathUtils.random(50,65));
                    	enemy3.setScaleX(-1);
                    }
                }
            }         
        }  
        
        /**
         * Colision del Enemigo1 y el Enemigo8
         */
        for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){    

            for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){
            	
                if (enemy1.overlaps(enemy8)){
                	
                	if(enemy1.velocityVec.x > 0) {
                		enemy1.setMotionAngle( enemy1.getMotionAngle() - 180 );
		            		
		            }else {
		            	enemy1.setMotionAngle( enemy1.getMotionAngle() + 180 );
		            }    	
                }
            }         
        }
        
        /**
         * Colision del Enemigo1 y el Enemigo2
         */
        for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){    

            for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){
            	
                if (enemy1.overlaps(enemy2)){
                	
                	if(enemy1.velocityVec.x > 0) {
                		enemy1.setMotionAngle( enemy1.getMotionAngle() - 180 );
		            		
		            }else {
		            	enemy1.setMotionAngle( enemy1.getMotionAngle() + 180 );
		            }    	
                }
            }         
        }
        
        /**
         * Colision del Enemigo8 y el Enemigo5
         */
        for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){    

            for (BaseActor enemy5 : BaseActor.getList(mainStage, "Enemy5")){
            	
            	if(enemy8.overlaps(enemy5)){
                	
                	if(enemy8.velocityVec.x > 0) {
                		enemy8.velocityVec.x = -(MathUtils.random(50,65));
                		enemy8.setScaleX(1);
                    		
                    }else {
                    	enemy8.velocityVec.x = (MathUtils.random(50,65));
                    	enemy8.setScaleX(-1);
                    }
                }
            }         
        } 
        /**
         * Colision del Enemigo1 y el Enemigo3
         */
        for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){    

            for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){
            	
            	if(enemy1.overlaps(enemy3)){
                	
                	if(enemy1.velocityVec.x > 0) {
                		enemy1.setMotionAngle( enemy1.getMotionAngle() - 180 );                   		
                    }else {
                    	enemy1.setMotionAngle( enemy1.getMotionAngle() + 180 );
                    }
                }
            }         
        } 
        
        /**
         * Colision del Enemigo3 y el Enemigo1
         */
        for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){    

            for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){
            	
                if (enemy3.overlaps(enemy1)){
                	
                	if(enemy3.velocityVec.x > 0) {
                		enemy3.velocityVec.x = -(MathUtils.random(60,100));
                		enemy3.setScaleX(1);
		            		
		            }else {
		            	enemy3.velocityVec.x = (MathUtils.random(60,100));
		            	enemy3.setScaleX(-1);
		            }    	
                }
            }         
        }
        /**
         * Colision del Enemigo2 y el Enemigo1
         */
        for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){    

            for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){
            	
            	if(enemy2.overlaps(enemy1)){
                	
                	if(enemy2.velocityVec.x > 0) {
                		enemy2.velocityVec.x = -(MathUtils.random(50,65));
                		enemy2.setScaleX(1);
                    		
                    }else {
                    	enemy2.velocityVec.x = (MathUtils.random(50,65));
                    	enemy2.setScaleX(-1);
                    }
                }
            }         
        } 
        
        /**
         * Colisiones del Enemigo1 con el Jugador
         */
        for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){
            if ( band.overlaps(enemy1) ){
            	
            	if(recibirDaño == true) {
            		
            		band.preventOverlap(enemy1);  
                	enemy1.setMotionAngle( enemy1.getMotionAngle() + 180 );
                    Vector2 bandPosition  = new Vector2(  band.getX(),  band.getY() );
                    Vector2 enemy1Position = new Vector2( enemy1.getX(), enemy1.getY() );
                	Vector2 hitVector = bandPosition.sub( enemy1Position );
                	band.setMotionAngle( hitVector.angle() );
            		
                	actTiempoVida = tiempoVida;
                	
                	daño.play(volumenEfecto);
                	health--;
                	lblHealth.setText(" x "+health); 
            	}          	 
            }
        }
        
        /**
         * Colisiones del Enemigo2 con el Jugador
         */
        for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){
            if ( band.overlaps(enemy2) ){

            	if(recibirDaño == true) {
            		
            		band.preventOverlap(enemy2);
            		
                	actTiempoVida = tiempoVida;
                	
                	daño.play(volumenEfecto);
                	health--;
                	lblHealth.setText(" x "+health);
                	
                	if(enemy2.velocityVec.x > 0) {
                		enemy2.velocityVec.x = -(MathUtils.random(50,65));
                		enemy2.setScaleX(1);
    	            		
    	            }else {
    	            	enemy2.velocityVec.x = (MathUtils.random(50,65));
    	            	enemy2.setScaleX(-1);
    	            }
            	}       
            	
            	
            }
        }
        
        /**
         * Colisiones del Enemigo3 con el Jugador
         */
        for (BaseActor enemy3 : BaseActor.getList(mainStage, "Enemy3")){
            if ( band.overlaps(enemy3) ){
            	   
            	if(recibirDaño == true) {
            		
            		band.preventOverlap(enemy3);
            		
                	actTiempoVida = tiempoVida;
                	
                	daño.play(volumenEfecto);
                	health--;
                	lblHealth.setText(" x "+health); 
                	
                	if(enemy3.velocityVec.x > 0) {
                		enemy3.velocityVec.x = -(MathUtils.random(50,65));
                		enemy3.setScaleX(1);
    	            		
    	            }else {
    	            	enemy3.velocityVec.x = (MathUtils.random(50,65));
    	            	enemy3.setScaleX(-1);
    	            }
            	}                     	
            }
        }
        
        /**
         * Colisiones del Enemigo4 con el Jugador
         */
        for (BaseActor enemy4 : BaseActor.getList(mainStage, "Enemy4")){
            if ( band.overlaps(enemy4) ){
            	
            	band.preventOverlap(enemy4);                
            	enemy4.setMotionAngle( enemy4.getMotionAngle() + 180 );
                Vector2 bandPosition  = new Vector2(  band.getX(),  band.getY() );
                Vector2 enemy4Position = new Vector2( enemy4.getX(), enemy4.getY() );
            	Vector2 hitVector = bandPosition.sub( enemy4Position );
            	band.setMotionAngle( hitVector.angle() );
            	
            	if(recibirDaño == true) {
            		
                	actTiempoVida = tiempoVida;
                	
                	daño.play(volumenEfecto);
                	health--;
                	lblHealth.setText(" x "+health); 
                	
                	if(enemy4.velocityVec.x > 0) {
                		enemy4.velocityVec.x = -(MathUtils.random(50,65));
                		enemy4.setScaleX(1);
    	            		
    	            }else {
    	            	enemy4.velocityVec.x = (MathUtils.random(50,65));
    	            	enemy4.setScaleX(-1);
    	            }
            	}      
            	
            	
            }
        }
        
        /**
         * Colisiones del Enemigo8 con el Jugador
         */
        for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){
            if ( band.overlaps(enemy8) ){

            	if(recibirDaño == true) {
            		band.preventOverlap(enemy8);
            		
                	actTiempoVida = tiempoVida;
                	
                	daño.play(volumenEfecto);
                	health--;
                	lblHealth.setText(" x "+health); 
                	
                	if(enemy8.velocityVec.x > 0) {
                		enemy8.velocityVec.x = -(MathUtils.random(50,65));
                		enemy8.setScaleX(1);
    	            		
    	            }else {
    	            	enemy8.velocityVec.x = (MathUtils.random(50,65));
    	            	enemy8.setScaleX(-1);
    	            }
            	}      
            	
            	
            }
        }
        
        /**
         * Colisiones del Enemigo9 con el Jugador
         */
        for (BaseActor enemy9 : BaseActor.getList(mainStage, "Enemy9")){
            if ( band.overlaps(enemy9) ){
	
	    		if(recibirDaño == true) {
	    			
	    			band.preventOverlap(enemy9); 
	        		
	            	actTiempoVida = tiempoVida;
	            	
	            	daño.play(volumenEfecto);
	            	System.out.println(volumenEfecto);
	            	health--;
	            	lblHealth.setText(" x "+health); 
	            	
	            	Vector2 bandPosition  = new Vector2(  band.getX(),  band.getY() );
	                Vector2 enemy1Position = new Vector2( enemy9.getX(), enemy9.getY() );
	            	Vector2 hitVector = bandPosition.sub( enemy1Position );
	            	enemy9.setMotionAngle( hitVector.angle()+180 );
	        	}   
       	 
            }
        }
        
        
        /*
         * Matar enemigo2 al saltar
         */
        for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){
        	
            if (band.belowOverlaps(enemy2)){
            	
            	enemy2.remove();
            	
            	if ( MathUtils.random(1,9) == 1 ) {
                	TripleCoin coinx3 = new TripleCoin(0, 0, mainStage);
                	coinx3.centerAtActor(enemy2);
                }else {
                	Coin coin = new Coin(0,0, mainStage);
                    coin.centerAtActor(enemy2); 
                }
                
            }
        }
        /*
         * Matar enemigo8 al saltar
         */
        for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){
        	
            if (band.belowOverlaps(enemy8)){
            	
            	enemy8.remove();
            	
            	if ( MathUtils.random(1,9) == 1 ) {
                	TripleCoin coinx3 = new TripleCoin(0, 0, mainStage);
                	coinx3.centerAtActor(enemy8);
                }else {
                	Coin coin = new Coin(0,0, mainStage);
                    coin.centerAtActor(enemy8); 
                }
                
            }
        }
        
        /*
         * Matar enemigo3 al saltar
         */
        for (BaseActor enemigo3 : BaseActor.getList(mainStage, "Enemy3")){
        	
            if (band.belowOverlaps(enemigo3)){
            	
            	enemigo3.remove();
                
                if ( MathUtils.random(1,2) == 1 ) {
                	Fireballitem fireballitem = new Fireballitem(0,0, mainStage);
                    fireballitem.centerAtActor(enemigo3);
                    fireballitem.centerAtActor(enemigo3); 
                }else {
                	Fireballitem fireballitem = new Fireballitem(0,0, mainStage);
                    fireballitem.centerAtActor(enemigo3); 
                    fireballitem.centerAtActor(enemigo3); 
                    fireballitem.centerAtActor(enemigo3); 
                }
                
            }
        }
        
        /**
         * Bolas de Fuego
         */
        for (BaseActor fireball : BaseActor.getList(mainStage, "Fireball")){
        	
            for (BaseActor enemy1 : BaseActor.getList(mainStage, "Enemy1")){
                
            	if (fireball.overlaps(enemy1)){
                	
                    enemy1.remove();
                    fireball.remove();
                    Coin coin = new Coin(0,0, mainStage);
                    coin.centerAtActor(enemy1);                    
                }
            }	
        	for (BaseActor solid : BaseActor.getList(mainStage, "Solid")){
            	
                
                if (fireball.overlaps(solid)){
                	
                	fireball.preventOverlap(solid);
                	fireball.setSpeed(0);
                	fireball.addAction( Actions.fadeOut(0.5f) );
                	fireball.addAction( Actions.after( Actions.removeActor() ) );
                }
                for (BaseActor enemy8 : BaseActor.getList(mainStage, "Enemy8")){
                    
                	if (fireball.overlaps(enemy8)){
                    	
                		enemy8.remove();
                        fireball.remove();
                        if ( MathUtils.random(1,6) == 1 ) {
                        	TripleCoin coinx3 = new TripleCoin(0, 0, mainStage);
                        	coinx3.centerAtActor(enemy8);
                        }else {
                        	Coin coin = new Coin(0,0, mainStage);
                            coin.centerAtActor(enemy8); 
                        }
                                           
                    }
            	}   
        	}
        	
        	for (BaseActor enemy2 : BaseActor.getList(mainStage, "Enemy2")){
                
            	if (fireball.overlaps(enemy2)){
                	
            		enemy2.remove();
                    fireball.remove();
                    if ( MathUtils.random(1,6) == 1 ) {
                    	TripleCoin coinx3 = new TripleCoin(0, 0, mainStage);
                    	coinx3.centerAtActor(enemy2);
                    }else {
                    	Coin coin = new Coin(0,0, mainStage);
                        coin.centerAtActor(enemy2); 
                    }
                                       
                }
        	}   
        	
        	for (BaseActor enemy9 : BaseActor.getList(mainStage, "Enemy9")){
                
            	if (fireball.overlaps(enemy9)){
                	
            		enemy9.remove();
                    fireball.remove();
                    
                    if ( MathUtils.random(1,5) == 1 ) {
                    	Vida vida = new Vida(0, 0, mainStage);
                    	vida.centerAtActor(enemy9);
                    }else {
                    	Coin coin = new Coin(0,0, mainStage);
                        coin.centerAtActor(enemy9); 
                    }
                                       
                }
        	}  
        }
        
        
        /**
         * Colisiones del PowerUp de Triple Monedas con el jugador
         */
        for (BaseActor tripleCoin : BaseActor.getList(mainStage, "TripleCoin")) {
        	
            if ( band.overlaps(tripleCoin) ) {
            	   	
            	lblTripleCoin.setText("Triple Coin");
            	lblTripleCoin.setVisible(true);
            	tripleCoin.remove();
            	
            	actTiempoTripleCoin = tiempoTripleCoin;
            	
            	coinx2 = true;
            }
        }
        /**
         * Colisiones de Muerte con el Jugador
         */
        for (BaseActor muerte : BaseActor.getList(mainStage, "Muerte")){

            if ( band.overlaps(muerte)) {     
            	music.pause();
            	ColourBand.setActiveScreen( new LevelScreenRedux());
            }  
        }
        /**
         * Colisiones del PowerUp de Salto con el Jugador
         */
        for (BaseActor jumpBoost : BaseActor.getList(mainStage, "JumpBoost")) {
        	
            if ( band.overlaps(jumpBoost) ) {
            	
            	lblJumpBoost.setText("Jump Boost");
            	lblJumpBoost.setVisible(true);
                
            	jumpBoost.remove();
            	
            	actTiempoBooster = tiempoBooster;
            	
            	band.JumpBoostOn();   
          	
            }
        }
        //Gravedad
        if(band.isFalling()) {
        	band.gravity = 1000;
        }else {
        	band.gravity = 650;
        }
        //Coldown de la vida
        if(actTiempoVida > 0) {
        	actTiempoVida -= Gdx.graphics.getDeltaTime();
        	recibirDaño = false;
        	lblHealth.setColor(Color.WHITE);
        }
        else if(actTiempoVida <= 0){
        	recibirDaño = true;
        	lblHealth.setColor(Color.PINK);
        }
        //Coldown de bolas de fuego
        if(actTiempoDisparo > 0) {
        	actTiempoDisparo -= Gdx.graphics.getDeltaTime();
        	dispararBola = false;
        	lblMana.setColor(Color.WHITE);
        }
        else if(actTiempoDisparo <= 0){
        	dispararBola = true;
        	lblMana.setColor(Color.ORANGE);
        }
        //Tiempo del Jump Booster
        if(actTiempoBooster > 0) {
        	actTiempoBooster -= Gdx.graphics.getDeltaTime();
        }
        else if(actTiempoBooster <= 0){
        	band.JumpBoostOff();
        	
        	lblJumpBoost.setText("");
        	lblJumpBoost.setVisible(false);
        }
        //Tiempo del Triple Coin
        if(actTiempoTripleCoin > 0) {
        	actTiempoTripleCoin -= Gdx.graphics.getDeltaTime();
        }
        else if(actTiempoTripleCoin <= 0){
        	
        	coinx2 = false;
        	
        	lblTripleCoin.setText("");
        	lblTripleCoin.setVisible(false);
        }
    }
    public void shootFireball(){
    	if ( mana <= 0 )
            return;
    	if(dispararBola == true) {
    		actTiempoDisparo = tiempoDisparo;
    	}
        mana--;
        
        lblMana.setText(" x "+mana);
        
        fireballSound.play(volumenEfecto);
        
    	Fireball fireball = new Fireball(0,0, mainStage);
        fireball.centerAtActor(band);
        fireball.setRotation( band.getFacingAngle());
        fireball.setMotionAngle( band.getFacingAngle());      
    }

    public boolean keyDown(int keyCode){
    	
        if (gameOver)
            return false;

        if (keyCode == Keys.SPACE){
        	
            if ( Gdx.input.isKeyPressed(Keys.S) ){
            	
                for (BaseActor actor : BaseActor.getList(mainStage, "Platform")) {
                	
                    Platform platform = (Platform)actor;
                    if ( band.belowOverlaps(platform) ){
                    	
                        platform.setEnabled(false);
                    }
                }
            }
            else if ( band.isOnSolid() )   {
            	band.jump();
            }
        }
        if (Gdx.input.isKeyPressed(Keys.ENTER)){
        	if(dispararBola == true) {
        		actTiempoDisparo = tiempoDisparo;
        		shootFireball();
        	}  
        }
            
        if(state == state.Paused) {
        	if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
            	state = State.Running;   	
            }else if (Gdx.input.isKeyJustPressed(Keys.M)){
            	ColourBand.setActiveScreen(new MenuScreen());
            	
            	musicaOff();
            	
            	state = State.Running; 
            } 
        }else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
        	state = State.Paused;   	
        	Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_BLEND);
        } 	
        
        return false;
    }
    public static boolean isJuegoEnMarcha() {
		return juegoEnMarcha;
	}
    public static void musicaOff() {
		music.pause();
	}
    public static String isTieneBigCoin1Demo() {
		return tieneBigCoin1Demo;
	}

	public static String isTieneBigCoin2Demo() {
		return tieneBigCoin2Demo;
	}

	public static String isTieneBigCoin3Demo() {
		return tieneBigCoin3Demo;
	}
    
}