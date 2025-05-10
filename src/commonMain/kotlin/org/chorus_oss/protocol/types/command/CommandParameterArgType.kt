package org.chorus_oss.protocol.types.command

object CommandParameterArgType {
    const val VALID: UInt = 0x100000u
    const val ENUM: UInt = 0x200000u
    const val SUFFIXED: UInt = 0x1000000u
    const val DYNAMIC_ENUM: UInt = 0x4000000u
    
    const val INT: UInt = 1u
    const val FLOAT: UInt = 3u
    const val VALUE: UInt = 4u
    const val WILDCARD_INT: UInt = 5u
    const val OPERATOR: UInt = 6u
    const val COMPARE_OPERATOR: UInt = 7u
    const val TARGET: UInt = 8u
    const val WILDCARD_TARGET: UInt = 10u
    const val FILE_PATH: UInt = 17u
    const val FULL_INTEGER_RANGE: UInt = 23u
    const val EQUIPMENT_SLOT: UInt = 47u
    const val STRING: UInt = 56u
    const val BLOCK_POSITION: UInt = 64u
    const val POSITION: UInt = 65u
    const val MESSAGE: UInt = 68u
    const val RAWTEXT: UInt = 70u
    const val JSON: UInt = 74u
    const val BLOCK_STATES: UInt = 84u
    const val COMMAND: UInt = 87u
}