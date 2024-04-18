import {Role} from "./Role";

export interface User {
  userId?: number
  userName?: string
  firstName?: string
  lastName?: string
  password?:string
  emailAddress?: string
  phoneNumber?: string
  creationDate?: Date
  accountStatus?: boolean
  roles?:Role[]
}
