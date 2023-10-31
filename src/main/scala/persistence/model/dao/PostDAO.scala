package persistence.model.dao

import persistence.model.savable.Post

import scala.concurrent.Future

trait PostDAO {

  def fetchPosts(userId: Int): Future[Seq[Post]]

}