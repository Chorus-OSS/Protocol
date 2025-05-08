package org.chorus_oss.protocol.types

import org.chorus_oss.chorus.command.data.CommandEnum

/**
 * CommandEnumConstraintData is sent in the AvailableCommandsPacket to limit what values of an enum may be used
 * taking in account things such as whether cheats are enabled.
 */
data class CommandEnumConstraintData(
    // The option in an enum that the constraints should be applied to.
    var option: String,

    // The name of the enum of which the option above should be constrained.
    var enumData: CommandEnum,

    // List of constraints that should be applied to the enum option.
    var constraints: Array<CommandEnumConstraintType>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandEnumConstraintData

        if (option != other.option) return false
        if (enumData != other.enumData) return false
        if (!constraints.contentEquals(other.constraints)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = option.hashCode()
        result = 31 * result + enumData.hashCode()
        result = 31 * result + constraints.contentHashCode()
        return result
    }
}
