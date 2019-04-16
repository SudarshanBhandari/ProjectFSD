/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the
 * code should remember to add themselves as a modifier.
 *
 * @author dancye, 2018
 */
//public abstract class Card 
public class Card {
    //default modifier for child classes

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a
     * regular playing card etc.
     */
//     public enum Suit{HEARTS,CLUBS,SPADES,DIAMONDS};
//        public enum Value{ACE,TWO,THREE,FOUR,FIVE,
//        SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING};
//        
//	private final Suit suit;
//	private final Value value;
//        
//        public Card(Suit s, Value val){
//            suit=s;
//            value=val;
//        }
//
//	public Suit getSuit() {
//		return this.suit;
//	}
//
//
//
//	public Value getValue() {
//		return this.value;
//	}
    int Value;//1=ace,2=2,...,11=jack,12=queen,13=king

    String suit; //"Hearts","Diamonds","Clubs","Spades"

    public Card(int val, String s) {
        Value = val;
        suit = s;
    }

    public String GetFileName() {

        return (Value + "_of_" + suit + ".png");

    }
//    @Override
//    public abstract String toString();

}
