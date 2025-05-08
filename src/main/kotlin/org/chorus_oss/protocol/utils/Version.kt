package org.chorus_oss.protocol.utils

import java.io.Serializable
import java.util.*
import kotlin.math.max

/**
 * Represents a version string allowing to compare.
 *
 *
 * The version must contain letters, numbers or ideograms. Other characters are considered separators.
 * If a string contains only separators than it will be considered the same as an empty string.
 *
 *
 * The versions are case-insensitive but the original case-sensitive version can be retrieved from [.toString].
 */
class Version(private val original: String) : Comparable<Version>, Serializable {
    @Transient
    private var list: List<String>? = null

    val parts: List<String>
        /**
         * Returns the parts that was identified in the version string. It parses the version on the first call,
         * the next calls will return a cached result.
         * @return An immutable list containing a mix of [Integer] and [String] objects
         * @since 0.1.0
         */
        get() {
            if (list == null) {
                list = parse(original)
            }
            return list!!
        }

    /**
     * Compare this version with another to determine which one is newer.
     * Both version strings will be parsed if necessary and if they are not cached.
     * @param other The other version to compare
     * @throws NullPointerException If `o` is `null`
     * @throws ClassCastException If `o` is not an instance of [Version]
     * @return Negative number if this version is older than `o`, `0` if it is the same,
     * or a positive number if `o` is newer
     * @since 0.1.0
     */
    override fun compareTo(other: Version): Int {
        if (original.equals(other.original, ignoreCase = true)) {
            return 0
        }

        val partsB = other.parts
        val partsA = parts
        val sizeA = partsA.size
        val sizeB = partsB.size
        for (i in 0..<max(sizeA.toDouble(), sizeB.toDouble()).toInt()) {
            val a = if (i < sizeA) partsA[i] else ""
            val b = if (i < sizeB) partsB[i] else ""
            if (a.javaClass != b.javaClass) {
                return if (Int::class.java == a.javaClass) 1 else -1
            }
            val cmp = a.compareTo(b)
            if (cmp != 0) {
                return cmp
            }
        }
        return 0
    }

    /**
     * Returns the original string that was used to create this instance.
     * @return The original version string.
     * @since 0.1.0
     */
    override fun toString(): String {
        return original
    }

    /**
     * Compares if the other object is a [Version] object, and it represents the same version.
     * The comparison is case-insensitive.
     * Both version strings will be parsed if necessary and if they are not cached.
     * @param other The object to be compared
     * @return If the object represents the same version
     * @since 0.1.0
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val other1 = other as Version
        if (original.equals(other1.original, ignoreCase = true)) return true
        return compareTo(other1) == 0
    }

    /**
     * Generates a hash code based on the processed parts.
     * The original string will be parsed if it was not parsed yet.
     * @return A hashcode compatible with the [.equals] and [.compareTo] implementations
     * @since 0.1.0
     */
    override fun hashCode(): Int {
        return parts.hashCode()
    }

    companion object {
        private const val serialVersionUID = 1L

        /**
         * Split the version into parts, separating the integers and the words from the version.
         * @param version The full version string
         * @return An immutable list containing [Integer] and [String] objects
         * @since 0.1.0
         */
        private fun parse(version: String): List<String> {
            val parts = ArrayList<String>(5)
            val len = version.length
            val pending = StringBuilder(len)
            var type: Byte
            var previous: Byte = 0
            for (i in 0..<len) {
                val c = version[i]
                type = if (c in '0'..'9') {
                    1
                } else if (Character.isDigit(c)) {
                    2
                } else if (Character.isLetter(c) || Character.isIdeographic(c.code)) {
                    3
                } else {
                    0
                }
                if (type != previous) {
                    addPendingPart(parts, pending, previous)
                    previous = type
                }
                when (type.toInt()) {
                    1 -> pending.append(c)
                    2, 3 -> pending.append(c.lowercaseChar())
                    else -> Unit
                }
            }
            addPendingPart(parts, pending, previous)
            parts.trimToSize()
            return Collections.unmodifiableList(parts)
        }

        /**
         * Adds an [Integer] or a [String] into `parts` built from the `pending` param
         * depending on the type given in the `type` param.
         * @param parts The list that will receive the object. Must support the [MutableList.add] operation.
         * @param pending A builder containing the value of the object that will be added. The content will be cleared when used.
         * @param type One of the type bellow to indicate what is inside the `pending` param:
         *
         *  1. Contains only ASCII decimal digits
         *  1. Contains only numbers but they are not ASCII
         *  1. Contains letters and/or ideograms
         *
         * @since 0.1.0
         */
        private fun addPendingPart(
            parts: MutableList<String>,
            pending: StringBuilder,
            type: Byte
        ) {
            when (type.toInt()) {
                1 -> {
                    parts.add(pending.toString())
                    pending.setLength(0)
                }

                2, 3 -> {
                    parts.add(pending.toString())
                    pending.setLength(0)
                }

                else -> Unit
            }
        }
    }
}
