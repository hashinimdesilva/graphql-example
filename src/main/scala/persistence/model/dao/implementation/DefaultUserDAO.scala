package persistence.model.dao.implementation

import persistence.DatabaseConnector.db
import persistence.model.DAL.{profile, userQuery}
import persistence.model.dao.UserDAO
import persistence.model.savable.User

import scala.concurrent.Future

class DefaultUserDAO extends UserDAO {

  import profile.api._

  override def getUser(id: Int): Future[Option[User]] =
    db.run(userQuery.filter(_.id === id).result.headOption)
}