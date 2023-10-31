package persistence.model

import persistence.model.savable.User
import slick.lifted.ProvenShape

trait UserComponent {
  this: ProfileComponent =>

  import profile.api._

  class UserTable(tag: Tag) extends Table[User](tag, _tableName = "users") {

    def id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)

    def name: Rep[String] = column[String]("name")

    override def * : ProvenShape[User] = (id, name) <> (User.tupled, User.unapply)
  }

  val userQuery = TableQuery[UserTable]
}