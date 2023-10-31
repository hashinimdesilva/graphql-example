package graphql

import persistence.model.dao.{PostDAO, UserDAO}

case class MyContext(userDAO: UserDAO,
                     postDAO: PostDAO) {

}
