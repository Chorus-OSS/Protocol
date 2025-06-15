package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class DisconnectFailReason {
    Unknown,
    CantConnectNoInternet,
    NoPermissions,
    UnrecoverableError,
    ThirdPartyBlocked,
    ThirdPartyNoInternet,
    ThirdPartyBadIp,
    ThirdPartyNoServerOrServerLocked,
    VersionMismatch,
    SkinIssue,
    InviteSessionNotFound,
    EduLevelSettingsMissing,
    LocalServerNotFound,
    LegacyDisconnect,
    UserLeaveGameAttempted,
    PlatformLockedSkinsError,
    RealmsWorldUnassigned,
    RealmsServerCantConnect,
    RealmsServerHidden,
    RealmsServerDisabledBeta,
    RealmsServerDisabled,
    CrossPlatformDisallowed,
    CantConnect,
    SessionNotFound,
    ClientSettingsIncompatibleWithServer,
    ServerFull,
    InvalidPlatformSkin,
    EditionVersionMismatch,
    EditionMismatch,
    LevelNewerThanExeVersion,
    NoFailOccurred,
    BannedSkin,
    Timeout,
    ServerNotFound,
    OutdatedServer,
    OutdatedClient,
    NoPremiumPlatform,
    MultiplayerDisabled,
    NoWifi,
    WorldCorruption,
    NoReason,
    Disconnected,
    InvalidPlayer,
    LoggedInOtherLocation,
    ServerIdConflict,
    NotAllowed,
    NotAuthenticated,
    InvalidTenant,
    UnknownPacket,
    UnexpectedPacket,
    InvalidCommandRequestPacket,
    HostSuspended,
    LoginPacketNoRequest,
    LoginPacketNoCert,
    MissingClient,
    Kicked,
    KickedForExploit,
    KickedForIdle,
    ResourcePackProblem,
    IncompatiblePack,
    OutOfStorage,
    InvalidLevel,
    @Deprecated("")
    DisconnectPacket,
    BlockMismatch,
    InvalidHeights,
    InvalidWidths,
    ConnectionLost,
    ZombieConnection,
    Shutdown,
    ReasonNotSet,
    LoadingStateTimeout,
    ResourcePackLoadingFailed,
    SearchingForSessionLoadingScreenFailed,
    ConnProtocolVersion,
    SubsystemStatusError,
    EmptyAuthFromDiscovery,
    EmptyUrlFromDiscovery,
    ExpiredAuthFromDiscovery,
    UnknownSignalServiceSignInFailure,
    XblJoinLobbyFailure,
    UnspecifiedClientInstanceDisconnection,
    ConnSessionNotFound,
    ConnCreatePeerConnection,
    ConnIce,
    ConnConnectRequest,
    ConnConnectResponse,
    ConnNegotiationTimeout,
    ConnInactivityTimeout,
    StaleConnectionBeingReplaced,
    @Deprecated("since v685")
    RealmsSessionNotFound,
    BadPacket,
    ConnClientSignalingError,
    SubClientLoginDisabled,
    DeepLinkTryingToOpenDemoWorldWhileSignedIn,
    AsyncJoinTaskDenied,
    RealmsTimelineRequired,
    GuestWithoutHost,
    FailedToJoinExperience;

    companion object : ProtoCodec<DisconnectFailReason> {
        override fun serialize(value: DisconnectFailReason, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): DisconnectFailReason {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}

