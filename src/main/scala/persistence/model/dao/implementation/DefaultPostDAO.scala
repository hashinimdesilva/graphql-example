package persistence.model.dao.implementation

import persistence.DatabaseConnector.db
import persistence.model.DAL.{postQuery, profile}
import persistence.model.dao.PostDAO
import persistence.model.savable.Post

import scala.concurrent.Future

class DefaultPostDAO extends PostDAO {

  import profile.api._

  override def fetchPosts(userId: Int): Future[Seq[Post]] =
    db.run(postQuery.filter(_.userId === userId).result)

}