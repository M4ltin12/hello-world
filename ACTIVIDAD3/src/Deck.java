import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"tréboles", "corazones", "picas", "diamantes"};
        String[] colors = {"negro", "rojo"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};

        for (String suit : suits) {
            for (String value : values) {
                String color = (suit.equals("corazones") || suit.equals("diamantes")) ? "rojo" : "negro";
                cards.add(new Card(suit, color, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Se mezcló el Deck.");
    }

    public void head() {
        if (!cards.isEmpty()) {
            Card card = cards.remove(0);
            System.out.println(card);
            System.out.println("Quedan " + cards.size() + " cartas en deck.");
        } else {
            System.out.println("No hay cartas en el deck.");
        }
    }

    public void pick() {
        if (!cards.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(cards.size());
            Card card = cards.remove(index);
            System.out.println(card);
            System.out.println("Quedan " + cards.size() + " cartas en deck.");
        } else {
            System.out.println("No hay cartas en el deck.");
        }
    }

    public void hand() {
        if (cards.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                Card card = cards.remove(0);
                System.out.println(card);
            }
            System.out.println("Quedan " + cards.size() + " cartas en deck.");
        } else {
            System.out.println("No hay suficientes cartas en el deck.");
        }
    }
}

