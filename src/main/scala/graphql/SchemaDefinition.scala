package graphql

import persistence.model.savable.{Post, User}
import sangria.schema._
import util.TimestampConverter

object SchemaDefinition {

  private val ID: Argument[Int] = Argument("id", IntType, description = "id of the user")

  private val PostType: ObjectType[Unit, Post] = ObjectType("Post", fields[Unit, Post](
    Field("id", IntType, resolve = _.value.id),
    Field("userId", IntType, resolve = _.value.userId),
    Field("createdTime", StringType,
      resolve = ctx => TimestampConverter.convertToString(ctx.value.createdTime)),
    Field("content", StringType, resolve = _.value.content)
  ))

  private val UserType: ObjectType[MyContext, User] = ObjectType("User", fields[MyContext, User](
    Field("id", IntType, resolve = _.value.id),
    Field("name", StringType, resolve = _.value.name),
    Field("posts", ListType(PostType), resolve = ctx => ctx.ctx.postDAO.fetchPosts(ctx.value.id))
  ))

  private val QueryType: ObjectType[MyContext, Unit] = ObjectType("Query", fields[MyContext, Unit](
    Field("user", OptionType(UserType), arguments = ID :: Nil,
      resolve = ctx => ctx.ctx.userDAO.getUser(ctx.arg(ID)))
  ))

  val userSchema: Schema[MyContext, Unit] = Schema(QueryType)

}