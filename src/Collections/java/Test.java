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

        Map<String, List<Article>> map =  groupByAuthor(articles);
        for (String key : map.keySet()) {
            System.out.println("map.get(" + key + ") = " + map.get(key) + "\n-------------------");
        }
        Map<String, List<Article>> StreamMap =  groupByAuthorStream(articles);
        StreamMap.keySet().forEach(key -> System.out.println("StreamMap.get(" + key + ") = " + map.get(key) + "\n-------------------"));
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
     * @param articles
     * @return
     */
    private static Map<String, List<Article>> groupByAuthorStream(List<Article> articles) {
        return articles.stream()
                .collect(Collectors.groupingBy(Article::getAuthor));
    }
}
