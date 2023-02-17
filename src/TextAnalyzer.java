import java.util.*;

//Это пример текста. Он содержит некоторые слова, которые мы будем анализировать
//Я помню чудное мгновенье: Передо мной явилась ты, Как мимолетное виденье, Как гений чистой красоты. В томленьях грусти безнадежной, В тревогах шумной суеты, Звучал мне долго голос нежный И снились милые черты.
public class TextAnalyzer {
    static Scanner scanner;
    static String text;
    static String lowerCaseText;
    static String[] words;
    static int wordCount;
    static int sentenceCount;
    static int characterCount;


    public static void main(String[] args) {
        start();
        //removingStopWordsAndConvertingTextToLowercase();
        //calculateTextStatistics();
        //findingWordInText();
        //countingTheNumberOfWordsInText();
        //countingTheNumberOfOccurrencesOfWordInText();
        //initializingVariablesToStoreLongestAndShortestWords();
        //initializationIsUsedStoreTheNumberOfWordsSentencesAndCharacters();
        bigramAndTrigramInTheText();
    }

    static void start() {
        scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        text = scanner.nextLine();
        System.out.println("Вы ввели следующий текст:");
        System.out.println(text);
    }

    // Удаляем стоп-слова
    public static void removingStopWordsAndConvertingTextToLowercase() {

        List<String> stopWords = Arrays.asList("это", "он", "в", "не", "во", "на", "с", "со", "и", "а", "но", "то", "что", "мы");
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                result.append(word).append(" ");
            }
        }
        String processedText = result.toString().trim();

        // Приводим текст к нижнему регистру
        lowerCaseText = processedText.toLowerCase();

        System.out.println("Обработанный текст:");
        System.out.println(lowerCaseText);
    }

    // Вычисляем статистику текста
    public static void calculateTextStatistics() {
        String[] wordsArray = lowerCaseText.split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();
        int totalWords = wordsArray.length;
        int uniqueWords = 0;
        for (String word : wordsArray) {
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 1);
                uniqueWords++;
            } else {
                int count = wordCount.get(word);
                wordCount.put(word, count + 1);
            }
        }

        System.out.println("Статистика текста:");
        System.out.println("Общее количество слов: " + totalWords);
        System.out.println("Количество уникальных слов: " + uniqueWords);
        System.out.println("Частота встречаемости каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            double frequency = (double) entry.getValue() / totalWords;
            System.out.printf("%s: %.2f%n", entry.getKey(), frequency);
        }
    }

    // Поиск слова в тексте
    public static void findingWordInText() {
        System.out.println("Введите слово которое надо найти в тексте:");
        String wordToSearch = scanner.next();

        if (text.contains(wordToSearch)) {
            System.out.println("Слово \"" + wordToSearch + "\" найдено в тексте.");
        } else {
            System.out.println("Слово \"" + wordToSearch + "\" не найдено в тексте.");
        }
    }

    // Подсчет количества слов в тексте

    public static void countingTheNumberOfWordsInText() {
        int wordCount = text.split("\\s+").length;

        System.out.println("Количество слов в тексте: " + wordCount);

    }

    // Подсчет количества вхождений слова в тексте
    public static void countingTheNumberOfOccurrencesOfWordInText() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            Integer count = wordCountMap.get(word);
            if (count == null) {
                wordCountMap.put(word, 1);
            } else {
                wordCountMap.put(word, count + 1);
            }
        }

        // Поиск наиболее часто встречающихся слов в тексте
        int maxCount = 0;
        String maxWord = "";
        for (String word : wordCountMap.keySet()) {
            int count = wordCountMap.get(word);
            if (count > maxCount) {
                maxCount = count;
                maxWord = word;
            }
        }

        System.out.println("Наиболее часто встречающееся слово в тексте: \"" + maxWord + "\" (встречается " + maxCount + " раз)");
    }

    public static void initializingVariablesToStoreLongestAndShortestWords() {
        words = text.split("\\s+");
        String longestWord = "";
        String shortestWord = words[0];

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
            if (word.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }

        System.out.println("Самое длинное слово: " + longestWord);
        System.out.println("Самое короткое слово: " + shortestWord);

    }

    public static void initializationIsUsedStoreTheNumberOfWordsSentencesAndCharacters() {
        words = text.split("\\s+");

        wordCount = words.length;
        sentenceCount = text.split("[!?.:]+").length;
        characterCount = text.replaceAll("\\s", "").length();

        // Информация о тексте
        System.out.println("Количество слов: " + wordCount);
        System.out.println("Количество предложений: " + sentenceCount);
        System.out.println("Количество символов: " + characterCount);

    }

    public static void bigramAndTrigramInTheText() {
        String[] words = text.split("\\s+");

        // Инициализация переменных для хранения количества слов, предложений и символов
        wordCount = words.length;
        sentenceCount = text.split("[!?.:]+").length;
        characterCount = text.replaceAll("\\s", "").length();

        // Информация о тексте
        System.out.println("Количество слов: " + wordCount);
        System.out.println("Количество предложений: " + sentenceCount);
        System.out.println("Количество символов: " + characterCount);

        // Подсчет количества вхождений каждой биграммы в тексте
        Map<String, Integer> bigramCounts = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String bigram = words[i] + " " + words[i+1];
            if (bigramCounts.containsKey(bigram)) {
                bigramCounts.put(bigram, bigramCounts.get(bigram) + 1);
            } else {
                bigramCounts.put(bigram, 1);
            }
        }

        // Сортировка списка биграмм по количеству вхождений
        List<Map.Entry<String, Integer>> bigramList = new ArrayList<>(bigramCounts.entrySet());
        bigramList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Вывод списка 10 самых часто встречающихся биграмм
        System.out.println("Самые часто встречающиеся биграммы:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : bigramList) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " раз");
            count++;
            if (count == 10) {
                break;
            }
        }

        // Подсчет количества вхождений каждой триграммы в тексте
        Map<String, Integer> trigramCounts = new HashMap<>();
        for (int i = 0; i < words.length - 2; i++) {
            String trigram = words[i] + " " + words[i+1] + " " + words[i+2];
            if (trigramCounts.containsKey(trigram)) {
                trigramCounts.put(trigram, trigramCounts.get(trigram) + 1);
            } else {
                trigramCounts.put(trigram, 1);
            }
        }

        // Сортировка списка триграмм по количеству вхождений
        List<Map.Entry<String, Integer>> trigramList = new ArrayList<>(trigramCounts.entrySet());
        trigramList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Вывод списка 10 самых часто встречающихся триграмм
        System.out.println("Самые часто встречающиеся триграммы:");
        count = 0;
        for (Map.Entry<String, Integer> entry : trigramList) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " раз");
            count++;
            if (count == 10) {
                break;
            }
        }
    }
}


