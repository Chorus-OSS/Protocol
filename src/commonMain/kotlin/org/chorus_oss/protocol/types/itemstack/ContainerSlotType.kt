package org.chorus_oss.protocol.types.itemstack

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class ContainerSlotType(val id: Byte) {
    AnvilInput(0),
    AnvilMaterial(1),
    AnvilResult(2),
    SmithingTableInput(3),
    SmithingTableMaterial(4),
    SmithingTableResult(5),
    Armor(6),
    LevelEntity(7),
    BeaconPayment(8),
    BrewingInput(9),
    BrewingResult(10),
    BrewingFuel(11),
    HotbarAndInventory(12),
    CraftingInput(13),
    CraftingOutput(14),
    RecipeConstruction(15),
    RecipeNature(16),
    RecipeItems(17),
    RecipeSearch(18),
    RecipeSearchBar(19),
    RecipeEquipment(20),
    RecipeBook(21),
    EnchantingInput(22),
    EnchantingMaterial(23),
    FurnaceFuel(24),
    FurnaceIngredient(25),
    FurnaceResult(26),
    HorseEquip(27),
    Hotbar(28),
    Inventory(29),
    ShulkerBox(30),
    TradeIngredient1(31),
    TradeIngredient2(32),
    TradeResult(33),
    Offhand(34),
    CompoundCreatorInput(35),
    CompoundCreatorOutput(36),
    ElementConstructorOutput(37),
    MaterialReducerInput(38),
    MaterialReducerOutput(39),
    LabTableInput(40),
    LoomInput(41),
    LoomDye(42),
    LoomMaterial(43),
    LoomResult(44),
    BlastFurnaceIngredient(45),
    SmokerIngredient(46),
    Trade2Ingredient1(47),
    Trade2Ingredient2(48),
    Trade2Result(49),
    GrindstoneInput(50),
    GrindstoneAdditional(51),
    GrindstoneResult(52),
    StonecutterInput(53),
    StonecutterResult(54),
    CartographyInput(55),
    CartographyAdditional(56),
    CartographyResult(57),
    Barrel(58),
    Cursor(59),
    CreatedOutput(60),
    SmithingTableTemplate(61),
    CrafterBlockContainer(62),
    DynamicContainer(63);


    companion object : ProtoCodec<ContainerSlotType> {
        override fun serialize(
            value: ContainerSlotType,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.id, stream)
        }

        override fun deserialize(stream: Source): ContainerSlotType {
            return Proto.Byte.deserialize(stream).let {
                entries.find { e -> e.id == it }!!
            }
        }
    }
}
