import java.awt.*;
import java.awt.event.*;
import java.util.HashSet.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan  extends JPanel{
//X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
      "XXXXXXXXXXXXXXXXXXX",
      "X        X        X",
      "X XX XXX X XXX XX X",
      "X                 X",
      "X XX X XXXXX X XX X",
      "X    X       X    X",
      "XXXX XXXX XXXX XXXX",
      "OOOX X       X XOOO",
      "XXXX X XXrXX X XXXX",
      "O       bpo       O",
      "XXXX X XXXXX X XXXX",
      "OOOX X       X XOOO",
      "XXXX X XXXXX X XXXX",
      "X        X        X",
      "X XX XXX X XXX XX X",
      "X  X     P     X  X",
      "XX X X XXXXX X X XX",
      "X    X   X   X    X",
      "X XXXXXX X XXXXXX X",
      "X                 X",
      "XXXXXXXXXXXXXXXXXXX" 
  };

class Block {
   int x;
   int y;
   int width;
   int height;
   Image image;

   int startX;
   int startY;

   Block (Image image, int x, int y, int width,int height){
      this.image = image;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.startX = x;
      this.startY = y;
   }  
}
   private int rowCount = 21;
   private int columnCount = 19;
   private int tileSize = 32;
   private int boardWidth = columnCount * tileSize;
   private int boardHeight = rowCount * tileSize;

   private Image wallImage;
   private Image blueGhostImage;
   private Image orangeGhostImage;
   private Image redGhostImage;
   private Image pinkGhostImage;

   private Image pacmanUpImage;
   private Image pacmanDownImage;
   private Image pacmanLeftImage;
   private Image pacmanRightImage;

   HashSet<Block> walls;
   HashSet<Block> ghosts;
   HashSet<Block> foods;
   Block pacman;
   


   PacMan(){
      setPreferredSize(new Dimension(boardWidth, boardHeight));
      setBackground(Color.BLACK);

      wallImage = new ImageIcon(getClass().getResource("./wall.png")).getImage() ;
      blueGhostImage = new ImageIcon(getClass().getResource("./blueGhost.png")).getImage() ;
      orangeGhostImage = new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage() ;
      redGhostImage = new ImageIcon(getClass().getResource("./redGhost.png")).getImage() ;
      pinkGhostImage = new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage() ;
      
      pacmanUpImage = new ImageIcon(getClass().getResource("./pacmanUp.png")).getImage() ;
      pacmanDownImage = new ImageIcon(getClass().getResource("./pacmanDown.png")).getImage() ;
      pacmanLeftImage = new ImageIcon(getClass().getResource("./pacmanLeft.png")).getImage() ;
      pacmanRightImage = new ImageIcon(getClass().getResource("./pacmanRight.png")).getImage() ;

      loadMap();
      System.out.println(walls.size());
      System.out.println(foods.size());
      System.out.println(ghosts.size());
                    
    }

    public void loadMap() {
      walls = new HashSet<Block>();
      foods = new HashSet<Block>(); 
      ghosts = new HashSet<Block>();
      for (int r = 0; r< rowCount; r++){
         for (int c=0; c < columnCount; c++){
         
               String row = tileMap[r];
               char tileMapChar = row.charAt(c);

               int x = c * tileSize;
               int y = r * tileSize;
         if (tileMapChar == 'X'){//block wall
            Block wall = new Block(wallImage, x, y, tileSize, tileSize);
            walls.add(wall);
         } else if (tileMapChar == 'b'){//blue ghost 
            Block ghost = new Block(blueGhostImage, x, y, tileSize, tileSize);
            ghosts.add(ghost);
         } 
         else if (tileMapChar == 'o'){//orange ghost
            Block ghost = new Block(orangeGhostImage, x, y, tileSize, tileSize);
            ghosts.add(ghost);
         }
         else if (tileMapChar == 'p'){//pink ghost
            Block ghost = new Block(pinkGhostImage, x, y, tileSize, tileSize);
         ghosts.add(ghost);
         }
         else if (tileMapChar == 'r'){//red ghost 
            Block ghost = new Block(redGhostImage, x, y, tileSize, tileSize);
            ghosts.add(ghost);
         }
         else if (tileMapChar == 'P'){//pacman
            pacman = new Block (pacmanRightImage, x, y, tileSize, tileSize);
            
         }
         else if (tileMapChar == ' '){//food
            Block food = new Block(null, x + 14,y+4, 4, 4);
            foods.add(food);
         }
      }
    }
}
public void paintComponent(Graphics g) {
   g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

   for (Block ghost : ghosts){
      g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);

   }
   for (Block wall : walls){
      g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);

   }
   g.setColor(Color.WHITE);
   for (Block food : foods){
      g.fillRect(food.x, food.y, food.width, food.height);
   }
}
}
