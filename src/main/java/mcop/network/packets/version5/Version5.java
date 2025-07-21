package mcop.network.packets.version5;

import dev.hilligans.ourcraft.mod.handler.content.ModContainer;
import mcop.MCOP;
import mcop.blocks.Version5BlockTable;
import mcop.network.packets.version5.client.*;
import mcop.network.packets.version5.handshake.CHandshakePacket5;
import mcop.network.packets.version5.login.CLoginStartPacket5;
import mcop.network.packets.version5.login.SDisconnectPacket5;
import mcop.network.packets.version5.login.SEncryptionRequestPacket5;
import mcop.network.packets.version5.login.SLogicSuccessPacket5;
import mcop.network.packets.version5.server.*;
import mcop.network.packets.version5.status.CPingStatus5;
import mcop.network.packets.version5.status.CRequestStatus5;
import mcop.network.packets.version5.status.SPingStatus5;
import mcop.network.packets.version5.status.SResponseStatus5;

public class Version5 {

    public static void register(ModContainer modContainer) {

        /*
        modContainer.registerPacket("mcop:5-handshake", 0, CHandshakePacket5::new);




        modContainer.registerPacket("mcop:5-status-client", 0, CRequestStatus5::new);
        modContainer.registerPacket("mcop:5-status-client", 1, CPingStatus5::new);

        modContainer.registerPacket("mcop:5-status-server", 0, SResponseStatus5::new);
        modContainer.registerPacket("mcop:5-status-server", 1, SPingStatus5::new);

        modContainer.registerPacket("mcop:5-login-client",0, CLoginStartPacket5::new);
        //modContainer.registerPacket("mcop:5-login-client", );

        modContainer.registerPacket("mcop:5-login-server", 0, SDisconnectPacket5::new);
        modContainer.registerPacket("mcop:5-login-server", 1, SEncryptionRequestPacket5::new);
        modContainer.registerPacket("mcop:5-login-server", 2, SLogicSuccessPacket5::new);


        modContainer.registerPacket("mcop:5-play-server", 0, SKeepAlivePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 1, SJoinGamePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 2, SChatMessagePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 3, STimeUpdatePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 4, SEntityEquipmentPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 5, SSpawnPositionPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 6, SUpdateHealthPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 7, SRespawnPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 8, SPlayerPositionAndLookPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 9, SHeldItemChangePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x0A, SUseBedPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x0B, SAnimationPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x0C, SSpawnPlayerPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x0D, SCollectItemPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x0E, SSpawnObjectPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x0F, SSpawnMobPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x10, SSpawnPaintingPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x11, SSpawnExperienceOrbPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x12, SEntityVelocityPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x13, SDestroyEntitiesPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x14, SEntityPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x15, SEntityRelativeMovePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x16, SEntityLookPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x17, SEntityLookAndRelativeMovePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x18, SEntityTeleportPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x19, SEntityHeadLookPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x1A, SEntityStatusPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x1B, SAttachEntityPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x1C, SEntityMetadataPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x1D, SEntityEffectPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x1E, SRemoveEntityEffectPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x1F, SSetExperiencePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x20, SEntityPropertiesPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x21, SChunkDataPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x22, SMultiBlockChangePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x23, SBlockChangePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x24, SBlockActionPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x25, SBlockBreakAnimationPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x26, SMapChunkBulkPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x27, SExplosionPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x28, SEffectPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x29, SSoundEffectPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x2A, SParticlePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x2B, SChangeGameStatePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x2C, SSpawnGlobalEntityPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x2E, SOpenWindowPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x2E, SCloseWindowPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x2F, SSetSlotPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x30, SWindowItemsPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x31, SWindowPropertyPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x32, SConfirmTransactionPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x33, SUpdateSignPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x34, SMapsPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x35, SUpdateBlockEntityPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x36, SSignEditorOpenPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x37, SStatisticsPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x38, SPlayerListItemPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x39, SPlayerAbilitiesPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x3A, STabCompletePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x3B, SScoreboardObjectivePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x3C, SUpdateScorePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x3D, SDisplayScoreboardPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x3E, STeamsPacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x3F, SPluginMessagePacket5::new);
        modContainer.registerPacket("mcop:5-play-server", 0x40, mcop.network.packets.version5.server.SDisconnectPacket5::new);

        modContainer.registerPacket("mcop:5-play-client", 0x00, CKeepAlivePacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x01, CChatMessagePacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x02, CUseEntityPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x03, CPlayerPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x04, CPlayerPositionPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x05, CPlayerLookPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x06, CPlayerPositionAndLookPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x07, CPlayerDiggingPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x08, CPlayerBlockPlacementPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x09, CHeldItemChangePacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x0A, CAnimationPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x0B, CEntityActionPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x0C, CSteerVehiclePacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x0D, CCloseWindowPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x0E, CClickWindowPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x0F, CConfirmTransactionPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x10, CCreativeInventoryActionPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x11, CEnchantItemPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x12, CUpdateSignPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x13, CPlayerAbilitiesPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x14, CTabCompletePacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x15, CClientSettingsPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x16, CClientStatusPacket5::new);
        modContainer.registerPacket("mcop:5-play-client", 0x17, CPluginMessagePacket5::new);

         */

        Version5BlockTable table = new Version5BlockTable(modContainer.gameInstance);
        MCOP.table = table;
        table.loadBlocks(modContainer);
    }


}
