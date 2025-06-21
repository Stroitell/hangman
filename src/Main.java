import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) throws FileNotFoundException {
        startGaameLoop();
    }

    public static void startGaameLoop () throws FileNotFoundException{

        System.out.println("Начать игру?");
        System.out.println("да/нет");

        while (scanner.nextLine().toLowerCase().equals("да")){

            System.out.println("Допустимые сивмолы: абвгдеёжзийклмнопрстуфхцчшщъыьэюя");

            String word = makeWord();
            StringBuilder shadow = makeShadow(word);
            List<String> letters = new ArrayList<>();

            int hp = 6;
            gallowsArts(hp, shadow, word);

            //System.out.println(word);  //     <------------------------ отображает загадонное слово в начале игры

            while (hp > 0 & shadow.toString().contains("_")){

                System.out.print("Введите букву: ");
                String character = scanner.nextLine().toLowerCase();

                if (checkValidInput(character)){
                    System.out.println("Такой символ не допустим");
                    continue;
                }

                if(letters.contains(character)){
                    System.out.println("""
                            Вы уже вводили эту букву.
                            Попробуйте ввести другую.
                            """);
                    continue;
                }
                addAnwerToList(character, letters);

                if (checkCharacter(word, character) == 1){
                    System.out.println(" ");
                    System.out.println(addCharacterToShadow(character, shadow, word));
                }

                else if (checkCharacter(word, character) == -1 & hp > 1){
                    hp -=1;
                    gallowsArts(hp, shadow, word);
                    System.out.print("Такой буквы нет. ");
                }
                else if (checkCharacter(word, character) == -1 & hp == 1){
                    hp -=1;
                    gallowsArts(hp, shadow, word);
                }
            }
            if (!shadow.toString().contains("_")){
                System.out.println("""
                        
                        Вы ПОБЕДИЛИ!!!
                        
                        Играть ещё раз?
                            да/нет""");
            }


        }
    }

    public static boolean checkValidInput(String character){
        if (!alphabet.contains(character)){
            return true;
        }   else{
            return false;
        }
    }

    public static String makeWord () throws FileNotFoundException {

        File file = new File("src/dictionary.txt");
        Scanner scan = new Scanner(file);
        List<String> list = new ArrayList<>();

        while (scan.hasNextLine()){
            list.add(scan.nextLine().toString());
        }

        Random random = new Random();
        String word = list.get(random.nextInt(list.size()));

        scan.close();
        return word;
    }

    public static StringBuilder makeShadow (String word) throws FileNotFoundException{

        StringBuilder shadow = new StringBuilder("_".repeat(word.length()));

        return shadow;
    }

    public static Integer checkCharacter(String word, String character){
        // есть или нет?

        if (word.contains(character)){
            return 1;
        }  else {
            return -1;
        }
    }

    public static StringBuilder addCharacterToShadow(String character, StringBuilder shadow, String word){

        char symbol = character.charAt(0);

        for (int inx = 0; inx < word.length(); inx ++){
            if(word.toCharArray()[inx] == symbol){
                shadow.setCharAt(inx, symbol);
            }
        }
        return shadow;
    }

    public static List<String> addAnwerToList(String character, List<String> letters){
        letters.add(character);
        return letters;
    }

    public static void gallowsArts(int hp, StringBuilder shadow, String word){
        if (hp == 6){
            System.out.println("""
                    _________
                    |       |
                    |       
                    |      
                    |      
                    |     
                    |
                    |
                    |_________
                    """);
            System.out.println(shadow);
        }
        if (hp == 5){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |      
                    |      
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(shadow);
        }        if (hp == 4){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |       |
                    |      
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(shadow);
        }        if (hp == 3){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |       |
                    |      / 
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(shadow);
        }        if (hp == 2){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |       |
                    |      / \\
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(shadow);
        }        if (hp == 1){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |      /|
                    |      / \\
                    |     
                    |
                    |
                    |_________
                    """);
            System.out.println(shadow);
        }        if (hp == 0){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |      /|\\
                    |      / \\
                    |     
                    |
                    |
                    |_________
                    """);
            System.out.println("Вы ПРОИГРАЛИ!");
            System.out.println(shadow + " -> " + word);
            System.out.println("""
                        
                        Играть ещё раз?
                            да/нет""");
        }
    }

}