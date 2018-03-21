package Collections.kotlin

import Collections.java.RandomDate.randomDate
import java.util.*
import kotlin.comparisons.compareBy

/**
 * Created by prefert on 2018/3/18.
 */
object Test {
    val articles = mutableListOf<Article>()

    val richArticles = mutableListOf<RichArticle>()
    @JvmStatic
    fun main(args: Array<String>) {
        initArticles()
        println(findFirstKotlin(articles))

        val map = groupByAuthorKotlin(articles)
        map.forEach { println("it.author=${it.key} it.article=${it.value} \n-------------------") }

        initRichArticles()
        richArticles.sortWith(compareBy({ it.isPinned }, { -1 * it.createTime.time }))
        richArticles.forEach(::println)

        basicOperation()
    }

    private fun basicOperation() {
        // 定义并初始化列表
        val list = listOf(1, 2, 3, 4, 5, 6)
        // 过滤
        println(list.filter { it % 2 == 0 })
        // >>> [2, 4, 6]
        println(list.map { it * it })
        // [1, 4, 9, 16, 25, 36]
        val words = listOf(listOf("kotlin"), listOf("is", "best"))
        println(words.flatten())
        // [kotlin, is, best]
        println(words.flatMap { it.map(String::toUpperCase) })
        // [KOTLIN, IS, BEST]

        // 自定义操作符
        println(list.collect({ it * it }, { it % 2 == 0 }))
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

    private fun initRichArticles() {
        val tags0 = listOf("集合", "函数式", "Java", "设计模式")
        val tags1 = listOf("前端", "ios", "Swift")
        val tags2 = listOf("翻译", "!PLAY")
        val tags3 = listOf("Kafka", "消息队列", "分布式系统")
        val tags4 = listOf("PHP", "x")

        for (i in 0..2) {
            richArticles.add(RichArticle("用 Swift 模仿 Vue + Vuex 进行 iOS 开发（二）：Coordinator $i", "Yison", tags1, randomDate(), false))
            richArticles.add(RichArticle("<译> 响应式 Web 应用（五）$i", "Shaw", tags2, randomDate(), false))
            richArticles.add(RichArticle("Kafka 学习笔记（一） ：为什么需要 Kafka？ $i", "Godpan", tags3, randomDate(), false))
            richArticles.add(RichArticle("如何24小时入门 Java $i", "小王", tags0, randomDate(), true))
            richArticles.add(RichArticle("PHP 是最好的语言 $i", "小王", tags4, randomDate(), true))
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

private fun <T, E> Iterable<T>.magicConvert(function: (T) -> E): MutableList<E> {
    val result: MutableList<E> = mutableListOf()
    for (element in this) result.add(function(element))
    return result
}

private fun <T, E> Iterable<T>.collect(function: (T) -> E, predicate: (T) -> Boolean): MutableList<E> {
    val result: MutableList<E> = mutableListOf()
    for (element in this) if (predicate(element)) result.add(function(element))
    return result
}
