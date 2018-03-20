package Collections.kotlin

import java.util.*

/**
 * Created by prefert on 2018/3/20.
 */
data class RichArticle(val title: String,
                       val author: String,
                       val tags: List<String>,
                       val createTime: Date,
                       val isPinned: Boolean)