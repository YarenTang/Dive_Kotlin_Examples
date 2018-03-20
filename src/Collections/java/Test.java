package Collections.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by prefert on 2018/3/18.
 */
public class Test {
    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        articles.addAll(initArticles());
        findFirstJava(articles);
        System.out.println("find first java tag article = " + findFirstJava(articles));
        System.out.println("find first java tag article stream = " + findFirstJavaStream(articles));

        Map<String, List<Article>> map = groupByAuthor(articles);
        for (String key : map.keySet()) {
            System.out.println("map.get(" + key + ") = " + map.get(key) + "\n-------------------");
        }
        Map<String, List<Article>> StreamMap = groupByAuthorStream(articles);
        StreamMap.keySet().forEach(key -> System.out.println("StreamMap.get(" + key + ") = " + map.get(key) + "\n-------------------"));

        List<RichArticle> richArticles = new ArrayList<>();
        richArticles.addAll(initRichArticles());
        sortedJava(richArticles);
        sortedJava8_1(richArticles);
        sortedJava8_2(richArticles);
    }


    private static List<RichArticle> initRichArticles() {
        List<RichArticle> richArticles = new ArrayList<>();
        List tags0 = Arrays.asList("集合", "函数式", "Java", "设计模式");
        List tags1 = Arrays.asList("前端", "ios", "Swift");
        List tags2 = Arrays.asList("翻译", "!PLAY");
        List tags3 = Arrays.asList("Kafka", "消息队列", "分布式系统");
        List tags4 = Arrays.asList("PHP", "x");

        for (int i = 0; i < 2; i++) {
            richArticles.add(new RichArticle("用 Swift 模仿 Vue + Vuex 进行 iOS 开发（二）：Coordinator" + i, "Yison", tags1, RandomDate.randomDate(), false));
            richArticles.add(new RichArticle("<译> 响应式 Web 应用（五）" + i, "Shaw", tags2, RandomDate.randomDate(), false));
            richArticles.add(new RichArticle("Kafka 学习笔记（一） ：为什么需要 Kafka？" + i, "Godpan", tags3, RandomDate.randomDate(), true));
            richArticles.add(new RichArticle("如何24小时入门 Java " + i, "小王", tags0, RandomDate.randomDate(), false));
            richArticles.add(new RichArticle("PHP 是最好的语言" + i, "小王", tags4, RandomDate.randomDate(), true));
        }
        return richArticles;
    }

    private static List<Article> initArticles() {
        List<Article> articles = new ArrayList<>();
        List tags0 = Arrays.asList("集合", "函数式", "Java", "设计模式");
        List tags1 = Arrays.asList("前端", "ios", "Swift");
        List tags2 = Arrays.asList("翻译", "!PLAY");
        List tags3 = Arrays.asList("Kafka", "消息队列", "分布式系统");
        List tags4 = Arrays.asList("PHP", "x");

        for (int i = 0; i < 2; i++) {
            articles.add(new Article("用 Swift 模仿 Vue + Vuex 进行 iOS 开发（二）：Coordinator" + i, "Yison", tags1));
            articles.add(new Article("<译> 响应式 Web 应用（五）" + i, "Shaw", tags2));
            articles.add(new Article("Kafka 学习笔记（一） ：为什么需要 Kafka？" + i, "Godpan", tags3));
            articles.add(new Article("如何24小时入门 Java " + i, "小王", tags0));
            articles.add(new Article("PHP 是最好的语言" + i, "小王", tags4));
        }
        return articles;
    }

    private static Article findFirstJava(List<Article> articles) {
        for (Article article : articles) {
            if (article.getTags().contains("Java")) {
                return article;
            }
        }
        return null;
    }

    private static Optional<Article> findFirstJavaStream(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("Java"))
                .findFirst();
    }

    /**
     * 按照作者对文章分组_循环处理
     *
     * @param articles
     * @return
     */
    private static Map<String, List<Article>> groupByAuthor(List<Article> articles) {
        Map<String, List<Article>> result = new HashMap<>();
        for (Article article : articles) {
            if (result.containsKey(article.getAuthor())) {
                result.get(article.getAuthor()).add(article);
            } else {
                ArrayList<Article> articlesTemp = new ArrayList<>();
                articlesTemp.add(article);
                result.put(article.getAuthor(), articlesTemp);
            }
        }
        return result;
    }

    /**
     * 按照作者对文章分组_流处理
     *
     * @param articles
     * @return
     */
    private static Map<String, List<Article>> groupByAuthorStream(List<Article> articles) {
        return articles.stream()
                .collect(Collectors.groupingBy(Article::getAuthor));
    }


    /**
     * 最原始Java多条件排序
     *
     * @param richArticles
     */
    private static void sortedJava(List<RichArticle> richArticles) {
        Collections.sort(richArticles, new Comparator<RichArticle>() {
            @Override
            public int compare(RichArticle r1, RichArticle r2) {
                int x = r1.getPinned().compareTo(r2.getPinned());
                int y = r1.getCreateTime().compareTo(r2.getCreateTime());
                if (x == 0) {
                    return y;
                }
                return x;
            }
        });

    }

    /**
     * lambda 版本排序
     *
     * @param richArticles
     */
    private static void sortedJava8_1(List<RichArticle> richArticles) {
        richArticles.sort((r1, r2) -> {
            int x = r1.getPinned().compareTo(r2.getPinned());
            int y = r1.getCreateTime().compareTo(r2.getCreateTime());
            if (x == 0) {
                return y;
            }
            return x;
        });
    }

    /**
     * Java 8 排序_链式
     *
     * @param richArticles
     */
    private static void sortedJava8_2(List<RichArticle> richArticles) {
        richArticles.sort(Comparator.comparing(RichArticle::getPinned).thenComparing(RichArticle::getCreateTime));
    }
}
