package com.tymwitko.toreplace.list

data class Interval(
  val number: Int,
  val cycleType: CycleType
) {
  constructor(codeString: String) : this(
    codeString.substring(0, codeString.length-1).toInt(),
    codeString.last().toCycleType()!!
  )

  fun toCode() = "$number${cycleType.toCode()}"
}
