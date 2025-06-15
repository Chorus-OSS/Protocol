package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class ExpressionOp {
    LeftBrace,
    RightBrace,
    LeftBracket,
    RightBracket,
    LeftParenthesis,
    RightParenthesis,
    Negate,
    LogicalNot,
    Abs,
    Add,
    Acos,
    Asin,
    Atan,
    Atan2,
    Ceil,
    Clamp,
    CopySign,
    Cos,
    DieRoll,
    DieRollInt,
    Div,
    Exp,
    Floor,
    HermiteBlend,
    Lerp,
    LerpRotate,
    Ln,
    Max,
    Min,
    MinAngle,
    Mod,
    Mul,
    Pow,
    Random,
    RandomInt,
    Round,
    Sin,
    Sign,
    Sqrt,
    Trunc,
    QueryFunction,
    ArrayVariable,
    ContextVariable,
    EntityVariable,
    TempVariable,
    MemberAccessor,
    HashedStringHash,
    GeometryVariable,
    MaterialVariable,
    TextureVariable,
    LessThan,
    LessEqual,
    GreaterEqual,
    GreaterThan,
    LogicalEqual,
    LogicalNotEqual,
    LogicalOr,
    LogicalAnd,
    NullCoalescing,
    Conditional,
    ConditionalElse,
    Float,
    Pi,
    Array,
    Geometry,
    Material,
    Texture,
    Loop,
    ForEach,
    Break,
    Continue,
    Assignment,
    Pointer,
    Semicolon,
    Return,
    Comma,
    This,
    NonEvaluatedArray;

    companion object : ProtoCodec<ExpressionOp> {
        override fun serialize(value: ExpressionOp, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): ExpressionOp {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}