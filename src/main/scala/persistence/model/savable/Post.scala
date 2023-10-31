package persistence.model.savable

import java.sql.Timestamp

case class Post(id: Int,
                userId: Int,
                createdTime: Timestamp = new Timestamp(System.currentTimeMillis()),
                content: String)
