package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class ExpressionOp {
    LEFT_BRACE,
    RIGHT_BRACE,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    NEGATE,
    LOGICAL_NOT,
    ABS,
    ADD,
    ACOS,
    ASIN,
    ATAN,
    ATAN2,
    CEIL,
    CLAMP,
    COPY_SIGN,
    COS,
    DIE_ROLL,
    DIE_ROLL_INT,
    DIV,
    EXP,
    FLOOR,
    HERMITE_BLEND,
    LERP,
    LERP_ROTATE,
    LN,
    MAX,
    MIN,
    MIN_ANGLE,
    MOD,
    MUL,
    POW,
    RANDOM,
    RANDOM_INT,
    ROUND,
    SIN,
    SIGN,
    SQRT,
    TRUNC,
    QUERY_FUNCTION,
    ARRAY_VARIABLE,
    CONTEXT_VARIABLE,
    ENTITY_VARIABLE,
    TEMP_VARIABLE,
    MEMBER_ACCESSOR,
    HASHED_STRING_HASH,
    GEOMETRY_VARIABLE,
    MATERIAL_VARIABLE,
    TEXTURE_VARIABLE,
    LESS_THAN,
    LESS_EQUAL,
    GREATER_EQUAL,
    GREATER_THAN,
    LOGICAL_EQUAL,
    LOGICAL_NOT_EQUAL,
    LOGICAL_OR,
    LOGICAL_AND,
    NULL_COALESCING,
    CONDITIONAL,
    CONDITIONAL_ELSE,
    FLOAT,
    PI,
    ARRAY,
    GEOMETRY,
    MATERIAL,
    TEXTURE,
    LOOP,
    FOR_EACH,
    BREAK,
    CONTINUE,
    ASSIGNMENT,
    POINTER,
    SEMICOLON,
    RETURN,
    COMMA,
    THIS,
    NON_EVALUATED_ARRAY;

    companion object : ProtoCodec<ExpressionOp> {
        override fun serialize(value: ExpressionOp, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): ExpressionOp {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}