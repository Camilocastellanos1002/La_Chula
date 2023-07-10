package network

import java.lang.Exception

interface Callback<T> { //como no se sabe que tipo de dato tiene esta interface, <T> significa que puede der se diferente tipo cada vez
    fun onSuccess(result: T?) // no se sabe de que tipo
    fun onFailed(exception: Exception)  //en caso de fallo envia una excepcion

}