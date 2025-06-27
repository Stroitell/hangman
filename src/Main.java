import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main{

    static String word;
    static String letter;
    static StringBuilder mask;
    static int hp;

    static Scanner scanner = new Scanner(System.in);
    static String input;

    static final String START = "да";
    static final String QUIT = "нет";

    static final char START_ALPHABET = 'а';
    static final char END_ALPHABET = 'я';
    static final int LETTER_YO = 'ё';          // буква ё

    static final int MAX_HP = 6;
    static final int MIN_HP = 1;

    public static void main(String[] args){
        do {
            offerToPlay();
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase(START) && !input.equalsIgnoreCase(QUIT)){
                System.out.println(colorText("Некорректный ответ", Color.YELLOW));
                continue;
            }
            if (input.equalsIgnoreCase(QUIT)){
                break;
            }
            startGame();
        } while (true);
    }

    private static void startGame(){
        word = chooseWord();
        System.out.println("Допустимые сивмолы: " + colorText("абвгдеёжзийклмнопрстуфхцчшщъыьэюя", Color.GREEN));
        System.out.println(word);
        mask = makeMask();
        hp = MAX_HP;
        List<String>  usedLetters = new ArrayList<>();
        while (isNotGameOver()){
            drawArtAndMask();
            System.out.println("Уже введенные буквы: " + colorText(String.join(",", usedLetters), Color.GREEN));
            System.out.print("Введите букву: ");
            letter = scanner.nextLine().toLowerCase();
            if (isNotAnswerCorrect()) {
                System.out.println(" ");
                System.out.println(colorText("Ответ может содержать только одну букву русского алфавита", Color.RED));
                continue;
            }
            if (usedLetters.contains(letter)){
                System.out.println(" ");
                System.out.println(colorText("Вы уже вводили эту букву!", Color.YELLOW));
                continue;
            }
            addUsedLetters(usedLetters);
            addLetterToMask();
            hp = takeAwayHp();
            sayNoLatter();
            printGameOver();
        }
    }

    private static boolean isNotGameOver(){
        return hp >= MIN_HP && mask.toString().contains("_");
    }

    private static boolean isNotAnswerCorrect(){
        if (letter.isEmpty()){
            return true;
        }
        char character = letter.charAt(0);
        return ((!(START_ALPHABET <= (int) character && (int) character <= END_ALPHABET)) && (int) character != LETTER_YO) || letter.length() != 1;
    }

    private static void printGameOver(){
        if (hp == 0) {
            System.out.println(GallowsArts.drawArt()[hp]);
            openMask();
            System.out.println(" ");
            System.out.println(colorText("ВЫ ПРОИГРАЛИ", Color.YELLOW));
        }
        sayWin();
    }

    private static void addUsedLetters(List<String> list){
        list.add(letter);
    }

    private static void openMask(){
        System.out.println(colorText(mask.toString(), Color.GREEN) + " --> " + word);
    }

    private static int takeAwayHp(){
        if (!word.contains(letter)){
            hp --;
            return hp--;
        } return hp;
    }
    private static void sayNoLatter(){
        if (!word.contains(letter)) {
            System.out.println(" ");
            System.out.println(colorText("Такой ббуквы нет", Color.YELLOW));
        }
    }

    private static void addLetterToMask(){
        for (int indx = 0; indx < word.length(); indx++) {
            if (word.toCharArray()[indx] == letter.charAt(0)){
                mask.setCharAt(indx, letter.charAt(0));
            }
        }
    }

    private static void sayWin(){
        if (!mask.toString().contains("_")){
            drawArtAndMask();
            System.out.println(" ");
            System.out.println(colorText("ВЫ ПОБЕДИЛИ!", Color.GREEN));
        }
    }

    private static StringBuilder makeMask(){
        return new StringBuilder("_".repeat(word.length()));
    }

    private static void offerToPlay(){
        System.out.println(" ");
        System.out.println("Начать новую игру?");
        System.out.println(START + "/" + QUIT);
    }

    private static Scanner readFile(){
        try {
            File file = new File("dictionary.txt");
            return new Scanner(file);
        }   catch (FileNotFoundException e){
            System.err.println("Файл со списком слов не найден!");
            System.out.println(colorText("Программа будет завершена.", Color.RED));
            System.exit(1);
            return null;
        }
    }

    private static List<String> makeWordsList() {
        List<String> wordsList = new ArrayList<>();
        Scanner scan = readFile();
        while (scan.hasNext()){
            wordsList.add(scan.nextLine());
        }
        return wordsList;
    }

    private static void drawArtAndMask(){
        System.out.println(" ");
        System.out.println(GallowsArts.drawArt()[hp]);
        System.out.println(colorText(mask.toString(), Color.GREEN));
    }

    private static String chooseWord(){
        Random random = new Random();
        makeWordsList();
        return makeWordsList().get(random.nextInt(makeWordsList().size()));
    }

    private static String colorText(String massage, Color color){
        return color.getColor() + massage + Color.RESET.getColor();
    }
}
