package Collections.kotlin

import java.util.*

/**
 * Created by prefert on 2018/3/18.
 */
object Test {
    val articles = mutableListOf<Article>()

    @JvmStatic
    fun main(args: Array<String>) {
        initArticles()
        println(findFirstKotlin(articles))

        val map = groupByAuthorKotlin(articles)
        map.forEach { println("it.author=${it.key} it.article=${it.value} \n-------------------") }
    }

    private fun initArticles() {
        val tags0 = listOf("集合", "函数式", "Java", "设计模式")
        val tags1 = listOf("前端", "ios", "Swift")
        val tags2 = listOf("翻译", "!PLAY")
        val tags3 = listOf("Kafka", "消息队列", "分布式系统")
        val tags4 = listOf("PHP", "x")

        for (i in 0..2) {
            articles.add(Article("用 Swift 模仿 Vue + Vuex 进行 iOS 开发（二）：Coordinator $i", "Yison", tags1))
            articles.add(Article("<译> 响应式 Web 应用（五）$i", "Shaw", tags2))
            articles.add(Article("Kafka 学习笔记（一） ：为什么需要 Kafka？ $i", "Godpan", tags3))
            articles.add(Article("如何24小时入门 Java $i", "小王", tags0))
            articles.add(Article("PHP 是最好的语言 $i", "小王", tags4))
        }
    }

    private fun findFirstKotlin(articles: List<Article>): Article? {
        return articles
                .find { a: Article -> a.tags.contains("Java") }
    }

    private fun groupByAuthorKotlin(articles: List<Article>): Map<String, List<Article>> {
        return articles.groupBy { it.author }
    }
}