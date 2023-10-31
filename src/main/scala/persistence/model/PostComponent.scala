package persistence.model

import persistence.model.savable.Post
import slick.lifted.ProvenShape

import java.sql.Timestamp

trait PostComponent {

  this: ProfileComponent =>

  import profile.api._

  class PostTable(tag: Tag) extends Table[Post](tag, _tableName = "posts") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def userId: Rep[Int] = column[Int]("user_id")

    def createdTime: Rep[Timestamp] = column[Timestamp]("created_time")

    def content: Rep[String] = column[String]("content")

    override def * : ProvenShape[Post] = (id, userId, createdTime, content) <> (Post.tupled, Post.unapply)
  }

  val postQuery = TableQuery[PostTable]

}