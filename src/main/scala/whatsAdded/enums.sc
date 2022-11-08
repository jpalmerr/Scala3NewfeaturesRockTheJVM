// sealed data type
enum Permissions {
  case READ, WRITE, EXECUTE, NONE

  def openDocument: Unit = {
    if (this == READ) println("Opening document...")
    else println("Reading not allowed")
  }
}

val somePermissions: Permissions = Permissions.READ
somePermissions.openDocument

// constructor args
enum PermissionWithBits(bits: Int) {
  case READ extends PermissionWithBits(4) // 100
  case WRITE extends PermissionWithBits(2) // 010
  case EXECUTE extends PermissionWithBits(1) // 001
  case NONE extends PermissionWithBits(0) // 000
}

object PermissionWithBits {
  def fromBits(bits: Int): PermissionWithBits = PermissionWithBits.NONE
}

// enum standard api

// index/ordinal: where it was definied ..

val somePermissionsOrdinal = somePermissions.ordinal // 0
Permissions.WRITE.ordinal // 1 ....

val allPermissions = PermissionWithBits.values

Permissions.valueOf("READ")

