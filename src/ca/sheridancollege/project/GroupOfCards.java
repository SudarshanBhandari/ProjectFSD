/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you
 * might want to subclass this more than once. The group of cards has a maximum
 * size attribute which is flexible for reuse.
 *
 * @author dancye
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<Card> playerPile = new ArrayList<Card>();
    ArrayList<Card> computerPile = new ArrayList<Card>();
    ArrayList<Card> tablePile = new ArrayList<Card>();

    private int size;//the size of the grouping

    //public GroupOfCards(int givenSize)
    public GroupOfCards() {
        // size = givenSize;
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
//    public void initCards()
//    {
//        
//        for (Card.Suit s: Card.Suit.values()){
//            
//            for (Card.Value v: Card.Value.values()){
//                 
//                Card c= new Card(s,v);
//                
//                deck.add(c);
//                 
//            }
//        }
//    }
    public void initCards() {
        String[] suits = new String[]{"hearts", "diamonds", "clubs", "spades"};
        for (String s : suits) {
            for (int i = 1; i < 14; i++) {
                Card c = new Card(i, s);
                deck.add(c);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
        Collections.shuffle(playerPile);
        Collections.shuffle(computerPile);

    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public void setSize(int givenSize) {
        size = givenSize;
    }

    public void distributeCards() {
        do {
            Card c = deck.remove((int) Math.random() * (deck.size() - 1));
            playerPile.add(c);
            c = deck.remove((int) Math.random() * (deck.size() - 1));
            computerPile.add(c);
        } while (deck.size() > 0);
    }

}//end class
