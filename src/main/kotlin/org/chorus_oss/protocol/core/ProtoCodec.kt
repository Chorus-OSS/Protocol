package org.chorus_oss.protocol.core

interface ProtoCodec<T> : ProtoSerializer<T>, ProtoDeserializer<T>