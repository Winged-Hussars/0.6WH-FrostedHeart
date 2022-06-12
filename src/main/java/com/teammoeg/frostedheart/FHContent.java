/*
 * Copyright (c) 2021 TeamMoeg
 *
 * This file is part of Frosted Heart.
 *
 * Frosted Heart is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Frosted Heart is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Frosted Heart. If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.frostedheart;

import static com.teammoeg.frostedheart.util.FHProps.berryBushBlocks;
import static com.teammoeg.frostedheart.util.FHProps.cropProps;
import static com.teammoeg.frostedheart.util.FHProps.ore_gravel;
import static com.teammoeg.frostedheart.util.FHProps.stoneDecoProps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import com.cannolicatfish.rankine.init.RankineBlocks;
import com.google.common.collect.ImmutableSet;
import com.teammoeg.frostedheart.base.block.FHBaseBlock;
import com.teammoeg.frostedheart.base.item.FHArmorMaterial;
import com.teammoeg.frostedheart.base.item.FHBaseArmorItem;
import com.teammoeg.frostedheart.base.item.FHBaseItem;
import com.teammoeg.frostedheart.base.item.FHBlockItem;
import com.teammoeg.frostedheart.base.item.FoodBlockItem;
import com.teammoeg.frostedheart.content.agriculture.RyeBlock;
import com.teammoeg.frostedheart.content.agriculture.WhiteTurnipBlock;
import com.teammoeg.frostedheart.content.agriculture.WolfBerryBushBlock;
import com.teammoeg.frostedheart.content.cmupdate.CMUpdateBlock;
import com.teammoeg.frostedheart.content.cmupdate.CMUpdateTileEntity;
import com.teammoeg.frostedheart.content.decoration.*;
import com.teammoeg.frostedheart.content.decoration.oilburner.OilBurnerBlock;
import com.teammoeg.frostedheart.content.decoration.oilburner.OilBurnerTileEntity;
import com.teammoeg.frostedheart.content.decoration.oilburner.SmokeBlockT1;
import com.teammoeg.frostedheart.content.generator.GeneratorRecipe;
import com.teammoeg.frostedheart.content.generator.GeneratorRecipeSerializer;
import com.teammoeg.frostedheart.content.generator.GeneratorSteamRecipe;
import com.teammoeg.frostedheart.content.generator.GeneratorSteamRecipeSerializer;
import com.teammoeg.frostedheart.content.generator.HeatedGeneratorMultiBlock;
import com.teammoeg.frostedheart.content.generator.NormalGeneratorMultiBlock;
import com.teammoeg.frostedheart.content.generator.UnlitHeatedGeneratorMultiBlock;
import com.teammoeg.frostedheart.content.generator.t1.T1GeneratorContainer;
import com.teammoeg.frostedheart.content.generator.t1.T1GeneratorMultiblock;
import com.teammoeg.frostedheart.content.generator.t1.T1GeneratorTileEntity;
import com.teammoeg.frostedheart.content.generator.t2.T2GeneratorContainer;
import com.teammoeg.frostedheart.content.generator.t2.T2GeneratorMultiblock;
import com.teammoeg.frostedheart.content.generator.t2.T2GeneratorTileEntity;
import com.teammoeg.frostedheart.content.incubator.IncubateRecipe;
import com.teammoeg.frostedheart.content.incubator.IncubateRecipeSerializer;
import com.teammoeg.frostedheart.content.recipes.CampfireDefrostRecipe;
import com.teammoeg.frostedheart.content.recipes.CampfireDefrostRecipeSerializer;
import com.teammoeg.frostedheart.content.recipes.DietValueRecipe;
import com.teammoeg.frostedheart.content.recipes.DietValueSerializer;
import com.teammoeg.frostedheart.content.recipes.RecipeInner;
import com.teammoeg.frostedheart.content.recipes.RecipeInnerDismantle;
import com.teammoeg.frostedheart.content.recipes.RecipeInnerDismantleSerializer;
import com.teammoeg.frostedheart.content.recipes.RecipeInnerSerializer;
import com.teammoeg.frostedheart.content.recipes.SmokingDefrostRecipe;
import com.teammoeg.frostedheart.content.recipes.SmokingDefrostRecipeSerializer;
import com.teammoeg.frostedheart.content.steamenergy.DebugHeaterBlock;
import com.teammoeg.frostedheart.content.steamenergy.DebugHeaterTileEntity;
import com.teammoeg.frostedheart.content.steamenergy.HeatDebugItem;
import com.teammoeg.frostedheart.content.steamenergy.HeatPipeBlock;
import com.teammoeg.frostedheart.content.steamenergy.HeatPipeTileEntity;
import com.teammoeg.frostedheart.content.steamenergy.charger.ChargerBlock;
import com.teammoeg.frostedheart.content.steamenergy.charger.ChargerRecipe;
import com.teammoeg.frostedheart.content.steamenergy.charger.ChargerRecipeSerializer;
import com.teammoeg.frostedheart.content.steamenergy.charger.ChargerTileEntity;
import com.teammoeg.frostedheart.content.steamenergy.radiator.RadiatorMultiblock;
import com.teammoeg.frostedheart.content.steamenergy.radiator.RadiatorTileEntity;
import com.teammoeg.frostedheart.content.temperature.FHSoupItem;
import com.teammoeg.frostedheart.content.temperature.MushroomBed;
import com.teammoeg.frostedheart.content.temperature.SoilThermometer;
import com.teammoeg.frostedheart.content.temperature.SteamBottleItem;
import com.teammoeg.frostedheart.content.temperature.ThermometerItem;
import com.teammoeg.frostedheart.content.temperature.ThermosItem;
import com.teammoeg.frostedheart.content.temperature.handstoves.CoalHandStove;
import com.teammoeg.frostedheart.content.temperature.handstoves.RecipeFueling;
import com.teammoeg.frostedheart.content.temperature.handstoves.RecipeFuelingSerializer;
import com.teammoeg.frostedheart.content.temperature.heatervest.HeaterVestItem;
import com.teammoeg.frostedheart.content.tools.CeramicBucket;
import com.teammoeg.frostedheart.content.tools.oredetect.CoreSpade;
import com.teammoeg.frostedheart.content.tools.oredetect.GeologistsHammer;
import com.teammoeg.frostedheart.content.tools.oredetect.ProspectorPick;
import com.teammoeg.frostedheart.research.gui.drawdesk.DrawDeskContainer;
import com.teammoeg.frostedheart.research.machines.DrawingDeskBlock;
import com.teammoeg.frostedheart.research.machines.DrawingDeskTileEntity;
import com.teammoeg.frostedheart.util.FHFoods;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.gui.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FHContent {

    public static List<Block> registeredFHBlocks = new ArrayList<>();
    public static List<Item> registeredFHItems = new ArrayList<>();
    public static List<Fluid> registeredFHFluids = new ArrayList<>();

    public static class FHBlocks {
        public static Block cmupdate = new CMUpdateBlock("cmupdate",Block.Properties.create(Material.ROCK), FHBlockItem::new);
		public static void init() {
        }

        public static Block generator_brick = new FHBaseBlock("generator_brick", stoneDecoProps, FHBlockItem::new);
        public static Block generator_core_t1 = new FHBaseBlock("generator_core_t1", stoneDecoProps, FHBlockItem::new);
        public static Block generator_amplifier_r1 = new FHBaseBlock("generator_amplifier_r1", stoneDecoProps, FHBlockItem::new);
        public static Block rye_block = new RyeBlock("rye_block", -10, cropProps, FHBlockItem::new);
        public static Block wolfberry_bush_block = new WolfBerryBushBlock("wolfberry_bush_block", -100, berryBushBlocks, 10);
        public static Block white_turnip_block = new WhiteTurnipBlock("white_turnip_block", -10, cropProps, ((block, properties) -> new FoodBlockItem(block, properties, FHFoods.WHITE_TURNIP)));
        public static Block copper_gravel = new FHBaseBlock("copper_gravel", ore_gravel, FHBlockItem::new);
        public static Block relic_chest = new RelicChestBlock("relic_chest");
        //        public static Block access_control = new AccessControlBlock("access_control", FHBlockItem::new);
//        public static Block gate = new FHBaseBlock("gate", AbstractBlock.Properties.from(Blocks.BEDROCK), FHBlockItem::new);
        public static Block fluorite_ore;

        public static Block heat_pipe = new HeatPipeBlock("heat_pipe", Block.Properties
                .create(Material.ROCK).sound(SoundType.WOOD)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(1, 5)
                .notSolid(), FHBlockItem::new);
        public static Block debug_heater = new DebugHeaterBlock("debug_heater", Block.Properties
                .create(Material.ROCK).sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2, 10)
                .notSolid(), FHBlockItem::new);
        public static Block charger = new ChargerBlock("charger", Block.Properties
                .create(Material.ROCK)
                .sound(SoundType.METAL)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2, 10)
                .notSolid(), FHBlockItem::new);
        public static Block oilburner=new OilBurnerBlock("oil_burner", Block.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2, 10)
                .notSolid(), FHBlockItem::new);
        public static Block drawing_desk=new DrawingDeskBlock("drawing_desk", Block.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2, 10)
                .notSolid(), FHBlockItem::new);
        public static Block smoket1=new SmokeBlockT1("smoke_block_t1", Block.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2, 10)
                .notSolid(), FHBlockItem::new);
        public static Block high_strength_concrete=new FHBaseBlock("high_strength_concrete", Block.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(45, 800)
                .harvestLevel(3)
                ,FHBlockItem::new);
    }

    public static class FHItems {
        public static void init() {
        }
        static Properties createProps() {
        	return new Item.Properties().group(FHMain.itemGroup);
        }
        public static Item hand_stove=new CoalHandStove("hand_stove",createProps().defaultMaxDamage(10));
        public static Item coal_stick = new FHBaseItem("coal_stick", createProps());
        public static Item charcoal_stick = new FHBaseItem("charcoal_stick", createProps());
        public static Item energy_core = new FHBaseItem("energy_core", createProps());
        public static Item wolfberries = new FoodBlockItem(FHBlocks.wolfberry_bush_block, createProps(), FHFoods.WOLFBERRIES, "wolfberries");
        public static Item dried_wolfberries = new FHBaseItem("dried_wolfberries", createProps().food(FHFoods.DRIED_WOLFBERRIES));
        public static Item rye = new FHBaseItem("rye", createProps());
        public static Item generator_ash = new FHBaseItem("generator_ash", createProps());
        public static Item frozen_seeds = new FHBaseItem("frozen_seeds", createProps());
        public static Item rye_flour = new FHBaseItem("rye_flour", createProps());
        public static Item raw_rye_bread = new FHBaseItem("raw_rye_bread", createProps());
        public static Item random_seeds = new FHBaseItem("random_seeds", createProps());
        public static Item mercury_body_thermometer = new ThermometerItem("mercury_body_thermometer", createProps());
        public static Item rye_bread = new FHBaseItem("rye_bread", createProps().food(FHFoods.RYE_BREAD));
        public static Item black_bread = new FHBaseItem("black_bread", createProps().food(FHFoods.BLACK_BREAD));
        public static Item vegetable_sawdust_soup = new FHSoupItem("vegetable_sawdust_soup", createProps().maxStackSize(1).food(FHFoods.VEGETABLE_SAWDUST_SOUP), true);
        public static Item rye_sawdust_porridge = new FHSoupItem("rye_sawdust_porridge", createProps().maxStackSize(1).food(FHFoods.RYE_SAWDUST_PORRIDGE), true);
        public static Item rye_porridge = new FHSoupItem("rye_porridge", createProps().maxStackSize(1).food(FHFoods.RYE_SAWDUST_PORRIDGE), false);
        public static Item vegetable_soup = new FHSoupItem("vegetable_soup", createProps().maxStackSize(1).food(FHFoods.VEGETABLE_SAWDUST_SOUP), false);
        public static Item steam_bottle = new SteamBottleItem("steam_bottle", createProps().maxStackSize(1));
        public static Item raw_hide = new FHBaseItem("raw_hide", createProps());
        public static Item buff_coat = new FHBaseItem("buff_coat", createProps().defaultMaxDamage(384)).setRepairItem(raw_hide);
        public static Item gambeson = new FHBaseItem("gambeson", createProps().defaultMaxDamage(384)).setRepairItem(Items.WHITE_WOOL);
        public static Item kelp_lining = new FHBaseItem("kelp_lining", createProps().defaultMaxDamage(256)).setRepairItem(Items.KELP);
        public static Item straw_lining = new FHBaseItem("straw_lining", createProps().defaultMaxDamage(256));
        public static Item hay_boots = new FHBaseArmorItem("hay_boots", FHArmorMaterial.HAY, EquipmentSlotType.FEET, createProps());
        public static Item hay_hat = new FHBaseArmorItem("hay_hat", FHArmorMaterial.HAY, EquipmentSlotType.HEAD, createProps());
        public static Item hay_jacket = new FHBaseArmorItem("hay_jacket", FHArmorMaterial.HAY, EquipmentSlotType.CHEST, createProps());
        public static Item hay_pants = new FHBaseArmorItem("hay_pants", FHArmorMaterial.HAY, EquipmentSlotType.LEGS, createProps());
        public static Item wool_boots = new FHBaseArmorItem("wool_boots", FHArmorMaterial.WOOL, EquipmentSlotType.FEET, createProps());
        public static Item wool_hat = new FHBaseArmorItem("wool_hat", FHArmorMaterial.WOOL, EquipmentSlotType.HEAD, createProps());
        public static Item wool_jacket = new FHBaseArmorItem("wool_jacket", FHArmorMaterial.WOOL, EquipmentSlotType.CHEST, createProps());
        public static Item wool_pants = new FHBaseArmorItem("wool_pants", FHArmorMaterial.WOOL, EquipmentSlotType.LEGS, createProps());
        public static Item hide_boots = new FHBaseArmorItem("hide_boots", FHArmorMaterial.HIDE, EquipmentSlotType.FEET, createProps());
        public static Item hide_hat = new FHBaseArmorItem("hide_hat", FHArmorMaterial.HIDE, EquipmentSlotType.HEAD, createProps());
        public static Item hide_jacket = new FHBaseArmorItem("hide_jacket", FHArmorMaterial.HIDE, EquipmentSlotType.CHEST, createProps());
        public static Item hide_pants = new FHBaseArmorItem("hide_pants", FHArmorMaterial.HIDE, EquipmentSlotType.LEGS, createProps());
        public static Item heater_vest = new HeaterVestItem("heater_vest", createProps().maxStackSize(1).setNoRepair());
        public static Item thermos = new ThermosItem("thermos", 1500, 250);
        public static Item advanced_thermos = new ThermosItem("advanced_thermos", 3000, 250);
        public static Item copper_pro_pick = new ProspectorPick("copper_pro_pick",8,4,createProps().defaultMaxDamage(128));
        public static Item iron_pro_pick = new ProspectorPick("iron_pro_pick",8,4,createProps().defaultMaxDamage(192));
        public static Item steel_pro_pick = new ProspectorPick("steel_pro_pick",9,5, createProps().defaultMaxDamage(256));
        public static Item copper_core_spade = new CoreSpade("copper_core_spade",1,32, createProps().defaultMaxDamage(96));
        public static Item iron_core_spade = new CoreSpade("iron_core_spade",2,64, createProps().defaultMaxDamage(128));
        public static Item steel_core_spade = new CoreSpade("steel_core_spade",4,72, createProps().defaultMaxDamage(160));
        public static Item copper_geologists_hammer = new GeologistsHammer("copper_geologists_hammer",4,4, createProps().defaultMaxDamage(96));
        public static Item iron_geologists_hammer = new GeologistsHammer("iron_geologists_hammer",5,5, createProps().defaultMaxDamage(128));
        public static Item steel_geologists_hammer = new GeologistsHammer("steel_geologists_hammer",6,6, createProps().defaultMaxDamage(160));
        public static Item soil_thermometer = new SoilThermometer("soil_thermometer", createProps());
        public static Item heat_debuger = new HeatDebugItem("heat_debugger");
        public static Item red_mushroombed=new MushroomBed("straw_briquette_red_mushroom",Items.RED_MUSHROOM,createProps().defaultMaxDamage(4800));
        public static Item brown_mushroombed=new MushroomBed("straw_briquette_brown_mushroom",Items.BROWN_MUSHROOM,createProps().defaultMaxDamage(4800));
        public static Item ceramic_bucket = new CeramicBucket("ceramic_bucket", createProps().maxStackSize(1));
        public static Item charcoal = new FHBaseItem("charcoal", createProps().maxDamage(50).setNoRepair().maxStackSize(1));
        public static Item quill_and_ink = new FHBaseItem("quill_and_ink", createProps().maxDamage(100).setNoRepair());
    }

    public static class FHMultiblocks {
        public static IETemplateMultiblock GENERATOR = new T1GeneratorMultiblock();
        public static IETemplateMultiblock GENERATOR_T2 = new T2GeneratorMultiblock();
        public static IETemplateMultiblock RADIATOR = new RadiatorMultiblock();
        public static Block generator = new NormalGeneratorMultiBlock("generator", FHTileTypes.GENERATOR_T1);
        public static Block generator_t2 = new HeatedGeneratorMultiBlock("generator_t2", FHTileTypes.GENERATOR_T2);
        public static Block radiator = new UnlitHeatedGeneratorMultiBlock("heat_radiator", FHTileTypes.RADIATOR);

        public static void init() {
            MultiblockHandler.registerMultiblock(FHMultiblocks.GENERATOR);
            MultiblockHandler.registerMultiblock(FHMultiblocks.RADIATOR);
            MultiblockHandler.registerMultiblock(FHMultiblocks.GENERATOR_T2);
        }
    }

    public static class FHTileTypes {
        public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(
                ForgeRegistries.TILE_ENTITIES, FHMain.MODID);

        public static final RegistryObject<TileEntityType<T1GeneratorTileEntity>> GENERATOR_T1 = REGISTER.register(
                "generator", makeType(() -> new T1GeneratorTileEntity(1, 2, 1), () -> FHMultiblocks.generator)
        );

        public static final RegistryObject<TileEntityType<HeatPipeTileEntity>> HEATPIPE = REGISTER.register(
                "heat_pipe", makeType(() -> new HeatPipeTileEntity(), () -> FHBlocks.heat_pipe)
        );
        public static final RegistryObject<TileEntityType<DebugHeaterTileEntity>> DEBUGHEATER = REGISTER.register(
                "debug_heater", makeType(() -> new DebugHeaterTileEntity(), () -> FHBlocks.debug_heater)
        );
        public static final RegistryObject<TileEntityType<ChargerTileEntity>> CHARGER = REGISTER.register(
                "charger", makeType(() -> new ChargerTileEntity(), () -> FHBlocks.charger)
        );

        public static final RegistryObject<TileEntityType<RadiatorTileEntity>> RADIATOR = REGISTER.register(
                "heat_radiator", makeType(() -> new RadiatorTileEntity(), () -> FHMultiblocks.radiator));

        public static final RegistryObject<TileEntityType<T2GeneratorTileEntity>> GENERATOR_T2 = REGISTER.register(
                "generator_t2", makeType(() -> new T2GeneratorTileEntity(1, 2, 1), () -> FHMultiblocks.generator_t2)
        );
        public static final RegistryObject<TileEntityType<OilBurnerTileEntity>> OIL_BURNER = REGISTER.register(
                "oil_burner", makeType(() -> new OilBurnerTileEntity(), () -> FHBlocks.oilburner)
        );

		public static final RegistryObject<TileEntityType<CMUpdateTileEntity>> CMUPDATE = REGISTER.register(
                "cm_update", makeType(() -> new CMUpdateTileEntity(), () -> FHBlocks.cmupdate)
        );

        public static final RegistryObject<TileEntityType<DrawingDeskTileEntity>> DRAWING_DESK = REGISTER.register(
                "drawing_desk", makeType(() -> new DrawingDeskTileEntity(), () -> FHBlocks.drawing_desk)
        );
        public static final RegistryObject<TileEntityType<RelicChestTileEntity>> RELIC_CHEST = REGISTER.register(
                "relic_chest", makeType(() -> new RelicChestTileEntity(), () -> FHBlocks.relic_chest)
        );

        private static <T extends TileEntity> Supplier<TileEntityType<T>> makeType(Supplier<T> create, Supplier<Block> valid) {
            return makeTypeMultipleBlocks(create, () -> ImmutableSet.of(valid.get()));
        }

        private static <T extends TileEntity> Supplier<TileEntityType<T>> makeTypeMultipleBlocks(Supplier<T> create, Supplier<Collection<Block>> valid) {
            return () -> new TileEntityType<>(create, ImmutableSet.copyOf(valid.get()), null);
        }

    }

    public static class FHRecipes {
        public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
                ForgeRegistries.RECIPE_SERIALIZERS, FHMain.MODID
        );

        static {
            GeneratorRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("generator", GeneratorRecipeSerializer::new);
            GeneratorSteamRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("steam_generator", GeneratorSteamRecipeSerializer::new);
            RecipeInner.SERIALIZER = RECIPE_SERIALIZERS.register("recipe_inner", RecipeInnerSerializer::new);
            ChargerRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("charger", ChargerRecipeSerializer::new);
            RecipeInnerDismantle.SERIALIZER=RECIPE_SERIALIZERS.register("recipe_inner_dismantle",RecipeInnerDismantleSerializer::new);
            CampfireDefrostRecipe.SERIALIZER=RECIPE_SERIALIZERS.register("defrost_campfire",CampfireDefrostRecipeSerializer::new);
            SmokingDefrostRecipe.SERIALIZER=RECIPE_SERIALIZERS.register("defrost_smoking",SmokingDefrostRecipeSerializer::new);
            RecipeFueling.SERIALIZER=RECIPE_SERIALIZERS.register("fuel_stove",RecipeFuelingSerializer::new);
            DietValueRecipe.SERIALIZER=RECIPE_SERIALIZERS.register("diet_override",DietValueSerializer::new);
            IncubateRecipe.SERIALIZER=RECIPE_SERIALIZERS.register("incubate",IncubateRecipeSerializer::new);
        }

        public static void registerRecipeTypes() {
            GeneratorRecipe.TYPE = IRecipeType.register(FHMain.MODID + ":generator");
            GeneratorSteamRecipe.TYPE = IRecipeType.register(FHMain.MODID + ":steam_generator");
            ChargerRecipe.TYPE = IRecipeType.register(FHMain.MODID + ":charger");
            DietValueRecipe.TYPE = IRecipeType.register(FHMain.MODID + ":diet_override");
            IncubateRecipe.TYPE = IRecipeType.register(FHMain.MODID + ":incubate");
        }
    }

    public static void registerContainers() {
        GuiHandler.register(T1GeneratorTileEntity.class, new ResourceLocation(FHMain.MODID, "generator"), T1GeneratorContainer::new);
        GuiHandler.register(T2GeneratorTileEntity.class, new ResourceLocation(FHMain.MODID, "generator_t2"), T2GeneratorContainer::new);
        GuiHandler.register(RelicChestTileEntity.class,new ResourceLocation(FHMain.MODID,"relic_chest"), RelicChestContainer::new);
        GuiHandler.register(DrawingDeskTileEntity.class,new ResourceLocation(FHMain.MODID,"draw_desk"), DrawDeskContainer::new);
    }
}
