package persistence.model.dao

import persistence.model.savable.User

import scala.concurrent.Future

trait UserDAO {

  def getUser(int: Int): Future[Option[User]]

}