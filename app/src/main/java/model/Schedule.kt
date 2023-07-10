package model

import java.io.Serializable
import java.util.*

class Schedule : Serializable{
    lateinit var event: String
    lateinit var provision: String
    lateinit var color_provision: String
    lateinit var datetime: Date
    lateinit var singer : String
    lateinit var description: String
    var image =""
}