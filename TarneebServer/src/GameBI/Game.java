package GameBI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {

    private final int numCardsForEachPlayer = 13;
    private final int totalScore = 41;
    private String tarneenType;
    private String splitType;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Card> cardK = new ArrayList<Card>();
    private ArrayList<Card> cardD = new ArrayList<Card>();
    private ArrayList<Card> cardP = new ArrayList<Card>();
    private ArrayList<Card> cardS = new ArrayList<Card>();
    private ArrayList<Card> playerCard = new ArrayList<Card>();
    public Card[] table = new Card[4];
    private ArrayList<Player> players = new ArrayList<Player>();
    private static String[] type = {"S", "D", "K", "P"};
    private static int[] number = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    public String currentType = "";

    public Game() {
        for (int i = 0; i < 4; i++) {
            players.add(new Player());
        }
    }

    public void setTarneebType() {
        Random a = new Random();
        int tarType = a.nextInt(4);
        int tarNum = a.nextInt(13);
        this.splitType = number[tarNum] + "_" + type[tarType];

        if (type[tarType].equals("S")) {
            this.tarneenType = "P";
        } else if (type[tarType].equals("P")) {
            this.tarneenType = "S";
        } else if (type[tarType].equals("K")) {
            this.tarneenType = "D";
        } else {
            this.tarneenType = "K";
        }
    }

    public String getTarneebType() {
        return tarneenType;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setPlayerName(String[] name) {
        for (int i = 0; i < 4; i++) {
            this.players.get(i).setName(name[i]);
        }
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void setTable(int index, Card card) {
        table[index] = card;
    }

    public Card getTable(int index) {
        return table[index];
    }

    public int getnumCardsForEachPlayer() {
        return numCardsForEachPlayer;
    }

    public void clearTable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    public boolean check(Card s) {
        for (int i = 0; i < playerCard.size(); i++) {
            if (playerCard.get(i).getType().equals(s.getType()) && playerCard.get(i).getNumber() == s.getNumber()) {
                return false;
            }
        }
        return true;
    }

    public void disCard() {
        cards.clear();
        playerCard.clear();
        Random r1 = new Random();
        int count = 0;
        Card s;
        for (int i = 0; i < players.size(); i++) {
            cardK.clear();
            cardD.clear();
            cardP.clear();
            cardS.clear();
            players.get(i).clearCard();
            while (count < number.length) {
                int random2 = r1.nextInt(13);
                int random1 = r1.nextInt(4);
                s = new Card(type[random1], number[random2]);

                if (check(s) && s.getType().equals("K")) {
                    playerCard.add(s);
                    cardK.add(s);
                    //players.get(i).setCard(s);
                    count++;
                }
                if (check(s) && s.getType().equals("D")) {
                    playerCard.add(s);
                    cardD.add(s);
                    count++;
                }
                if (check(s) && s.getType().equals("P")) {
                    playerCard.add(s);
                    cardP.add(s);
                    count++;
                }
                if (check(s) && s.getType().equals("S")) {
                    playerCard.add(s);
                    cardS.add(s);
                    count++;
                }
            }

            Collections.sort(cardK);
            Collections.sort(cardD);
            Collections.sort(cardP);
            Collections.sort(cardS);

            for (int j = 0; j < cardK.size(); j++) {
                cards.add(cardK.get(j));
                players.get(i).setCard(cardK.get(j));
            }
            for (int j = 0; j < cardD.size(); j++) {
                cards.add(cardD.get(j));
                players.get(i).setCard(cardD.get(j));
            }
            for (int j = 0; j < cardP.size(); j++) {
                cards.add(cardP.get(j));
                players.get(i).setCard(cardP.get(j));
            }
            for (int j = 0; j < cardS.size(); j++) {
                cards.add(cardS.get(j));
                players.get(i).setCard(cardS.get(j));
            }
            count = 0;
        }
        setTarneebType();

    }

    public ArrayList<Card> printCardPlayer(Player p) {
        ArrayList<Card> s = p.printCard();
        return s;
    }

    public boolean tableExistTarneeb(Card[] table) {
        for (int i = 0; i < table.length; i++) {
            if (getTable(i).getType().equals(getTarneebType())) {
                return true;
            }
        }
        return false;
    }

    public int winnerPlayerRound(Card[] table) {
        int max = 0;
        int index = 0;
        if (tableExistTarneeb(table)) {
            for (int i = 0; i < table.length; i++) {
                if (getTable(i).getType().equals(getTarneebType())) {
                    if (max < getTable(i).getNumber()) {
                        max = getTable(i).getNumber();
                        index = i;
                    }
                }
            }
        } else {
            for (int i = 0; i < table.length; i++) {
                if (max < getTable(i).getNumber()) {
                    max = getTable(i).getNumber();
                    index = i;
                }
            }
        }
        players.get(index).setScoreRound(players.get(index).getScoreRound() + 1);
        clearTable();
        currentType = "";
        return index;
    }

    public boolean validation(int daman, int low, int high) {
        return daman >= low && daman <= high;
    }

    public boolean validateDonotHaveType(int index) {
        for (int i = 0; i < 13; i++) {
            if (players.get(index).getCard(i).getType().equals(currentType)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateInputCard(int indexPlayer, String str) {
        String[] s = str.split("\\_");
        Card c = new Card(s[1], Integer.parseInt(s[0]));

        if (currentType.equals("") && !c.getType().equals("")) {
            currentType = c.getType();
            setTable(indexPlayer, c);
            players.get(indexPlayer).inputCard(c);
            return true;
        } else if (currentType.equals(c.getType()) && !c.getType().equals("")) {
            setTable(indexPlayer, c);
            players.get(indexPlayer).inputCard(c);
            return true;
        } else {
            if (validateDonotHaveType(indexPlayer) && !c.getType().equals("")) {
                setTable(indexPlayer, c);
                players.get(indexPlayer).inputCard(c);
                return true;
            }
            if (c.getType().equals("")) {
                return false;
            }

            return false;
        }
    }

    public void calcScore() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScoreRound() >= players.get(i).getDaman()) {
                if (players.get(i).getDaman() >= 2 && players.get(i).getDaman() <= 4) {
                    int s = players.get(i).getScore() + players.get(i).getDaman();
                    players.get(i).setScore(s);
                } else if (players.get(i).getDaman() >= 5 && players.get(i).getDaman() <= 8) {
                    int s = players.get(i).getScore() + (players.get(i).getDaman() * 2);
                    players.get(i).setScore(s);
                } else if (players.get(i).getDaman() >= 9 && players.get(i).getDaman() <= 13) {
                    int s = players.get(i).getScore() + (players.get(i).getDaman() * 3);
                    players.get(i).setScore(s);
                }
            } else if (players.get(i).getDaman() >= 2 && players.get(i).getDaman() <= 4) {
                int s = players.get(i).getScore() - players.get(i).getDaman();
                players.get(i).setScore(s);
            } else if (players.get(i).getDaman() >= 5 && players.get(i).getDaman() <= 8) {
                int s = players.get(i).getScore() - (players.get(i).getDaman() * 2);
                players.get(i).setScore(s);
            } else if (players.get(i).getDaman() >= 9 && players.get(i).getDaman() <= 13) {
                int s = players.get(i).getScore() - (players.get(i).getDaman() * 3);
                players.get(i).setScore(s);
            }
        }
    }

    public void printScore() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("The score player " + players.get(i).getName() + " = " + players.get(i).getScore());
        }
    }

    public boolean ifgetTotalScore() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() == totalScore) {
                return true;
            }
        }
        return false;
    }

    public void makeZeroScoreRound() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setScoreRound(0);
        }
    }

}
